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

import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)

public class EuclideanDistanceCalculatorTest {

  @Test
  public void findDistanceTest() {

    List<Task> list = new ArrayList<>(Arrays.asList(
      new Task("1", 67.09, 23.65, 98.7, 22.3),
      new Task("2", 68.34, 24.5, 99.7, 23.65),
      new Task("3", 68.38, 26.4, 98.6, 23.67)
    ));
    IDistanceCalculator distanceCalculator = new EuclideanDistanceCalculator();
    double[][] expectedDistanceMatrix = { 
      {0, 1.600781059, 1.293870164}, 
      {1.600781059, 0, 1.100727032}, 
      {1.293870164, 1.100727032, 0} 
    };

    double[][] distanceMatrix = distanceCalculator.findDistance(list);

    for (int i = 0; i < expectedDistanceMatrix.length; i++) {
      assertArrayEquals(expectedDistanceMatrix[i], distanceMatrix[i], /* delta = */ 0.00000001);
    }
  }
}