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

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)

public class CalculateDistanceTest {

  @Test
  public void calculateDistanceUsingEuclideanTest() {
    List<Task> list = new ArrayList<>();
    Task task1 = new Task("1", 67.09, 23.65, 98.7, 22.3);
    Task task2 = new Task("2", 68.34, 24.5, 99.7, 23.65);
    Task task3 = new Task("3", 68.38, 26.4, 98.6, 23.67);
    list.add(task1);
    list.add(task2);
    list.add(task3);

    ICalculateDistance distanceActual = new calculateDistanceUsingEuclidean();
    double distance = distanceActual.findDistance(list, 2, 3);
    double testDistance = 1.100727032;
    assertEquals(testDistance, distance, 0.00000001);
  }
}