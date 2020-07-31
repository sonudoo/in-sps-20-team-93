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
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.lang.Exception;
import com.google.sps.lib.ErrorResponse;
import com.google.sps.lib.Job;
import org.springframework.mock.web.MockHttpServletRequest;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

@RunWith(JUnit4.class)
public final class SubmitJobTest {

  @Test
  public void addJobToDataStore_jobIsAddedToDataStore() throws Exception{
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.addParameter("name", "MockUser");
    request.addParameter("phone", "991");
    request.addParameter("date", "27/7/2020");
    request.addParameter("start_latitude", "67.09");
    request.addParameter("start_longitude", "23.65");
    request.addParameter("end_latitude", "98.7");
    request.addParameter("end_longitude", "22.3");

    ErrorResponse errResponse = new Job().addJobToDataStore(request, datastore);

    assertEquals(errResponse, null);
  }

  @Test
  public void convertToJsonUsingGson_stringIsReturned() {
    ErrorResponse errResponse = new ErrorResponse(false, "no error");

    String errJson = new Job().convertToJsonUsingGson(errResponse);

    assertFalse(errJson.isEmpty());
  }
}