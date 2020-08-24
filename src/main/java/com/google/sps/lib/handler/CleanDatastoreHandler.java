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

/**
 * Handles deletion of all entries from datastore.
 */
public class CleanDatastoreHandler implements IRequestHandler {
    
  private final DatastoreWrapper datastoreWrapper;

  /**
   * The constructor is purposely kept package-private. Please use
   * {@link JobHandlerFactory} to get an instance of this class.
   */
  CleanDatastoreHandler(final DatastoreWrapper datastoreWrapper) {
    this.datastoreWrapper = datastoreWrapper;
  }

  /**
   * Deletes all the entries from
   * {@link DatastoreWrapper}.
   */
  @Override
  public HandlerResponse getResponse() {
    datastoreWrapper.cleanDataStore();
    return new SuccessResponse();
  }
}