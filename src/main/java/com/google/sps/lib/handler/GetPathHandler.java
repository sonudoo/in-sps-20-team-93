// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.lib.handler;

import java.util.ArrayList;
import java.util.List;
import com.google.sps.lib.algorithm.distance.MapsApiDistanceCalculator;
import com.google.sps.lib.algorithm.distance.URLWrapper;
import com.google.sps.lib.algorithm.graph.TravellingSalesmanGraph;
import com.google.sps.lib.algorithm.graph.TravellingSalesmanTask;
import com.google.sps.lib.algorithm.tsp.TspDynamicProgrammingAlgorithm;

/**
 * Gets the best path for all the jobs in the datastore.
 */
public class GetPathHandler implements IRequestHandler {
  /**
   * The home latitudes and longitudes corresponds to India Gate, New Delhi. It is
   * assumed to be the warehouse location from where the vehicle will start and
   * end the delivery task.
   */
  private final static double HOME_LATITUDES = 28.6129;
  private final static double HOME_LONGITUDES = 77.2295;
  private final static String HOME_NAME = "HOME";

  /**
   * This limit comes from the fact that Google Maps Distance Matrix Api can only
   * allow a maximum of 10 origins and 10 destination. One of the origin is HOME
   * coordinates itself.
   * https://developers.google.com/maps/documentation/distance-matrix/usage-and-billing#other-usage-limits
   */
  private static final int MAXIMUM_NUMBER_OF_JOBS = 9;

  private final DatastoreWrapper datastoreWrapper;

  /**
   * The constructor is purposely kept package-private. Please use
   * {@link JobHandlerFactory} to get an instance of this class.
   */
  GetPathHandler(final DatastoreWrapper datastoreWrapper) {
    this.datastoreWrapper = datastoreWrapper;
  }

  /**
   * Fetches all the jobs from the database returns the most optimum path.
   */
  @Override
  public HandlerResponse getResponse() {
    List<DatastoreJob> datastoreJobs = datastoreWrapper.getAllJobs(MAXIMUM_NUMBER_OF_JOBS);

    // Get a list of {@link TravellingSalesmanTask} from all the jobs in the
    // datastore. The task ids are generated serially from 0.
    List<TravellingSalesmanTask> travellingSalesmanTasks = new ArrayList<>();
    for (int i = 0; i < datastoreJobs.size(); i++) {
      travellingSalesmanTasks.add(new TravellingSalesmanTask(/* taskId= */ i, datastoreJobs.get(i).getLatitudes(),
          datastoreJobs.get(i).getLongitudes()));
    }

    // Create a TSP graph and get the optimum order of tasks.
    TravellingSalesmanGraph graph = new TravellingSalesmanGraph(travellingSalesmanTasks,
        new TspDynamicProgrammingAlgorithm(), new MapsApiDistanceCalculator(new URLWrapper()), HOME_LATITUDES,
        HOME_LONGITUDES);
    List<TravellingSalesmanTask> optimumTasks = graph.getMinimumPath();

    // Create a list of {@link ResponseJob} from the ordered list of {@link
    // TravellingSalesmanTask}.
    List<ResponseJob> responseJobs = new ArrayList<>();
    // Add the home location to a response job.
    responseJobs.add(new ResponseJob(HOME_NAME, "", HOME_LATITUDES, HOME_LONGITUDES));
    // Add all the response jobs corresponding to the ordered list.
    for (int i = 0; i < optimumTasks.size(); i++) {
      // We will use the task id to get the {@link DatastoreJob} back from {@link
      // TravellingSalesmanTask}.
      DatastoreJob datastoreJob = datastoreJobs.get(optimumTasks.get(i).getTaskId());
      responseJobs.add(new ResponseJob(datastoreJob.getName(), datastoreJob.getPhone(), datastoreJob.getLatitudes(),
          datastoreJob.getLongitudes()));
    }
    // Add the home location to a response job again.
    responseJobs.add(new ResponseJob(HOME_NAME, "", HOME_LATITUDES, HOME_LONGITUDES));
    return new GetPathResponse(responseJobs);
  }
}