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

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.ByteArrayInputStream;
import java.lang.Exception;
import java.net.HttpURLConnection;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MapsApiDistanceCalculatorTest {
  @Mock
  HttpURLConnection mockConnection;

  @Mock
  URLWrapper mockUrlWrapper;

  @Test
  public void findDistance_successResponse_matrixAsExpected() throws Exception {
    // when(mockUrlWrapper.openConnection(any())).thenReturn(mockConnection);
    // when(mockConnection.getResponseCode()).thenReturn(200);
    // String apiResponse = "{}";
    // when(mockConnection.getInputStream()).thenReturn(new
    // ByteArrayInputStream(apiResponse.getBytes("UTF-8")));
  }
}