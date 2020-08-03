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

/**
 * Contains required parameters for a particular Task and sets its value. 
 */
public class Task {
  private final String taskId;
  private final double startLatitudes;
  private final double endLatitudes;
  private final double startLongitudes;
  private final double endLongitudes;

  public Task(String taskId, double startLatitudes, double endLatitudes, double startLongitudes, double endLongitudes) {
    this.taskId = taskId;
    this.startLatitudes = startLatitudes;
    this.startLongitudes = startLongitudes;
    this.endLatitudes = endLatitudes;
    this.endLongitudes = endLongitudes;
  }

  /**
   * Getter for taskId.
   */
  public String getTaskId() {
    return this.taskId;
  }

  /**
   * Getter for startLatitudes.
   */
  public double getStartLatitude() {
    return this.startLatitudes;
  }

  /**
   * Getter for startLongitudes.
   */
  public double getStartLongitude() {
    return this.startLongitudes;
  }

  /**
   * Getter for endLatitudes.
   */
  public double getEndLatitude() {
    return this.endLatitudes;
  }

 /**
  * Getter for endLongitudes.
  */
  public double getEndLongitude() {
    return this.endLongitudes;
  }
}