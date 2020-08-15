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

package com.google.sps.lib.algorithm.graph;

/**
 * The wrapper for task Id, latitudes and longitudes of a delivery location.
 */
public class TravellingSalesmanTask {
  private final int taskId;
  private final double latitudes;
  private final double longitudes;

  public TravellingSalesmanTask(final int taskId, final double latitudes, final double longitudes) {
    this.taskId = taskId;
    this.latitudes = latitudes;
    this.longitudes = longitudes;
  }

  public int getTaskId() {
    return taskId;
  }

  public double getLatitudes() {
    return latitudes;
  }

  public double getLongitudes() {
    return longitudes;
  }
}