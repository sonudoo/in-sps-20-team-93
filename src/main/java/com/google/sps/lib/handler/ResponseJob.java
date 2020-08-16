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
 * The job object in response. The class (and its methods) are implementation
 * details of the handler and must not be accessed from outside.
 */
class ResponseJob {
  private final String name;
  private final String phone;
  private final double latitude;
  private final double longitude;

  ResponseJob(final String name, final String phone, final double latitude, final double longitude) {
    this.name = name;
    this.phone = phone;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  String getName() {
    return name;
  }

  String getPhone() {
    return phone;
  }

  double getLatitude() {
    return latitude;
  }

  double getLongitude() {
    return longitude;
  }
}