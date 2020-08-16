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

package com.google.sps.lib.algorithm.tsp;

import java.util.List;

/**
 * Contains method to find shortest path using TSP Algorithm. All
 * implementations are required to implement a method tha returns a list of
 * indices, visiting which in order will result in minimum distance covered.
 */
public interface ITspAlgorithm {
  /**
   * Returns an order of index (as list) that can be visited in such a way that
   * all locations are visited and total distance travelled is minimized. The 0th
   * index is assumed to be the starting and the ending location.
   * 
   * 
   * @param distanceMatix The matrix representing distance between different
   *                      locations that are required to be visited.
   */
  List<Integer> findShortestPath(double[][] distanceMatrix) throws InvalidDistanceMatrixException;
}