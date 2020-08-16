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
 * Contains constants related to fields of a Job stored in database.
 */
public class DatastoreJobEntityParams {
  public static final String ENTITY_JOBID_PROPERTY_NAME = "JobId";
  public static final String ENTITY_USER_PROPERTY_NAME = "Name";
  public static final String ENTITY_PHONE_PROPERTY_NAME = "Phone";
  public static final String ENTITY_LAT_PROPERTY_NAME = "Latitude";
  public static final String ENTITY_LONG_PROPERTY_NAME = "Longitude";
  public static final String ENTITY_NAME = "Job";

  private DatastoreJobEntityParams() {
  }
}