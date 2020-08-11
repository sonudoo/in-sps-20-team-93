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
import java.net.URI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.lang.RuntimeException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Calculates the distance between every node using google maps API.
 */
public class MapsApiDistanceCalculator implements IDistanceCalculator {

  private static final String MATRIX_API_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial";
  //private static final String API_KEY = "YOUR_API_KEY";
  private static final String API_KEY = "AIzaSyAMsHvuX0YkyiEtOebfy_JxuYpYtw_nTYg";

  @Override
  public double[][] findDistance(List<Task> taskList) {
    String matrixAPIResponse = getResponse(taskList);
    MatrixAPIResponse apiObject = jsonResponseParser(matrixAPIResponse); 
    double distMatrix[][] = getDistanceMatrix(apiObject);
    return distMatrix;
  }  

  /**
   * Builds a request URL based on matrix URL and Task list.
   */
  private String findRequestURL(List<Task> taskList) {
    String requestURL = MATRIX_API_URL;
    StringBuilder origins = new StringBuilder("origins=");
    StringBuilder destinations = new StringBuilder("destinations=");
    boolean isWarehouseAdded[] = {false};       //Single Warehouse Origin point location

    taskList.forEach((currTask) -> {
      if(!isWarehouseAdded[0]) {
        origins.append(currTask.getStartLatitude() + "," + currTask.getStartLongitude());
        destinations.append(currTask.getStartLatitude() + "," + currTask.getStartLongitude());
        isWarehouseAdded[0] = true;
      }

      origins.append("|" + currTask.getEndLatitude() + "," + currTask.getEndLongitude());
      destinations.append("|" + currTask.getEndLatitude() + "," + currTask.getStartLongitude());
    });

    requestURL += "&" + origins.toString() + "&" + destinations.toString() + "&key=" + API_KEY;
    return requestURL;
  }

  /**
   * Parses API json response to Response object. 
   */
  public MatrixAPIResponse jsonResponseParser(String response) {
    MatrixAPIResponse apiResponseObj = new MatrixAPIResponse();
    Gson gson = new Gson();

    apiResponseObj = gson.fromJson(response, MatrixAPIResponse.class);
    return apiResponseObj;
  }

  /**
   * Builds a distance matrix from API response object.
   */
  private double[][] getDistanceMatrix(MatrixAPIResponse apiObject) {
    return null;
  }

  /**
   * Calls the Matrix API and returns the response.
   */
  private String getResponse(List<Task> taskList) {
    try {
      String requestURL = findRequestURL(taskList);
      URL urlObject = new URL(requestURL);
		  HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
		  connection.setRequestMethod("GET");
    
      int responseCode = connection.getResponseCode();
		  if (responseCode == HttpURLConnection.HTTP_OK) { 
			  BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			  String inputLine;
			  StringBuffer apiResponse = new StringBuffer();

			  while ((inputLine = in.readLine()) != null) {
			    apiResponse.append(inputLine);
			  }
        in.close();
			  return apiResponse.toString();
		  } else {
			  return null;
      }
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}