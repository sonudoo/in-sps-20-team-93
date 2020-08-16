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

import javax.servlet.http.HttpServletResponse;

/**
 * Represents a bad request response.
 */
class BadRequestErrorResponse extends HandlerResponse {

  private final static String RESPONSE_MESSAGE_TITLE = "Bad Request Format";

  private final int status;
  private final ResponseMessage message;

  /**
   * The constructor is package-private because its instance can only be created
   * by request handlers.
   */
  BadRequestErrorResponse(final String errorMessage) {
    this.status = HttpServletResponse.SC_BAD_REQUEST;
    this.message = ResponseMessage.create(RESPONSE_MESSAGE_TITLE, errorMessage);
  }

  @Override
  public int getStatus() {
    return status;
  }

  @Override
  public ResponseMessage getMessage() {
    return message;
  }
}