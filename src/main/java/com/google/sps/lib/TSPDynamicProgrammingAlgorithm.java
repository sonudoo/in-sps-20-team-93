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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implements TSP algorithm in exponential time using dynamic programming. From
 * the source location, the salesman has to travel to all the locations only
 * once and then come back to the source position. So, from a particular
 * location, check that going to which path will give the minimum distance out
 * of all the paths that can be visited from that particular location and return
 * that path. Continue like this until all the locations are visited.
 */
public class TSPDynamicProgrammingAlgorithm implements ITSPAlgorithm {
  public static final String SOURCE_LOCATION_TASK_ID = "1";

  @Override
  public List<String> findShortestPath(double[][] distanceMatrix) {
    Integer[][] path = new Integer[(1 << distanceMatrix.length)][distanceMatrix[0].length];
    double[][] minCost = new double[(1 << distanceMatrix.length)][distanceMatrix[0].length];
    for (int i = 0; i < minCost.length; i++) {
      Arrays.fill(minCost[i], Double.MAX_VALUE);
    }

    if (!checkNegativeDistance(distanceMatrix)) {
      shortestPathDistanceCalculator(distanceMatrix, 1, 0, path, minCost);
      return getShortestPath(path);
    }
    return null;
  }

  /**
   * Calculates the distance of the shortest path to be followed.
   * 
   * @param distanceMatrix
   * @param state           Represents state that what all locations have been
   *                        visited.
   * @param currentLocation Current location at which we are present.
   * @param path            Represents next location to be visited from current
   *                        location at a particular state.
   * @param minCost
   */
  private double shortestPathDistanceCalculator(double[][] distanceMatrix, int state, int currentLocation,
      Integer[][] path, double[][] minCost) {

    // If all the locations have been visited then
    // return the distance from the current location
    // to starting location(source).
    if (state == (1 << distanceMatrix.length) - 1) {
      return distanceMatrix[currentLocation][0];
    }
    if (minCost[state][currentLocation] != Double.MAX_VALUE) {
      return minCost[state][currentLocation];
    }

    double minimumDistance = Double.MAX_VALUE;
    int nextLocation = -1;

    // Finds which location should be visited next to the current
    // location that will give the shortest distance path from all the locations
    // which are not visited yet.
    for (int location = 0; location < distanceMatrix.length; location++) {

      // Checks if the location is visited or not.
      if ((state & (1 << location)) == 0) {
        double newDistance = distanceMatrix[currentLocation][location]
            + shortestPathDistanceCalculator(distanceMatrix, (state | (1 << location)), location, path, minCost);
        if (newDistance < minimumDistance) {
          minimumDistance = newDistance;
          nextLocation = location;
        }
      }
    }

    // Storing next location to be visited from the current
    // location at a particular state.
    path[state][currentLocation] = nextLocation;

    // Memoization
    minCost[state][currentLocation] = minimumDistance;
    return minimumDistance;
  }

  /**
   * Gets the path to be followed giving the minimum distance.
   * 
   * @param path
   */
  private List<String> getShortestPath(Integer[][] path) {
    List<String> shortestDistancePath = new ArrayList<>();

    // Represents the initial state when only starting location is visited.
    int state = 1;

    int currentLocationTaskId = Integer.valueOf(SOURCE_LOCATION_TASK_ID);

    // Gets the next location to be visited from the path array.
    // If the next location exists then update the state.
    // Current location will be updated to next location to be visited.
    while (true) {
      shortestDistancePath.add(String.valueOf(currentLocationTaskId));
      Integer nextLocationTaskId = path[state][currentLocationTaskId - 1];
      if (nextLocationTaskId == null) {
        break;
      }
      state = state | (1 << nextLocationTaskId);
      currentLocationTaskId = nextLocationTaskId + 1;
    }
    shortestDistancePath.add(SOURCE_LOCATION_TASK_ID);

    return shortestDistancePath;
  }

  /**
   * Checks the negative distance cycle using floyd warshall algorithm.
   * 
   * @param distanceMatrix
   */
  private boolean checkNegativeDistance(double[][] distanceMatrix) {

    // distance[][] is the matrix that will finally have the shortest distances
    // between every pair of vertices.
    double[][] distance = distanceMatrix;

    // Add all vertices one by one to the set of intermediate vertices.
    for (int k = 0; k < distance.length; k++) {

      // Pick all vertices as source one by one.
      for (int i = 0; i < distance.length; i++) {

        // Pick all vertices as destination for the above picked source.
        for (int j = 0; j < distance.length; j++) {

          // If vertex k is on the shortest path from i to j, then update the value of
          // distance[i][j]
          if (distance[i][k] + distance[k][j] < distance[i][j])
            distance[i][j] = distance[i][k] + distance[k][j];
        }
      }
    }

    // If distance of any vertex from itself becomes negative, then there is a
    // negative distance cycle.
    for (int i = 0; i < distance.length; i++) {
      if (distance[i][i] < 0) {
        return true;
      }
    }
    return false;
  }
}