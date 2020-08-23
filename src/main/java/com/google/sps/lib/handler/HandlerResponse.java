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

import com.google.appengine.repackaged.com.google.gson.Gson;

/**
 * The handler response. All child classes are required to implement two methods
 * <p>
 * 1. One which returns the HTTP response status code and
 * <p>
 * 2. Other that returns a {@link ResponseMessage}.
 */
public abstract class HandlerResponse {
  /**
   * Returns HTTP response status.
   */
  public abstract int getStatus();

  /**
   * Returns additional message with the response.
   */
  public abstract ResponseMessage getMessage();

  /**
   * Returns the response object as json string.
   */
  public String getJson() {
    return new Gson().toJson(this);
  }
}