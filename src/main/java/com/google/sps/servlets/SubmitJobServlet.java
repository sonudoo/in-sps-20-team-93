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

package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.sps.lib.IResponse;
import com.google.sps.lib.JobHandler;
import com.google.sps.lib.LibUtils;
import com.google.sps.lib.SubmitJobValidator;

/**
 * Submits job provided by user to Database.
 */
@WebServlet("/api/submitJob")
public class SubmitJobServlet extends HttpServlet {

  /**
   * Handles server side POST requests.
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    JobHandler jobHandler = new JobHandler();
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    SubmitJobValidator requestValidator = new SubmitJobValidator();

    IResponse handlerResponse = jobHandler.addJobToDataStore(request, datastore, requestValidator);
    String responseJson = LibUtils.convertResponseToJson(handlerResponse);
    response.setStatus(handlerResponse.getStatus());
    response.setContentType("application/json;");
    response.getWriter().println(responseJson);
  }
}