/**
 *  Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https: *www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * gets the path to be followed.
 */
@WebServlet("/api/getpath")
public class GetPathServlet extends HttpServlet {
 /**
  * Handles server side GET requests.
  */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // created these tasks for the algorithm implementation only
    // TODO: to get tasks from database and form the List accordingly
    Task task1 = new Task("1", 67.09, 98.7, 67.09, 23.65);
    Task task2 = new Task("2", 68.34, 99.8, 68.34, 23.65);
    Task task3 = new Task("3", 68.38, 98.9, 68.38, 23.67);
    Task task4 = new Task("4", 68.40, 95.7, 68.40, 23.69);
    Task task5 = new Task("5", 68.42, 98.7, 68.42, 23.72);
    List<Task> TaskList = new ArrayList<>();
    TaskList.add(task1);
    TaskList.add(task2);
    TaskList.add(task3);
    TaskList.add(task4);
    TaskList.add(task5);

    Graph InitialGraph = new Graph(TaskList);
    String PathJson = convertToJsonUsingGson(InitialGraph.getGraph());
    response.setContentType("application/json;");
    response.getWriter().println(PathJson);
  }

 /**
  * Converts a List instance into a JSON string using the Gson library.
  * 
  * @param task_order
  */
  public String convertToJsonUsingGson(double[][] task_order) {
    Gson gson = new Gson();
    String json = gson.toJson(task_order);
    return json;
  }
}