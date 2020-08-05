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
 * Represents a submit job request validator.
 */
public class SubmitJobValidator {
  /**
   * Checks for strings with only alphabets.
   */
  private boolean isAlphabet(String value) {
    return Pattern.matches("[a-zA-Z]+", value);
  }

  /**
   * Checks for float numberic values.
   */  
  private boolean isNumericFloat(String value) {
    try {
      float fValue = Float.parseFloat(value);
      return true;
    }
    catch(NumberFormatException e) {
      return false;
    }
  }

  /**
   * Checks for integer numberic values.
   */
  private boolean isNumericInt(String value) {
    try {
      int fValue = Integer.parseInt(value);
      return true;
    }
    catch(NumberFormatException e) {
      return false;
    }
  }

  /**
   * Checks for null or empty values.
   */
  private boolean isNullOrEmpty(String value) {
    if(value == null || value.length() == 0) {
      return false;
    }
    return true;
  }

  /**
   * Checks for a valid latitude value.
   */
  private boolean isValidLatitude(float value) {
    if(value >= -90 && value <= 90) {
      return true;
    }
    return false;
  }

  /**
   * Checks for a valid longitude value.
   */
  private boolean isValidLongitude(float value) {
    if(value >= -180 && value <= 180) {
      return true;
    }
    return false;
  }

  /**
   * Checks for a valid submit job request.
   */
  public ValidationStatus validate(HttpServletRequest request) {
    String nName = request.getParameter(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME);
    if(!isNullOrEmpty(nName) || !isAlphabet(nName)) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE, "Name should not be empty");
    }

    String nPhone = request.getParameter(DataStoreEntityParams.ENTITY_PHONE_PROPERTY_NAME);
    if(!isNullOrEmpty(nPhone) || !isNumericInt(nPhone)) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE, "Phone should not be empty");
    }

    String nDate = request.getParameter(DataStoreEntityParams.ENTITY_DATE_PROPERTY_NAME);
    if(!isNullOrEmpty(nDate)) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE, "Date should not be empty");
    }

    String startLat = request.getParameter(DataStoreEntityParams.ENTITY_START_LAT_PROPERTY_NAME);
    if(!isNullOrEmpty(startLat) || !isNumericFloat(startLat) || !isValidLatitude(Float.parseFloat(startLat))) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE, "Start Latitude should be a valid number");
    }
   
    String startLong = request.getParameter(DataStoreEntityParams.ENTITY_START_LONG_PROPERTY_NAME);
    if(!isNullOrEmpty(startLong) || !isNumericFloat(startLong) || !isValidLongitude(Float.parseFloat(startLong))) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE, "Start Longitude should be a valid number");
    }
    
    String endLat = request.getParameter(DataStoreEntityParams.ENTITY_END_LAT_PROPERTY_NAME);
    if(!isNullOrEmpty(endLat) || !isNumericFloat(endLat) || !isValidLatitude(Float.parseFloat(endLat))) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE, "End Latitude should be a valid number");
    }
    
    String endLong = request.getParameter(DataStoreEntityParams.ENTITY_END_LONG_PROPERTY_NAME);
    if(!isNullOrEmpty(endLong) || !isNumericFloat(endLong) || !isValidLongitude(Float.parseFloat(endLong))) {
      return ValidationStatus.create(ValidationStatusCode.VALIDATIONFAILURE, "End Longitude should be a valid number");
    }
    
    return ValidationStatus.create(ValidationStatusCode.VALIDATIONSUCCESS, "");
  }
}