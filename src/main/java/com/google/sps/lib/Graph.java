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
  private final int listSize;
  private boolean isGraphCreated;
  private double[][] distanceMatrix;

  public Graph(List<Task> taskList) {
    this.taskList = taskList;
    this.listSize = taskList.size();
    this.isGraphCreated = false;
  }

  /**
   * Getter for distanceMatrix.
   */
  public double[][] getGraph() {
    if (!this.isGraphCreated) {
      this.distanceMatrix = createGraph();
      this.isGraphCreated = true;
    }
    return this.distanceMatrix;
  }

  private double[][] createGraph() {
    double[][] initialDistanceMatrix = new double[listSize][listSize];

    for (int i = 1; i <= initialDistanceMatrix.length; i++) {
      for (int j = 1; j <= initialDistanceMatrix[1].length; j++) {
        if (i != j) {
          initialDistanceMatrix[i - 1][j - 1] = findDistance(i, j);
        }
      }
    }
    return initialDistanceMatrix;
  }

  private double findDistance(int i, int j) {
    // TODO[samii9914]: Try to use Map API to calculate Distance
    double xCoordinate = this.taskList.get(i - 1).getStartLatitude() - this.taskList.get(j - 1).getStartLatitude();
    double yCoordinate = this.taskList.get(i - 1).getStartLongitude() - this.taskList.get(j - 1).getStartLongitude();
    return Math.sqrt(((xCoordinate * xCoordinate) + (yCoordinate * yCoordinate)));
  }
}