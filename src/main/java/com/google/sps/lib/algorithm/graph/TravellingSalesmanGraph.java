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

package com.google.sps.lib.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.sps.lib.algorithm.distance.Coordinate;
import com.google.sps.lib.algorithm.distance.IDistanceCalculator;
import com.google.sps.lib.algorithm.tsp.ITspAlgorithm;
import com.google.sps.lib.algorithm.tsp.InvalidDistanceMatrixException;

/**
 * Creates a graph showing distance between the adjacent locations.
 */
public class TravellingSalesmanGraph {
  private final List<TravellingSalesmanTask> taskList;
  private boolean isGraphCreated;
  private double[][] distanceMatrix;
  private IDistanceCalculator distanceCalculator;
  private final ITspAlgorithm tspAlgorithm;
  private final double homeLatitudes;
  private final double homeLongitudes;

  public TravellingSalesmanGraph(List<TravellingSalesmanTask> taskList, ITspAlgorithm tspAlgorithm,
      IDistanceCalculator distanceCalculator, double homeLatitudes, double homeLongitudes) {
    this.taskList = taskList;
    this.isGraphCreated = false;
    this.distanceCalculator = distanceCalculator;
    this.tspAlgorithm = tspAlgorithm;
    this.homeLatitudes = homeLatitudes;
    this.homeLongitudes = homeLongitudes;
  }

  /**
   * Returns the task list in a order such that the total distance is minimized.
   * Returns null if no such order was found.
   */
  public List<TravellingSalesmanTask> getMinimumPath() {
    if (!this.isGraphCreated) {
      List<Coordinate> coordinateList = new ArrayList<>();
      coordinateList.add(new Coordinate(homeLatitudes, homeLongitudes));
      coordinateList.addAll(taskList.stream().map(task -> new Coordinate(task.getLatitudes(), task.getLongitudes()))
          .collect(Collectors.toList()));

      this.distanceMatrix = distanceCalculator.findDistance(coordinateList);
      this.isGraphCreated = true;
    }
    List<Integer> shortestPathIndices;
    try {
      shortestPathIndices = tspAlgorithm.findShortestPath(distanceMatrix);
    } catch (InvalidDistanceMatrixException e) {
      return null;
    }
    List<TravellingSalesmanTask> minimumPathOrder = new ArrayList<>();

    // Skipping 0th and last index as they will be the home coordinates.
    for (int i = 1; i < shortestPathIndices.size() - 1; i++) {
      minimumPathOrder.add(taskList.get(shortestPathIndices.get(i) - 1));
    }
    return minimumPathOrder;
  }
}