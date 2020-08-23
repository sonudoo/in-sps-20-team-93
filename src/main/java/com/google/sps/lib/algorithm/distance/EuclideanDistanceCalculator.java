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

package com.google.sps.lib.algorithm.distance;

import java.util.List;

/**
 * The distance calculator between every two coordinates using Euclidean
 * distance method.
 */
public class EuclideanDistanceCalculator implements IDistanceCalculator {

  @Override
  public double[][] findDistance(final List<Coordinate> coordinateList) {
    final double[][] distanceMatrix = new double[coordinateList.size()][coordinateList.size()];
    for (int i = 0; i < distanceMatrix.length; i++) {
      for (int j = 0; j < distanceMatrix[0].length; j++) {
        final double xDistance = coordinateList.get(i).getLatutides() - coordinateList.get(j).getLatutides();
        final double yDistance = coordinateList.get(i).getLongitudes() - coordinateList.get(j).getLongitudes();
        distanceMatrix[i][j] = Math.sqrt(((xDistance * xDistance) + (yDistance * yDistance)));
      }
    }
    return distanceMatrix;
  }
}