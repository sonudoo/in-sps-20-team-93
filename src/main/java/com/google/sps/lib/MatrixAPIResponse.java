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

import java.util.List;
import java.util.Arrays;

/**
 * Represents a Matrix API response.
 */
public class MatrixAPIResponse {
  private String responseStatus; 
  private List<String> destinationAddresses;
  private List<String> originAddresses;
  private List<List<DistanceElement>> distanceRows;

  /**
   * Represents all information stored in a distance element.
   */
  public class DistanceElement {
    private String elementStatus;
    private DistanceElementData duration;
    private DistanceElementData distance;

    public String getElementStatus() {
      return this.elementStatus;
    }

    public DistanceElementData getDuration() {
      return this.duration;
    }

    public DistanceElementData getDistance() {
      return this.distance;
    }
  }

  public class DistanceElementData {
    private String value;
    private String text;

    public String getValue() {
      return this.value;
    }

    public String getText() {
      return this.text;
    }
  }  

  public String getResponseStatus() {
    return this.responseStatus;
  }

  public List<String> getOriginAddresses() {
    return this.originAddresses;
  }

  public List<String> getDestinationAddresses() {
    return this.destinationAddresses;
  }

  public List<List<DistanceElement>> getDistanceRows() {
    return this.distanceRows;
  }
}