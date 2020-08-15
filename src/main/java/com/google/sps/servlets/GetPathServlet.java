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

package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.sps.lib.Graph;
import com.google.sps.lib.ITSPAlgorithm;
import com.google.sps.lib.LibUtils;
import com.google.sps.lib.TSPDynamicProgrammingAlgorithm;
import com.google.sps.lib.Task;

/**
 * Gets the path to be followed.
 */
@WebServlet("/api/getPath")
public class GetPathServlet extends HttpServlet {

  private static final long serialVersionUID = 2L;

  /**
   * Handles server side GET requests.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    List<Task> taskList = new ArrayList<>(Arrays.asList(new Task("1", 67.09, 23.65, 98.7, 22.3),
        new Task("2", 68.34, 24.5, 99.7, 23.65), new Task("3", 68.38, 26.4, 98.6, 23.67),
        new Task("4", 68.40, 26.5, 98.3, 23.69), new Task("5", 68.42, 24.57, 98.7, 23.72)));
    ITSPAlgorithm tspAlgorithm = new TSPDynamicProgrammingAlgorithm();
    Graph initialGraph = new Graph(taskList, tspAlgorithm);
    String pathJson = LibUtils.convertPathToJson(initialGraph.getMinimumPath());
    response.setContentType("application/json;");
    response.getWriter().println(pathJson);
  }
}