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

/**
 * Contains essential functions for a new Job.
 */
public class JobHandler {
  /**
   * Adds the Job to Datastore.
   */
  public IResponse addJobToDataStore(final HttpServletRequest request, final DatabaseWrapper databaseWrapper,
      final SubmitJobValidator requestValidator) {

    final ValidationStatus validationStatus = requestValidator.validate(request);
    if (validationStatus.getStatus() == ValidationStatusCode.VALIDATIONFAILURE) {
      return new BadRequestErrorResponse(validationStatus.getMessage());
    }
    final String nName = request.getParameter(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME);
    final String nPhone = request.getParameter(DataStoreEntityParams.ENTITY_PHONE_PROPERTY_NAME);
    final double nStartLat = Double
        .parseDouble(request.getParameter(DataStoreEntityParams.ENTITY_START_LAT_PROPERTY_NAME));
    final double nStartLong = Double
        .parseDouble(request.getParameter(DataStoreEntityParams.ENTITY_START_LONG_PROPERTY_NAME));
    final double nEndLat = Double.parseDouble(request.getParameter(DataStoreEntityParams.ENTITY_END_LAT_PROPERTY_NAME));
    final double nEndLong = Double
        .parseDouble(request.getParameter(DataStoreEntityParams.ENTITY_END_LONG_PROPERTY_NAME));

    databaseWrapper.addJob(nName, nPhone, nStartLat, nStartLong, nEndLat, nEndLong);
    return new SuccessResponse();
  }
}