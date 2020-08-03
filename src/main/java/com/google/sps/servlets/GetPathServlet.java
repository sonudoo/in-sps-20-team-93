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
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.sps.lib.Graph;
import com.google.sps.lib.Task;

/**
 * Gets the path to be followed.
 */
@WebServlet("/api/getpath")
public class GetPathServlet extends HttpServlet {
  /**
   * Handles server side GET requests.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // TODO[samii9914]: to get tasks from database and form the List accordingly
    Task task1 = new Task("1", 67.09, 23.65, 98.7, 22.3);
    Task task2 = new Task("2", 68.34, 24.5, 99.7, 23.65);
    Task task3 = new Task("3", 68.38, 26.4, 98.6, 23.67);
    Task task4 = new Task("4", 68.40, 26.5, 98.3, 23.69);
    Task task5 = new Task("5", 68.42, 24.57, 98.7, 23.72);
    List<Task> taskList = new ArrayList<>();
    taskList.add(task1);
    taskList.add(task2);
    taskList.add(task3);
    taskList.add(task4);
    taskList.add(task5);

    Graph initialGraph = new Graph(taskList);
    String pathJson = convertPathToJson(initialGraph.getGraph());
    response.setContentType("application/json;");
    response.getWriter().println(pathJson);
  }

  /**
   * Converts a List instance into a JSON string using the Gson library.
   * @param taskOrder
   */
  public String convertPathToJson(double[][] taskOrder) {
    Gson gson = new Gson();
    String json = gson.toJson(taskOrder);
    return json;
  }
}