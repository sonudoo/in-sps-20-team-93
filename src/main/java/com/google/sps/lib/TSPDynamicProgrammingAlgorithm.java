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
 * Implements TSP algorithm in exponential time using dynamic programming.
 */
public class TSPDynamicProgrammingAlgorithm implements ITSPAlgorithm {

  @Override
  public List<String> findShortestPath(double[][] distanceMatrix) {
    Integer[][] path = new Integer[(1 << distanceMatrix.length)][distanceMatrix[0].length];
    double[][] dp = new double[(1 << distanceMatrix.length)][distanceMatrix[0].length];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }
    
    if (!checkNegativeDistance(distanceMatrix)) {
      tsp(distanceMatrix, 1, 0, path, dp);
      return getTour(path);
    }
    return null;
  }

  private double tsp(double[][] distanceMatrix, int stage, int pos, Integer[][] path, double[][] dp) {
    if (stage == (1 << distanceMatrix.length) - 1) {
      return distanceMatrix[pos][0];
    }
    if (dp[stage][pos] != Integer.MAX_VALUE) {
      return dp[stage][pos];
    }

    double ans = Double.MAX_VALUE;
    int index = -1;
    for (int city = 0; city < distanceMatrix.length; city++) {
      if ((stage & (1 << city)) == 0) {
        double newDistance = distanceMatrix[pos][city] + tsp(distanceMatrix, (stage | (1 << city)), city, path, dp);
        if (newDistance < ans) {
          ans = newDistance;
          index = city;
        }
      }
    }

    path[stage][pos] = index;
    dp[stage][pos] = ans;
    return ans;
  }

  private List<String> getTour(Integer[][] path) {
    List<String> tour = new ArrayList<>();
    int state = 1;
    int index = 0;

    while (true) {
      tour.add(String.valueOf(index+1));
      Integer nextIndex = path[state][index];
      if (nextIndex == null) {
        break;
      }
      state = state | (1 << nextIndex);
      index = nextIndex;
    }
    return tour;
  }

  private boolean checkNegativeDistance(double[][] distanceMatrix) {
    double[][] dist = distanceMatrix;

    for (int k = 0; k < dist.length; k++) {
      for (int i = 0; i < dist.length; i++) {
        for (int j = 0; j < dist.length; j++) {
          if (dist[i][k] + dist[k][j] < dist[i][j])
            dist[i][j] = dist[i][k] + dist[k][j];
        }
      }
    }

    for (int i = 0; i < dist.length; i++) {
      if (dist[i][i] < 0) {
        return true;
      }
    }
    return false;
  }
}