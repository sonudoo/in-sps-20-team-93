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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TspDynamicProgrammingAlgorithmTest {
  @Test
  public void findShortestPathTest_trivialCase() throws InvalidDistanceMatrixException {
    double[][] distanceMatrix = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
    ITspAlgorithm tspAlgorithm = new TspDynamicProgrammingAlgorithm();
    List<Integer> expectedPath = Arrays.asList(0, 1, 2, 0);

    List<Integer> path = tspAlgorithm.findShortestPath(distanceMatrix);

    assertEquals(expectedPath, path);
  }

  @Test
  public void findShortestPathTest_orderedPath() throws InvalidDistanceMatrixException {
    double[][] distanceMatrix = { { 0, 20, 42, 25 }, { 20, 0, 30, 34 }, { 42, 30, 0, 10 }, { 25, 34, 10, 0 } };
    ITspAlgorithm tspAlgorithm = new TspDynamicProgrammingAlgorithm();
    List<Integer> expectedPath = Arrays.asList(0, 1, 2, 3, 0);

    List<Integer> path = tspAlgorithm.findShortestPath(distanceMatrix);

    assertEquals(expectedPath, path);
  }

  @Test
  public void findShortestPathTest_randomOrderedPath() throws InvalidDistanceMatrixException {

    double[][] distanceMatrix = { { 0, 5000, 5000 }, { 1000, 0, 5000 }, { 1000, 1000, 0 } };
    ITspAlgorithm tspAlgorithm = new TspDynamicProgrammingAlgorithm();
    List<Integer> expectedPath = Arrays.asList(0, 2, 1, 0);

    List<Integer> path = tspAlgorithm.findShortestPath(distanceMatrix);

    assertEquals(expectedPath, path);
  }

  @Test
  public void findShortestPathTest_invalidRequest_negativeDistance() {

    double[][] distanceMatrix = { { 0, -20, -42, -25 }, { -20, 0, -30, -34 }, { -42, -30, 0, -10 },
        { -25, -34, 10, 0 } };
    ITspAlgorithm tspAlgorithm = new TspDynamicProgrammingAlgorithm();

    try {
      tspAlgorithm.findShortestPath(distanceMatrix);
      throw new RuntimeException("Expected InvalidDistanceMatrixException");
    } catch (InvalidDistanceMatrixException e) {
      // Test passes
    }
  }

  @Test
  public void findShortestPathTest_largeInput_randomOrderedPath() throws InvalidDistanceMatrixException {
    double[][] distanceMatrix = { { 0, 20, 30, 40, 50, 60, 70, 80, 90, 100 }, { 40, 0, 84, 59, 62, 64, 2, 80, 52, 7 },
        { 100, 6, 0, 4, 96, 43, 2, 81, 4, 30 }, { 10, 15, 6, 0, 88, 58, 2, 90, 5, 47 },
        { 2, 19, 90, 2, 0, 63, 100, 91, 56, 20 }, { 26, 18, 10, 7, 14, 0, 90, 1, 80, 6 },
        { 48, 76, 8, 9, 16, 49, 0, 2, 7, 18 }, { 4, 6, 8, 10, 15, 6, 1, 0, 8, 56 }, { 3, 90, 2, 1, 2, 3, 22, 6, 0, 40 },
        { 10, 1, 6, 31, 23, 46, 44, 3, 48, 0 } };
    ITspAlgorithm tspAlgorithm = new TspDynamicProgrammingAlgorithm();
    List<Integer> expectedPath = Arrays.asList(0, 1, 6, 7, 5, 9, 2, 3, 8, 4, 0);

    List<Integer> path = tspAlgorithm.findShortestPath(distanceMatrix);

    assertEquals(expectedPath, path);
  }

  @Test
  public void findShortestPathTest_invalidRequest_largeInput_negativeDistance() {
    double[][] distanceMatrix = { { 0, 20, 30, 40, 50, 60, 70, 80, 90, -100 }, { 40, 0, -84, 59, 62, 64, 2, 80, 52, 7 },
        { 100, 6, 0, 4, 96, 43, 2, -200, 4, -30 }, { 10, 15, 6, 0, 88, 58, 2, 90, 5, 47 },
        { 2, 19, -90, 2, 0, 63, -100, 91, 56, 20 }, { 26, 18, 10, -10, 14, 0, 90, 1, 80, 6 },
        { 48, 76, 8, 9, 16, 49, 0, 2, 7, -18 }, { 4, 6, -8, 10, 15, 6, 1, 0, 8, 56 },
        { 3, 90, 2, 1, 2, 3, 22, 6, 0, 40 }, { 10, 1, 6, -31, 23, 46, -44, 3, 48, 0 } };
    ITspAlgorithm tspAlgorithm = new TspDynamicProgrammingAlgorithm();

    try {
      tspAlgorithm.findShortestPath(distanceMatrix);
      throw new RuntimeException("Expected InvalidDistanceMatrixException");
    } catch (InvalidDistanceMatrixException e) {
      // Test passes
    }
  }

  @Test
  public void findShortestPathTest_largeInput_sortedOrderedPath() throws InvalidDistanceMatrixException {
    double[][] distanceMatrix = { { 0, 1, 40, 50, 67.4, 70, 90, 99.5, 67, 120, 32.7, 130 },
        { 40, 0, 0, 50, 87, 45.65, 120.33, 34.43, 12.34, 110, 87.6, 100 },
        { 80, 100, 0, 2, 45, 180, 25, 69, 24, 56, 34.44, 19 }, { 56.7, 60, 10, 0, 2, 4, 7, 56, 78.1, 34.4, 90, 1 },
        { 12, 35, 11, 67, 0, 1, 30.9, 12.4, 11, 35, 60, 8 }, { 23, 2, 2.5, 20.55, 10.88, 0, 2, 57, 89, 75, 12.4, 11 },
        { 48, 76, 8, 9, 16, 49, 0, 4.2, 1, 18, 78, 90 }, { 23, 56, 14.5, 26.7, 29.3, 26, 29, 0, 1, 46, 10, 5 },
        { 2, 45, 65, 78, 99, 1, 62, 100, 0, 2, 2, 9.5 }, { 7.1, 3, 12, 88, 55, 66.3, 12, 100, 100, 0, 2, 9 },
        { 43, 12, 56, 22.3, 12, 56, 89, 45, 12, 67, 0, 1 }, { 88, 3, 0, 7, 56, 23, 88, 90, 100, 102.34, 23, 0 } };
    ITspAlgorithm tspAlgorithm = new TspDynamicProgrammingAlgorithm();
    List<Integer> expectedPath = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0);

    List<Integer> path = tspAlgorithm.findShortestPath(distanceMatrix);

    assertEquals(expectedPath, path);
  }

  @Test
  public void findShortestPathTest_largeInput_negativeDistance() throws InvalidDistanceMatrixException {
    double[][] distanceMatrix = { { 0, 1, 40, 50, 67.4, 70, 90, 99.5, 67, 120, 32.7, 130 },
        { 40, 0, 100, 50, 87, 45.65, 120.33, 34.43, 12.34, 110, 87.6, 100 },
        { 80, 100, 0, 2, 45, 180, 25, 69, 24, 56, 34.44, 19 }, { 56.7, 60, 10, 0, 200, 4, 7, 56, 78.1, 34.4, 90, -1 },
        { 12, 35, 11, 67, 0, 1, 30.9, 12.4, 11, 35, 60, 8 }, { 23, 20, 2.5, 20.55, 10.88, 0, -2, 57, 89, 75, 12.4, 11 },
        { 48, 76, 8, 9, 16, 49, 0, 4.2, 1, 18, 78, 90 }, { 23, 56, 14.5, 26.7, 29.3, 26, 29, 0, 1, 46, 10, 5 },
        { 2, 45, 65, 78, 99, 1, 62, 100, 0, 2, 2, 9.5 }, { -7.1, 3, 12, 88, 55, 66.3, 12, 100, 100, 0, -2, 9 },
        { 43, 12, 56, 22.3, 12, 56, 89, 45, 12, 67, 0, 100 }, { 88, 3, 0, 7, 56, 23, 88, 90, 100, 102.34, 23, 0 } };
    ITspAlgorithm tspAlgorithm = new TspDynamicProgrammingAlgorithm();
    List<Integer> expectedPath = Arrays.asList(0, 1, 5, 6, 7, 11, 2, 3, 8, 9, 10, 4, 0);

    List<Integer> path = tspAlgorithm.findShortestPath(distanceMatrix);

    assertEquals(expectedPath, path);
  }
}