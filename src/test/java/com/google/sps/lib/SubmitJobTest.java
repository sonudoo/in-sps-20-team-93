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
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.lang.Exception;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public final class SubmitJobTest {
  @Mock
  HttpServletRequest dataStoreRequest;

  @Test
  public void addJobToDataStore_jobIsAddedToDataStore() throws Exception {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    when(dataStoreRequest.getParameter(Job.ENTITY_USER_PROPERTY_NAME)).thenReturn("MockUser");
    when(dataStoreRequest.getParameter(Job.ENTITY_PHONE_PROPERTY_NAME)).thenReturn("911");
    when(dataStoreRequest.getParameter(Job.ENTITY_DATE_PROPERTY_NAME)).thenReturn("27/7/2020");
    when(dataStoreRequest.getParameter(Job.ENTITY_START_LAT_PROPERTY_NAME)).thenReturn("67.09");
    when(dataStoreRequest.getParameter(Job.ENTITY_START_LONG_PROPERTY_NAME)).thenReturn("23.65");
    when(dataStoreRequest.getParameter(Job.ENTITY_END_LAT_PROPERTY_NAME)).thenReturn("88.7");
    when(dataStoreRequest.getParameter(Job.ENTITY_END_LONG_PROPERTY_NAME)).thenReturn("22.3");

    SubmitJobValidator requestValidator = new SubmitJobValidator();
    
    IResponse handlerResponse = new Job().addJobToDataStore(dataStoreRequest, datastore, requestValidator);

    assertNotNull(handlerResponse); 
  }

  @Mock
  HttpServletRequest validationRequest;

  @Test
  public void validate_requestIsValidated() throws Exception {
    when(validationRequest.getParameter(Job.ENTITY_USER_PROPERTY_NAME)).thenReturn("MockUser");
    when(validationRequest.getParameter(Job.ENTITY_PHONE_PROPERTY_NAME)).thenReturn("911");
    when(validationRequest.getParameter(Job.ENTITY_DATE_PROPERTY_NAME)).thenReturn("27/7/2020");
    when(validationRequest.getParameter(Job.ENTITY_START_LAT_PROPERTY_NAME)).thenReturn("67.09");
    when(validationRequest.getParameter(Job.ENTITY_START_LONG_PROPERTY_NAME)).thenReturn("23.65");
    when(validationRequest.getParameter(Job.ENTITY_END_LAT_PROPERTY_NAME)).thenReturn("88.7");
    when(validationRequest.getParameter(Job.ENTITY_END_LONG_PROPERTY_NAME)).thenReturn("22.7");

    SubmitJobValidator requestValidator = new SubmitJobValidator();
    
    ValidationStatus validationStatus = requestValidator.validate(validationRequest);

    assertNotNull(validationStatus);
  }
}