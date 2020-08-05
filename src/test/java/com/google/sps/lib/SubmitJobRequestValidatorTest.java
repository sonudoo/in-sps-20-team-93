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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.lang.Exception;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public final class SubmitJobRequestValidatorTest {
  @Mock
  HttpServletRequest successRequest;

  @Test
  public void validate_validRequest_validationSuccess() throws Exception {
    when(successRequest.getParameter(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME)).thenReturn("MockUser");
    when(successRequest.getParameter(DataStoreEntityParams.ENTITY_PHONE_PROPERTY_NAME)).thenReturn("911");
    when(successRequest.getParameter(DataStoreEntityParams.ENTITY_DATE_PROPERTY_NAME)).thenReturn("27/7/2020");
    when(successRequest.getParameter(DataStoreEntityParams.ENTITY_START_LAT_PROPERTY_NAME)).thenReturn("67.09");
    when(successRequest.getParameter(DataStoreEntityParams.ENTITY_START_LONG_PROPERTY_NAME)).thenReturn("23.65");
    when(successRequest.getParameter(DataStoreEntityParams.ENTITY_END_LAT_PROPERTY_NAME)).thenReturn("88.7");
    when(successRequest.getParameter(DataStoreEntityParams.ENTITY_END_LONG_PROPERTY_NAME)).thenReturn("22.3");
    SubmitJobValidator requestValidator = new SubmitJobValidator();
    
    ValidationStatus validationStatus = requestValidator.validate(successRequest);

    assertEquals(validationStatus.getStatus(), ValidationStatusCode.VALIDATIONSUCCESS);
  }

  @Mock
  HttpServletRequest nameFailureRequest;

  @Test
  public void validate_invalidRequest_validationFailure_missingName() throws Exception {
    SubmitJobValidator requestValidator = new SubmitJobValidator();
    
    ValidationStatus validationStatus = requestValidator.validate(nameFailureRequest);

    assertEquals(validationStatus.getStatus(), ValidationStatusCode.VALIDATIONFAILURE);
  }

  @Mock
  HttpServletRequest phoneFailureRequest;

  @Test
  public void validate_invalidRequest_validationFailure_missingPhone() throws Exception {
    when(phoneFailureRequest.getParameter(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME)).thenReturn("MockUser");
    SubmitJobValidator requestValidator = new SubmitJobValidator();
    
    ValidationStatus validationStatus = requestValidator.validate(phoneFailureRequest);

    assertEquals(validationStatus.getStatus(), ValidationStatusCode.VALIDATIONFAILURE);
  }

  @Mock
  HttpServletRequest dateFailureRequest;

  @Test
  public void validate_invalidRequest_validationFailure_missingDate() throws Exception {
    when(dateFailureRequest.getParameter(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME)).thenReturn("MockUser");
    when(dateFailureRequest.getParameter(DataStoreEntityParams.ENTITY_PHONE_PROPERTY_NAME)).thenReturn("911");
    SubmitJobValidator requestValidator = new SubmitJobValidator();
    
    ValidationStatus validationStatus = requestValidator.validate(dateFailureRequest);

    assertEquals(validationStatus.getStatus(), ValidationStatusCode.VALIDATIONFAILURE);
  }

  @Mock
  HttpServletRequest startLatFailureRequest;

  @Test
  public void validate_invalidRequest_validationFailure_missingStartLatitude() throws Exception {
    when(startLatFailureRequest.getParameter(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME)).thenReturn("MockUser");
    when(startLatFailureRequest.getParameter(DataStoreEntityParams.ENTITY_PHONE_PROPERTY_NAME)).thenReturn("911");
    when(startLatFailureRequest.getParameter(DataStoreEntityParams.ENTITY_DATE_PROPERTY_NAME)).thenReturn("27/7/2020");
    SubmitJobValidator requestValidator = new SubmitJobValidator();
    
    ValidationStatus validationStatus = requestValidator.validate(startLatFailureRequest);

    assertEquals(validationStatus.getStatus(), ValidationStatusCode.VALIDATIONFAILURE);
  }

  @Mock
  HttpServletRequest startLongFailureRequest;

  @Test
  public void validate_invalidRequest_validationFailure_missingStartLongitude() throws Exception {
    when(startLongFailureRequest.getParameter(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME)).thenReturn("MockUser");
    when(startLongFailureRequest.getParameter(DataStoreEntityParams.ENTITY_PHONE_PROPERTY_NAME)).thenReturn("911");
    when(startLongFailureRequest.getParameter(DataStoreEntityParams.ENTITY_DATE_PROPERTY_NAME)).thenReturn("27/7/2020");
    when(startLongFailureRequest.getParameter(DataStoreEntityParams.ENTITY_START_LAT_PROPERTY_NAME)).thenReturn("67.09");
    SubmitJobValidator requestValidator = new SubmitJobValidator();
    
    ValidationStatus validationStatus = requestValidator.validate(startLongFailureRequest);

    assertEquals(validationStatus.getStatus(), ValidationStatusCode.VALIDATIONFAILURE);
  }

  @Mock
  HttpServletRequest endLatFailureRequest;

  @Test
  public void validate_invalidRequest_validationFailure_missingEndLatitude() throws Exception {
    when(endLatFailureRequest.getParameter(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME)).thenReturn("MockUser");
    when(endLatFailureRequest.getParameter(DataStoreEntityParams.ENTITY_PHONE_PROPERTY_NAME)).thenReturn("911");
    when(endLatFailureRequest.getParameter(DataStoreEntityParams.ENTITY_DATE_PROPERTY_NAME)).thenReturn("27/7/2020");
    when(endLatFailureRequest.getParameter(DataStoreEntityParams.ENTITY_START_LAT_PROPERTY_NAME)).thenReturn("67.09");
    when(endLatFailureRequest.getParameter(DataStoreEntityParams.ENTITY_START_LONG_PROPERTY_NAME)).thenReturn("23.65");
    SubmitJobValidator requestValidator = new SubmitJobValidator();
    
    ValidationStatus validationStatus = requestValidator.validate(endLatFailureRequest);

    assertEquals(validationStatus.getStatus(), ValidationStatusCode.VALIDATIONFAILURE);
  }

  @Mock
  HttpServletRequest endLongFailureRequest;

  @Test
  public void validate_invalidRequest_validationFailure_missingEndLongitude() throws Exception {
    when(endLongFailureRequest.getParameter(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME)).thenReturn("MockUser");
    when(endLongFailureRequest.getParameter(DataStoreEntityParams.ENTITY_PHONE_PROPERTY_NAME)).thenReturn("911");
    when(endLongFailureRequest.getParameter(DataStoreEntityParams.ENTITY_DATE_PROPERTY_NAME)).thenReturn("27/7/2020");
    when(endLongFailureRequest.getParameter(DataStoreEntityParams.ENTITY_START_LAT_PROPERTY_NAME)).thenReturn("67.09");
    when(endLongFailureRequest.getParameter(DataStoreEntityParams.ENTITY_START_LONG_PROPERTY_NAME)).thenReturn("23.65");
    when(endLongFailureRequest.getParameter(DataStoreEntityParams.ENTITY_END_LAT_PROPERTY_NAME)).thenReturn("88.7");
    SubmitJobValidator requestValidator = new SubmitJobValidator();
    
    ValidationStatus validationStatus = requestValidator.validate(endLongFailureRequest);

    assertEquals(validationStatus.getStatus(), ValidationStatusCode.VALIDATIONFAILURE);
  }
}