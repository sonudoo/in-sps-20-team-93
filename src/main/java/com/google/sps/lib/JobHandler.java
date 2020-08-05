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
public class JobHandler {
  /**
   * Adds the Job to Datastore.
   */
  public IResponse addJobToDataStore(HttpServletRequest request, DatastoreService datastore, SubmitJobValidator requestValidator) {
    
    ValidationStatus validationStatus = requestValidator.validate(request);
    if(validationStatus.getStatus() == ValidationStatusCode.VALIDATIONFAILURE) {
      return new BadRequestErrorResponse(validationStatus.getMessage());
    }
    
    String nName = request.getParameter(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME);
    String nPhone = request.getParameter(DataStoreEntityParams.ENTITY_PHONE_PROPERTY_NAME);
    String nDate = request.getParameter(DataStoreEntityParams.ENTITY_DATE_PROPERTY_NAME);
    float nStartLat = Float.parseFloat(request.getParameter(DataStoreEntityParams.ENTITY_START_LAT_PROPERTY_NAME));
    float nStartLong = Float.parseFloat(request.getParameter(DataStoreEntityParams.ENTITY_START_LONG_PROPERTY_NAME));
    float nEndLat = Float.parseFloat(request.getParameter(DataStoreEntityParams.ENTITY_END_LAT_PROPERTY_NAME));
    float nEndLong = Float.parseFloat(request.getParameter(DataStoreEntityParams.ENTITY_END_LONG_PROPERTY_NAME));

    String nJobId = UUID.randomUUID().toString();

    Entity nJob = new Entity(DataStoreEntityParams.ENTITY_NAME);
    nJob.setProperty(DataStoreEntityParams.ENTITY_JOBID_PROPERTY_NAME, nJobId);
    nJob.setProperty(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME, nName);
    nJob.setProperty(DataStoreEntityParams.ENTITY_PHONE_PROPERTY_NAME, nPhone);
    nJob.setProperty(DataStoreEntityParams.ENTITY_DATE_PROPERTY_NAME, nDate);
    nJob.setProperty(DataStoreEntityParams.ENTITY_START_LAT_PROPERTY_NAME, nStartLat);
    nJob.setProperty(DataStoreEntityParams.ENTITY_START_LONG_PROPERTY_NAME, nStartLong);
    nJob.setProperty(DataStoreEntityParams.ENTITY_END_LAT_PROPERTY_NAME, nEndLat);
    nJob.setProperty(DataStoreEntityParams.ENTITY_END_LONG_PROPERTY_NAME, nEndLong);

    datastore.put(nJob);
    return new SuccessResponse();
  }
}