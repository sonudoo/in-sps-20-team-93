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

import javax.servlet.http.HttpServletRequest;
import com.google.sps.lib.validator.SubmitJobValidator;

/**
 * The factory for creating instances of {@link IRequestHandler}. The factory
 * has single instance of all the classes required by request handlers such as
 * {@link DatastoreWrapper} and {@link SubmitJobValidator}. These global objects
 * fed to the constructor of the handlers when their instances are created.
 */
public class JobHandlerFactory {

  private static final DatastoreWrapper databaseWrapper = new DatastoreWrapper();
  private static final SubmitJobValidator submitJobValidator = new SubmitJobValidator();

  public static SubmitJobHandler createSubmitJobHandler(HttpServletRequest request) {
    return new SubmitJobHandler(submitJobValidator, databaseWrapper, request);
  }

  public static GetPathHandler createGetPathHandler(HttpServletRequest request) {
    return new GetPathHandler(databaseWrapper);
  }

  public static CleanDatastoreHandler createCleanDatastoreHandler(HttpServletRequest request) {
    return new CleanDatastoreHandler(databaseWrapper);
  }
}
