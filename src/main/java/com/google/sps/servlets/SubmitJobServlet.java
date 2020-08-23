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
import com.google.sps.lib.handler.HandlerResponse;
import com.google.sps.lib.handler.JobHandlerFactory;

/**
 * Adds job provided by user to Datastore.
 */
@WebServlet("/api/submitJob")
public class SubmitJobServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HandlerResponse handlerResponse = JobHandlerFactory.createSubmitJobHandler(request).getResponse();
    response.setStatus(handlerResponse.getStatus());
    response.setContentType("application/json;");
    response.getWriter().println(handlerResponse.getJson());
  }
}