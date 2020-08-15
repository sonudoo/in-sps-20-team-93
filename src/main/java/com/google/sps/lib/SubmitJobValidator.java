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

import java.util.regex.Pattern;
import java.lang.NumberFormatException;
import javax.servlet.http.HttpServletRequest;

/**
 * The validator for submit job requests.
 */
public class SubmitJobValidator {
  /**
   * Checks that the string contains only alphabets (at least one).
   */
  private boolean isAlphabetic(String value) {
    return Pattern.matches("[a-zA-Z]+", value);
  }

  /**
   * Checks for double numeric value.
   */
  private boolean isNumericDouble(String value) {
    try {
      Double.parseDouble(value);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Checks for long numeric value.
   */
  private boolean isNumericLong(String value) {
    try {
      Long.parseLong(value);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Checks for null or empty string.
   */
  private boolean isNullOrEmpty(String value) {
    if (value == null || value.length() == 0) {
      return false;
    }
    return true;
  }

  /**
   * Checks for a valid latitude valu.
   */
  private boolean isValidLatitude(double value) {
    if (value >= -90 && value <= 90) {
      return true;
    }
    return false;
  }

  /**
   * Checks for a valid longitude value.
   */
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
    String nName = request.getParameter(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME);
    if (!isNullOrEmpty(nName) || !isAlphabetic(nName)) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE, "Name should not be empty");
    }

    String nPhone = request.getParameter(DataStoreEntityParams.ENTITY_PHONE_PROPERTY_NAME);
    if (!isNullOrEmpty(nPhone) || !isNumericLong(nPhone)) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE, "Phone is empty or incorrect");
    }

    String startLat = request.getParameter(DataStoreEntityParams.ENTITY_START_LAT_PROPERTY_NAME);
    if (!isNullOrEmpty(startLat) || !isNumericDouble(startLat) || !isValidLatitude(Double.parseDouble(startLat))) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE, "Start Latitude should be a valid number");
    }

    String startLong = request.getParameter(DataStoreEntityParams.ENTITY_START_LONG_PROPERTY_NAME);
    if (!isNullOrEmpty(startLong) || !isNumericDouble(startLong) || !isValidLongitude(Double.parseDouble(startLong))) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE,
          "Start Longitude should be a valid number");
    }

    String endLat = request.getParameter(DataStoreEntityParams.ENTITY_END_LAT_PROPERTY_NAME);
    if (!isNullOrEmpty(endLat) || !isNumericDouble(endLat) || !isValidLatitude(Double.parseDouble(endLat))) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE, "End Latitude should be a valid number");
    }

    String endLong = request.getParameter(DataStoreEntityParams.ENTITY_END_LONG_PROPERTY_NAME);
    if (!isNullOrEmpty(endLong) || !isNumericDouble(endLong) || !isValidLongitude(Double.parseDouble(endLong))) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE, "End Longitude should be a valid number");
    }

    return ValidationStatus.create(ValidationStatusCode.VALIDATIONSUCCESS, "");
  }
}