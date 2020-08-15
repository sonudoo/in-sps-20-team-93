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
 * The distance calculator between every two nodes using Euclidean distance
 * method.
 */
public class EuclideanDistanceCalculator implements IDistanceCalculator {

  @Override
  public double[][] findDistance(final List<Task> taskList) {

    final double[][] initialDistanceMatrix = new double[taskList.size()][taskList.size()];

    for (int i = 1; i <= initialDistanceMatrix.length; i++) {
      for (int j = 1; j <= initialDistanceMatrix[1].length; j++) {
        if (i != j) {
          final double xCoordinate = taskList.get(i - 1).getStartLatitude() - taskList.get(j - 1).getStartLatitude();
          final double yCoordinate = taskList.get(i - 1).getStartLongitude() - taskList.get(j - 1).getStartLongitude();
          initialDistanceMatrix[i - 1][j - 1] = Math.sqrt(((xCoordinate * xCoordinate) + (yCoordinate * yCoordinate)));
        }
      }
    }
    return initialDistanceMatrix;
  }
}