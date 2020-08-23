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
 * Contains method for calculating distance between the locations. All
 * implementations of this interface are required to implement a method which
 * takes a list of coordinates and returns a matrix representing distance
 * between them.
 */
public interface IDistanceCalculator {
  /**
   * Returns a matrix of distance between all the coordinates.
   * 
   * @param coordinateList List of coordinates between which the distance needs to
   *                       be computed.
   */
  double[][] findDistance(List<Coordinate> coordinateList);
}