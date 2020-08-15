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

import com.google.sps.lib.algorithm.distance.EuclideanDistanceCalculator;
import com.google.sps.lib.algorithm.graph.TravellingSalesmanGraph;
import com.google.sps.lib.algorithm.graph.TravellingSalesmanTask;
import com.google.sps.lib.algorithm.tsp.TspDynamicProgrammingAlgorithm;

/**
 * Gets the best path for all the jobs in the datastore.
 */
public class GetPathHandler implements IRequestHandler {
  private final static double HOME_LATITUDES = 28.6129;
  private final static double HOME_LONGITUDES = 77.2295;

  private final DatastoreWrapper datastoreWrapper;

  GetPathHandler(final DatastoreWrapper datastoreWrapper) {
    this.datastoreWrapper = datastoreWrapper;
  }

  /**
   * Fetches all the jobs from the database returns the most optimum path.
   */
  @Override
  public HandlerResponse getResponse() {
    List<DatastoreJob> datastoreJobs = datastoreWrapper.getAllJobs();
    List<TravellingSalesmanTask> travellingSalesmanTasks = new ArrayList<>();
    for (int i = 0; i < datastoreJobs.size(); i++) {
      travellingSalesmanTasks
          .add(new TravellingSalesmanTask(i, datastoreJobs.get(i).getLatitude(), datastoreJobs.get(i).getLongitude()));
    }
    TravellingSalesmanGraph graph = new TravellingSalesmanGraph(travellingSalesmanTasks,
        new TspDynamicProgrammingAlgorithm(), new EuclideanDistanceCalculator(), HOME_LATITUDES, HOME_LONGITUDES);
    List<TravellingSalesmanTask> optimumTasks = graph.getMinimumPath();
    List<Double> latitudes = new ArrayList<>();
    List<Double> longitudes = new ArrayList<>();
    latitudes.add(HOME_LATITUDES);
    longitudes.add(HOME_LONGITUDES);
    for (int i = 0; i < optimumTasks.size(); i++) {
      latitudes.add(optimumTasks.get(i).getLatitudes());
      latitudes.add(optimumTasks.get(i).getLongitudes());
    }
    latitudes.add(HOME_LATITUDES);
    longitudes.add(HOME_LONGITUDES);
    return new GetPathResponse(latitudes, longitudes);
  }
}