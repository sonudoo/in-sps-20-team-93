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
 * Represents a response message. The objects of this class forms a part of
 * {@link HandlerResponse} and so the visibility rules are same as that of
 * concrete implementations of {@link HandlerResponse}.
 */
class ResponseMessage {
  private final String messageTitle;
  private final String messageBody;

  private ResponseMessage(final String messageTitle, final String messageBody) {
    this.messageTitle = messageTitle;
    this.messageBody = messageBody;
  }

  static ResponseMessage create(final String messageTitle, final String messageBody) {
    return new ResponseMessage(messageTitle, messageBody);
  }

  String getMessageTitle() {
    return messageTitle;
  }

  String getMessageBody() {
    return messageBody;
  }
}