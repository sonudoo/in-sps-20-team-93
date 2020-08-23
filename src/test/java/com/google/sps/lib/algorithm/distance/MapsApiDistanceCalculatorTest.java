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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.ByteArrayInputStream;
import java.lang.Exception;
import java.net.HttpURLConnection;
import org.mockito.Mock;
import org.mockito.Captor;
import org.mockito.ArgumentCaptor;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class MapsApiDistanceCalculatorTest {
  @Mock
  HttpURLConnection mockConnection;

  @Mock
  URLWrapper mockUrlWrapper;

  @Captor
  ArgumentCaptor<String> urlCaptor;


  @Test
  public void findDistance_successResponse_matrixAsExpected() throws Exception {
    when(mockUrlWrapper.openConnection(any())).thenReturn(mockConnection);
    when(mockConnection.getResponseCode()).thenReturn(200);
    String apiResponse = "{ \"destination_addresses\" : [ \"Amar Jawan Jyoti Rd, India Gate, New Delhi, Delhi 110001, India\", \"24, Ramakanta Mistri Ln, Cossipore, Newland, College Square, Kolkata, West Bengal 700073, India\", \"1777, Lower Parel, Friends Colony, Kurla West, Kurla, Mumbai, Maharashtra 400070, India\" ], \"origin_addresses\" : [ \"Amar Jawan Jyoti Rd, India Gate, New Delhi, Delhi 110001, India\", \"24, Ramakanta Mistri Ln, Cossipore, Newland, College Square, Kolkata, West Bengal 700073, India\", \"1777, Lower Parel, Friends Colony, Kurla West, Kurla, Mumbai, Maharashtra 400070, India\" ], \"rows\" : [ { \"elements\" : [ { \"distance\" : { \"text\" : \"1 ft\", \"value\" : 0 }, \"duration\" : { \"text\" : \"1 min\", \"value\" : 0 }, \"status\" : \"OK\" }, { \"distance\" : { \"text\" : \"939 mi\", \"value\" : 1510850 }, \"duration\" : { \"text\" : \"1 day 2 hours\", \"value\" : 93607 }, \"status\" : \"OK\" }, { \"distance\" : { \"text\" : \"873 mi\", \"value\" : 1404782 }, \"duration\" : { \"text\" : \"23 hours 21 mins\", \"value\" : 84081 }, \"status\" : \"OK\" } ] }, { \"elements\" : [ { \"distance\" : { \"text\" : \"938 mi\", \"value\" : 1509173 }, \"duration\" : { \"text\" : \"1 day 2 hours\", \"value\" : 94436 }, \"status\" : \"OK\" }, { \"distance\" : { \"text\" : \"1 ft\", \"value\" : 0 }, \"duration\" : { \"text\" : \"1 min\", \"value\" : 0 }, \"status\" : \"OK\" }, { \"distance\" : { \"text\" : \"1,276 mi\", \"value\" : 2053949 }, \"duration\" : { \"text\" : \"1 day 17 hours\", \"value\" : 146217 }, \"status\" : \"OK\" } ] }, { \"elements\" : [ { \"distance\" : { \"text\" : \"876 mi\", \"value\" : 1409170 }, \"duration\" : { \"text\" : \"23 hours 25 mins\", \"value\" : 84308 }, \"status\" : \"OK\" }, { \"distance\" : { \"text\" : \"1,273 mi\", \"value\" : 2048291 }, \"duration\" : { \"text\" : \"1 day 16 hours\", \"value\" : 145790 }, \"status\" : \"OK\" }, { \"distance\" : { \"text\" : \"1 ft\", \"value\" : 0 }, \"duration\" : { \"text\" : \"1 min\", \"value\" : 0 }, \"status\" : \"OK\" } ] } ], \"status\" : \"OK\"}";
    when(mockConnection.getInputStream()).thenReturn(new ByteArrayInputStream(apiResponse.getBytes("UTF-8")));
    List<Coordinate> coordinates = new ArrayList<>(Arrays.asList(new Coordinate(28.6129, 77.2295),
        new Coordinate(22.5726, 88.3639), new Coordinate(19.0760, 72.8777)));
    double expectedDistanceMatrix[][] = { { 0.0, 1510850.0, 1404782.0 }, { 1509173.0, 0.0, 2053949.0 },
        { 1409170.0, 2048291.0, 0.0 }, };

    double distanceMatrix[][] = new MapsApiDistanceCalculator(mockUrlWrapper).findDistance(coordinates);

    for (int i = 0; i < expectedDistanceMatrix.length; i++) {
      assertArrayEquals(expectedDistanceMatrix[i], distanceMatrix[i], /* delta= */ 0.000001);
    }
  }

  @Test(expected = RuntimeException.class)
  public void findDistance_failureResponse_noApiResponse_RuntimeException() throws Exception {
    when(mockUrlWrapper.openConnection(any())).thenReturn(mockConnection);
    when(mockConnection.getResponseCode()).thenReturn(200);
    String apiResponse = "";
    when(mockConnection.getInputStream()).thenReturn(new ByteArrayInputStream(apiResponse.getBytes("UTF-8")));
    List<Coordinate> coordinates = new ArrayList<>(Arrays.asList(new Coordinate(28.6129, 77.2295),
        new Coordinate(22.5726, 88.3639), new Coordinate(19.0760, 72.8777)));

    new MapsApiDistanceCalculator(mockUrlWrapper).findDistance(coordinates);
  }

  @Test
  public void findDistance_successresponse_requestUrlAsExpected() throws Exception {
    when(mockUrlWrapper.openConnection(any())).thenReturn(mockConnection);
    when(mockConnection.getResponseCode()).thenReturn(200);
    String apiResponse = "{ \"destination_addresses\" : [ \"Amar Jawan Jyoti Rd, India Gate, New Delhi, Delhi 110001, India\", \"24, Ramakanta Mistri Ln, Cossipore, Newland, College Square, Kolkata, West Bengal 700073, India\", \"1777, Lower Parel, Friends Colony, Kurla West, Kurla, Mumbai, Maharashtra 400070, India\" ], \"origin_addresses\" : [ \"Amar Jawan Jyoti Rd, India Gate, New Delhi, Delhi 110001, India\", \"24, Ramakanta Mistri Ln, Cossipore, Newland, College Square, Kolkata, West Bengal 700073, India\", \"1777, Lower Parel, Friends Colony, Kurla West, Kurla, Mumbai, Maharashtra 400070, India\" ], \"rows\" : [ { \"elements\" : [ { \"distance\" : { \"text\" : \"1 ft\", \"value\" : 0 }, \"duration\" : { \"text\" : \"1 min\", \"value\" : 0 }, \"status\" : \"OK\" }, { \"distance\" : { \"text\" : \"939 mi\", \"value\" : 1510850 }, \"duration\" : { \"text\" : \"1 day 2 hours\", \"value\" : 93607 }, \"status\" : \"OK\" }, { \"distance\" : { \"text\" : \"873 mi\", \"value\" : 1404782 }, \"duration\" : { \"text\" : \"23 hours 21 mins\", \"value\" : 84081 }, \"status\" : \"OK\" } ] }, { \"elements\" : [ { \"distance\" : { \"text\" : \"938 mi\", \"value\" : 1509173 }, \"duration\" : { \"text\" : \"1 day 2 hours\", \"value\" : 94436 }, \"status\" : \"OK\" }, { \"distance\" : { \"text\" : \"1 ft\", \"value\" : 0 }, \"duration\" : { \"text\" : \"1 min\", \"value\" : 0 }, \"status\" : \"OK\" }, { \"distance\" : { \"text\" : \"1,276 mi\", \"value\" : 2053949 }, \"duration\" : { \"text\" : \"1 day 17 hours\", \"value\" : 146217 }, \"status\" : \"OK\" } ] }, { \"elements\" : [ { \"distance\" : { \"text\" : \"876 mi\", \"value\" : 1409170 }, \"duration\" : { \"text\" : \"23 hours 25 mins\", \"value\" : 84308 }, \"status\" : \"OK\" }, { \"distance\" : { \"text\" : \"1,273 mi\", \"value\" : 2048291 }, \"duration\" : { \"text\" : \"1 day 16 hours\", \"value\" : 145790 }, \"status\" : \"OK\" }, { \"distance\" : { \"text\" : \"1 ft\", \"value\" : 0 }, \"duration\" : { \"text\" : \"1 min\", \"value\" : 0 }, \"status\" : \"OK\" } ] } ], \"status\" : \"OK\"}";
    when(mockConnection.getInputStream()).thenReturn(new ByteArrayInputStream(apiResponse.getBytes("UTF-8")));
    List<Coordinate> coordinates = new ArrayList<>(Arrays.asList(new Coordinate(28.6129, 77.2295),
        new Coordinate(22.5726, 88.3639), new Coordinate(19.0760, 72.8777)));
    String expectedRequestUrl = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=28.6129,77.2295|22.5726,88.3639|19.076,72.8777&destinations=28.6129,77.2295|22.5726,88.3639|19.076,72.8777&";

    new MapsApiDistanceCalculator(mockUrlWrapper).findDistance(coordinates);

    verify(mockUrlWrapper).openConnection(urlCaptor.capture());
    String requestUrl = urlCaptor.getValue();
    assertEquals(expectedRequestUrl, requestUrl.split("key")[0]);
  }
}