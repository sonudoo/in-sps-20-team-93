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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.lang.RuntimeException;
import com.google.gson.Gson;

/**
 * Calculates the distance between every node using google maps API.
 */
public class MapsApiDistanceCalculator implements IDistanceCalculator {

  private static final String MATRIX_API_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial";
  private static final String API_KEY = "YOUR_API_KEY";
  private final URLWrapper urlWrapper;

  public MapsApiDistanceCalculator(final URLWrapper urlWrapper) {
    this.urlWrapper = urlWrapper;
  }

  public static String getApiKey() {
    return API_KEY;
  }

  @Override
  public double[][] findDistance(final List<Coordinate> taskList) {
    final String matrixAPIResponse = getApiResponse(taskList);
    final MatrixAPIResponse apiObject = parseJsonResponse(matrixAPIResponse);
    final double distMatrix[][] = getDistanceMatrix(apiObject);
    return distMatrix;
  }

  /**
   * Calls the Matrix API and returns the response.
   */
  private String getApiResponse(final List<Coordinate> coordinates) {
    try {
      final String requestUrl = getRequestUrl(coordinates);
      final HttpURLConnection connection = urlWrapper.openConnection(requestUrl);
      connection.setRequestMethod("GET");
      final int responseCode = connection.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {
        final StringBuffer apiResponse = new StringBuffer();
        final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
          apiResponse.append(inputLine);
        }
        in.close();
        return apiResponse.toString();
      } else {
        return "";
      }
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Builds a request URL based on matrix URL and Task list.
   */
  private String getRequestUrl(final List<Coordinate> coordinates) throws IOException {
    String requestURL = MATRIX_API_URL;
    final StringBuilder origins = new StringBuilder("origins=");
    final StringBuilder destinations = new StringBuilder("destinations=");
    boolean isWarehouseAdded = false; // Single Warehouse origin point location

    for (final Coordinate currCoordinate : coordinates) {
      if (!isWarehouseAdded) {
        origins.append(currCoordinate.getLatutides() + "," + currCoordinate.getLongitudes());
        destinations.append(currCoordinate.getLatutides() + "," + currCoordinate.getLongitudes());
        isWarehouseAdded = true;
      } else {

        origins.append("|" + currCoordinate.getLatutides() + "," + currCoordinate.getLongitudes());
        destinations.append("|" + currCoordinate.getLatutides() + "," + currCoordinate.getLongitudes());
      }
    }

    requestURL += "&" + origins.toString() + "&" + destinations.toString() + "&key=" + API_KEY;
    return requestURL;
  }

  /**
   * Parses API json response to Response object.
   */
  private MatrixAPIResponse parseJsonResponse(final String response) {
    MatrixAPIResponse apiResponseObj = new MatrixAPIResponse();
    final Gson gson = new Gson();
    apiResponseObj = gson.fromJson(response, MatrixAPIResponse.class);
    return apiResponseObj;
  }

  /**
   * Builds a distance matrix from API response object.
   */
  private double[][] getDistanceMatrix(final MatrixAPIResponse apiObject) {
    final int locationsSize = apiObject.getRows().size();
    final double distMatrix[][] = new double[locationsSize][locationsSize];
    int row = 0, col = 0;
    for (final Rows currRow : apiObject.getRows()) {
      col = 0;
      for (final Elements currElement : currRow.getElements()) {
        distMatrix[row][col] = Double.parseDouble(currElement.getDistance().getValue());
        col++;
      }
      row++;
    }
    return distMatrix;
  }
}
