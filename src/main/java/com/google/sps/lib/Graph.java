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

package com.google.sps.lib;

import java.util.List;

/**
 * Creates a graph showing distance between the adjacent locations.
 */
public class Graph {
  private final List<Task> taskList;
  private boolean isGraphCreated;
  private double[][] distanceMatrix;
  IDistanceCalculator distanceCalculator;
  ITSPAlgorithm tspAlgorithm;

  public Graph(List<Task> taskList) {
    this.taskList = taskList;
    this.isGraphCreated = false;
    this.distanceCalculator = new EuclideanDistanceCalculator();
  }

  public List<String> getMinimumPath() {
    if (!this.isGraphCreated) {
      this.distanceMatrix = calculateDistance();
      this.isGraphCreated = true;
    }
    if(taskList.size() <= 15) {
      tspAlgorithm = new TSPDynamicProgrammingAlgorithm();
    } else {
      tspAlgorithm = new TSPGreedyAlgorithm();
    }
    return tspAlgorithm.findShortestPath(distanceMatrix);
  }

  private double[][] calculateDistance() {
    return distanceCalculator.findDistance(taskList);
  }
}