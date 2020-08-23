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

package com.google.sps.lib.algorithm.distance;

import java.util.List;

/**
 * The POJO class for Google Maps Matrix API response.
 */
public class MatrixApiResponse {
  private List<String> destinationAddresses;
  private List<String> originAddresses;
  private List<Rows> rows;
  private String status;

  public void setDestinationAddresses(List<String> destinationAddresses) {
    this.destinationAddresses = destinationAddresses;
  }

  public List<String> getDestinationAddresses() {
    return this.destinationAddresses;
  }

  public void setOriginAddresses(List<String> originAddresses) {
    this.originAddresses = originAddresses;
  }

  public List<String> getOriginAddresses() {
    return this.originAddresses;
  }

  public void setRows(List<Rows> rows) {
    this.rows = rows;
  }

  public List<Rows> getRows() {
    return this.rows;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return this.status;
  }
}

/**
 * Represents distance between origin and destination.
 */
class Distance {
  private String text;
  private String value;

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}

/**
 * Represents time duration in API response.
 */
class Duration {
  private String text;
  private String value;

  public void setText(String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}

/**
 * Represents a response row element.
 */
class Elements {
  private Distance distance;
  private Duration duration;
  private String status;

  public void setDistance(Distance distance) {
    this.distance = distance;
  }

  public Distance getDistance() {
    return this.distance;
  }

  public void setDuration(Duration duration) {
    this.duration = duration;
  }

  public Duration getDuration() {
    return this.duration;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return this.status;
  }
}

/**
 * Represents a row of API response.
 */
class Rows {
  private List<Elements> elements;

  public void setElements(List<Elements> elements) {
    this.elements = elements;
  }

  public List<Elements> getElements() {
    return this.elements;
  }
}
