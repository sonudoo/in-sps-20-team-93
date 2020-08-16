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

package com.google.sps.lib.validator;

/**
 * Represents a request validation status.
 */
public class ValidationStatus {
  private final String message;
  private final ValidationStatusCode status;

  private ValidationStatus(ValidationStatusCode status, String message) {
    this.message = message;
    this.status = status;
  }

  public static ValidationStatus create(ValidationStatusCode status, String message) {
    return new ValidationStatus(status, message);
  }

  public ValidationStatusCode getStatus() {
    return this.status;
  }

  public String getMessage() {
    return this.message;
  }
}