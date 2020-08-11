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

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)
public class TSPDynamicProgrammingTest {
  @Test
  public void findShortestPathTest1() {

    double[][] distanceMatrix = { 
      { 0, 0, 0 }, 
      { 0, 0, 0 }, 
      { 0, 0, 0 } 
    };
    ITSPAlgorithm tspAlgorithm = new TSPDynamicProgrammingAlgorithm();
    List<String> expectedPath = Arrays.asList("1", "2", "3");

    List<String> path = tspAlgorithm.findShortestPath(distanceMatrix);

    assertEquals(expectedPath, path);

  }

  @Test
  public void findShortestPathTest2() {

    double[][] distanceMatrix = { 
      { 0, 20, 42, 25 }, 
      { 20, 0, 30, 34 }, 
      { 42, 30, 0, 10 }, 
      { 25, 34, 10, 0 } 
    };
    ITSPAlgorithm tspAlgorithm = new TSPDynamicProgrammingAlgorithm();
    List<String> expectedPath = Arrays.asList("1", "2", "3", "4");

    List<String> path = tspAlgorithm.findShortestPath(distanceMatrix);

    assertEquals(expectedPath, path);

  }

  @Test
  public void findShortestPathTest3() {

    double[][] distanceMatrix = { 
      { 0, 5000, 5000 }, 
      { 1000, 0, 5000 }, 
      { 1000, 1000, 0 } 
    };
    ITSPAlgorithm tspAlgorithm = new TSPDynamicProgrammingAlgorithm();
    List<String> expectedPath = Arrays.asList("1", "3", "2");

    List<String> path = tspAlgorithm.findShortestPath(distanceMatrix);

    assertEquals(expectedPath, path);

  }

  @Test
  public void findShortestPathTest4() {

    double[][] distanceMatrix = { 
      { 0, -20, -42, -25 }, 
      { -20, 0, -30, -34 }, 
      { -42, -30, 0, -10 },
      { -25, -34, 10, 0 } 
    };
    ITSPAlgorithm tspAlgorithm = new TSPDynamicProgrammingAlgorithm();
    List<String> expectedPath = null;

    List<String> path = tspAlgorithm.findShortestPath(distanceMatrix);

    assertEquals(expectedPath, path);
  }
}