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

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import com.google.sps.lib.validator.SubmitJobValidator;
import com.google.sps.lib.validator.ValidationStatus;
import com.google.sps.lib.validator.ValidationStatusCode;

/**
 * Handles new job submission.
 */
public class SubmitJobHandler implements IRequestHandler {

  private final SubmitJobValidator requestValidator;
  private final DatastoreWrapper datastoreWrapper;
  private final HttpServletRequest request;

  /**
   * The constructor is purposely kept package-private. Please use
   * {@link JobHandlerFactory} to get an instance of this class.
   */
  SubmitJobHandler(final SubmitJobValidator submitJobValidator, final DatastoreWrapper datastoreWrapper,
      final HttpServletRequest request) {
    this.requestValidator = submitJobValidator;
    this.datastoreWrapper = datastoreWrapper;
    this.request = request;
  }

  /**
   * Validates the requests and delegates the job insertion to
   * {@link DatastoreWrapper}.
   */
  @Override
  public HandlerResponse getResponse() {
    final ValidationStatus validationStatus = requestValidator.validate(request);
    if (validationStatus.getStatus() == ValidationStatusCode.FAILURE) {
      return new BadRequestErrorResponse(validationStatus.getMessage());
    }
    final String nName = request.getParameter(DatastoreJobEntityParams.ENTITY_USER_PROPERTY_NAME);
    final String nPhone = request.getParameter(DatastoreJobEntityParams.ENTITY_PHONE_PROPERTY_NAME);
    final double nLat = Double.parseDouble(request.getParameter(DatastoreJobEntityParams.ENTITY_LAT_PROPERTY_NAME));
    final double nLong = Double.parseDouble(request.getParameter(DatastoreJobEntityParams.ENTITY_LONG_PROPERTY_NAME));
    datastoreWrapper.addJob(new DatastoreJob(UUID.randomUUID().toString(), nName, nPhone, nLat, nLong));
    return new SuccessResponse();
  }
}