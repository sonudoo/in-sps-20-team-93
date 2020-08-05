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
 * Represents DataStore parameters for an Entity.
 */
public class DataStoreEntityParams {
  public static final String ENTITY_JOBID_PROPERTY_NAME = "JobId";
  public static final String ENTITY_USER_PROPERTY_NAME = "Name";
  public static final String ENTITY_PHONE_PROPERTY_NAME = "Phone";
  public static final String ENTITY_DATE_PROPERTY_NAME = "Date";
  public static final String ENTITY_START_LAT_PROPERTY_NAME = "StartLatitude";
  public static final String ENTITY_START_LONG_PROPERTY_NAME = "StartLongitude";
  public static final String ENTITY_END_LAT_PROPERTY_NAME = "EndLatitude";
  public static final String ENTITY_END_LONG_PROPERTY_NAME = "EndLongitude";
  public static final String ENTITY_NAME = "Job";

  private DataStoreEntityParams() {

  }    
}