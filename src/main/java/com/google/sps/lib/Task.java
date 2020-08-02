/**
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.google.sps.lib;

/**
 * Represents a Task.
 */
public class Task {
  final String TaskId;
  double StartLatitudes;
  double EndLatitudes;
  double StartLongitudes;
  double EndLongitudes;

  public Task(String TaskId, double StartLatitudes, double EndLatitudes, double StartLongitudes, double EndLongitudes) {
    this.TaskId = TaskId;
    this.StartLatitudes = StartLatitudes;
    this.StartLongitudes = StartLongitudes;
    this.EndLatitudes = EndLatitudes;
    this.EndLongitudes = EndLongitudes;
  }

  /**
   * getter for Task_Id
   */
  public String getTaskId() {
    return this.TaskId;
  }

  /**
   * getter for Start_Latitude
   */
  public double getStartLatitude() {
    return this.StartLatitudes;
  }

  /**
   * getter for Start_Longitude
   */
  public double getStartLongitude() {
    return this.StartLongitudes;
  }

  /**
   * getter for End_Latitudes
   */
  public double getEndLatitude() {
    return this.EndLatitudes;
  }

  /**
   * getter for End_Longitudes
   */
  public double getEndLongitude() {
    return this.EndLongitudes;
  }
}