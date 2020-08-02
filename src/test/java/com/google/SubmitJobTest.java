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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.lang.Exception;
import com.google.sps.lib.ErrorResponse;
import com.google.sps.lib.Job;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public final class SubmitJobTest {
  @Mock
  HttpServletRequest request;

  @Test
  public void addJobToDataStore_jobIsAddedToDataStore() throws Exception{
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    when(request.getParameter("name")).thenReturn("MockUser");
    when(request.getParameter("phone")).thenReturn("911");
    when(request.getParameter("date")).thenReturn("27/7/2020");
    when(request.getParameter("start_latitude")).thenReturn("67.09");
    when(request.getParameter("start_longitude")).thenReturn("23.65");
    when(request.getParameter("end_latitude")).thenReturn("98.7");
    when(request.getParameter("end_longitude")).thenReturn("22.3");

    ErrorResponse errResponse = new Job().addJobToDataStore(request, datastore);

    assertNotNull(errResponse); 
  }

  @Test
  public void convertToJsonUsingGson_stringIsReturned() {
    ErrorResponse errResponse = new ErrorResponse(false, "no error");

    String errJson = new Job().convertToJsonUsingGson(errResponse);

    assertFalse(errJson.isEmpty());
  }
}