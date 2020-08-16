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

package com.google.sps.lib.handler;

/**
 * A job object stored in the datastore.
 */
class DatastoreJob {
  private final String jobId;
  private final String name;
  private final String phone;
  private final double latitudes;
  private final double longitudes;

  DatastoreJob(final String jobId, final String name, final String phone, final double latitudes,
      final double longitudes) {
    this.jobId = jobId;
    this.name = name;
    this.phone = phone;
    this.latitudes = latitudes;
    this.longitudes = longitudes;
  }

  public double getLongitudes() {
    return longitudes;
  }

  public double getLatitudes() {
    return latitudes;
  }

  public String getPhone() {
    return phone;
  }

  public String getName() {
    return name;
  }

  public String getJobId() {
    return jobId;
  }

}