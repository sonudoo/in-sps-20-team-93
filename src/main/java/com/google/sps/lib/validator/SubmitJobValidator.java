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

package com.google.sps.lib.validator;

import java.util.regex.Pattern;
import java.lang.NumberFormatException;
import javax.servlet.http.HttpServletRequest;
import com.google.sps.lib.handler.DatastoreJobEntityParams;

/**
 * The validator for submit job requests.
 */
public class SubmitJobValidator {
  
  private boolean isNumericDouble(String value) {
    try {
      Double.parseDouble(value);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private boolean isNumericLong(String value) {
    try {
      Long.parseLong(value);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private boolean isNullOrEmpty(String value) {
    if (value == null || value.length() == 0) {
      return false;
    }
    return true;
  }

  private boolean isValidLatitude(double value) {
    if (value >= -90 && value <= 90) {
      return true;
    }
    return false;
  }

  private boolean isValidLongitude(double value) {
    if (value >= -180 && value <= 180) {
      return true;
    }
    return false;
  }

  /**
   * Checks for a valid submit job request.
   */
  public ValidationStatus validate(HttpServletRequest request) {
    String nName = request.getParameter(DatastoreJobEntityParams.ENTITY_USER_PROPERTY_NAME);
    if (!isNullOrEmpty(nName)) {
      return ValidationStatus.create(ValidationStatusCode.FAILURE, "Name should not be empty");
    }

    String nPhone = request.getParameter(DatastoreJobEntityParams.ENTITY_PHONE_PROPERTY_NAME);
    if (!isNullOrEmpty(nPhone) || !isNumericLong(nPhone)) {
      return ValidationStatus.create(ValidationStatusCode.FAILURE, "Phone is empty or incorrect");
    }

    String nLat = request.getParameter(DatastoreJobEntityParams.ENTITY_LAT_PROPERTY_NAME);
    if (!isNullOrEmpty(nLat) || !isNumericDouble(nLat) || !isValidLatitude(Double.parseDouble(nLat))) {
      return ValidationStatus.create(ValidationStatusCode.FAILURE, "Latitude should be a valid number");
    }

    String nLong = request.getParameter(DatastoreJobEntityParams.ENTITY_LONG_PROPERTY_NAME);
    if (!isNullOrEmpty(nLong) || !isNumericDouble(nLong) || !isValidLongitude(Double.parseDouble(nLong))) {
      return ValidationStatus.create(ValidationStatusCode.FAILURE, "Longitude should be a valid number");
    }

    return ValidationStatus.create(ValidationStatusCode.SUCCESS, "");
  }
}