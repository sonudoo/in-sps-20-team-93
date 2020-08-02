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

import javax.servlet.http.HttpServletRequest;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import java.util.UUID;

/**
 * Contains essential functions for a new Job.
 */
public class Job {

  private static final String ENTITY_JOBID_PROPERTY_NAME = "JobId";
  private static final String ENTITY_USER_PROPERTY_NAME = "Name";
  private static final String ENTITY_PHONE_PROPERTY_NAME = "Phone";
  private static final String ENTITY_DATE_PROPERTY_NAME = "Date";
  private static final String ENTITY_START_LAT_PROPERTY_NAME = "StartLatitude";
  private static final String ENTITY_START_LONG_PROPERTY_NAME = "StartLongitude";
  private static final String ENTITY_END_LAT_PROPERTY_NAME = "EndLatitude";
  private static final String ENTITY_END_LONG_PROPERTY_NAME = "EndLongitude";
  private static final String ENTITY_NAME = "Job";

  /**
   * Adds the Job to Datastore.
   */
  public IResponse addJobToDataStore(HttpServletRequest request, DatastoreService datastore) {
    String nName = request.getParameter(ENTITY_USER_PROPERTY_NAME);

    // TODO: Inject a validator.

    if(nName == null || nName.isEmpty()){
      return new BadRequestErrorResponse("Name should not be empty");
    }
    String nPhone = request.getParameter(ENTITY_PHONE_PROPERTY_NAME);

    // TODO(shradha-khapra): Add validations for others as well.

    String nDate = request.getParameter(ENTITY_DATE_PROPERTY_NAME);
    float nStartLat = Float.parseFloat(request.getParameter(ENTITY_START_LAT_PROPERTY_NAME));
    float nStartLong = Float.parseFloat(request.getParameter(ENTITY_START_LONG_PROPERTY_NAME));
    float nEndLat = Float.parseFloat(request.getParameter(ENTITY_END_LAT_PROPERTY_NAME));
    float nEndLong = Float.parseFloat(request.getParameter(ENTITY_END_LONG_PROPERTY_NAME));
    String nJobId = UUID.randomUUID().toString();

    Entity nJob = new Entity(ENTITY_NAME);
    nJob.setProperty(ENTITY_JOBID_PROPERTY_NAME, nJobId);
    nJob.setProperty(ENTITY_USER_PROPERTY_NAME, nName);
    nJob.setProperty(ENTITY_PHONE_PROPERTY_NAME, nPhone);
    nJob.setProperty(ENTITY_DATE_PROPERTY_NAME, nDate);
    nJob.setProperty(ENTITY_START_LAT_PROPERTY_NAME, nStartLat);
    nJob.setProperty(ENTITY_START_LONG_PROPERTY_NAME, nStartLong);
    nJob.setProperty(ENTITY_END_LAT_PROPERTY_NAME, nEndLat);
    nJob.setProperty(ENTITY_END_LONG_PROPERTY_NAME, nEndLong);

    datastore.put(nJob);
    return new SuccessResponse();
  }
}