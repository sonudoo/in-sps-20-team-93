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
  private final double latitude;
  private final double longitude;

  DatastoreJob(final String jobId, final String name, final String phone, final double latitude,
      final double longitude) {
    this.jobId = jobId;
    this.name = name;
    this.phone = phone;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitude() {
    return latitude;
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