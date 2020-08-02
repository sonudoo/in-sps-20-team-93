package com.google.sps.lib;

import java.util.List;

/**
 * creates a graph with distance between the adjacent locations
 */
public class Graph {
  private List<Task> TaskList;
  private final int ListSize;
  double[][] DistanceMatrix;

  public Graph(List<Task> TaskList) {
    this.TaskList = TaskList;
    ListSize = TaskList.size();
    DistanceMatrix = createGraph();
  }

 /**
  * Getter for DistanceMatrix
  */
  public double[][] getGraph() {
    return this.DistanceMatrix;
  }

 /**
  * Creates an initial graph
  */
  public double[][] createGraph() {
    double[][] InitialDistanceMatrix = new double[ListSize][ListSize];

    for (int i = 1; i < InitialDistanceMatrix.length; i++) {
      for (int j = 1; j < InitialDistanceMatrix[1].length; j++) {
        if (i != j) {
          InitialDistanceMatrix[i - 1][j - 1] = findDistance(i, j);
        }
      }
    }
    return InitialDistanceMatrix;
  }

 /**
  * Calculates the euclidean distance between two locations
  * @param i
  * @param j
  */
  public double findDistance(int i, int j) {
    // TODO: Try to use Map API to calculate Distance
    return Math.sqrt(Math.pow((TaskList.get(i - 1).getStartLatitude() - TaskList.get(j - 1).getStartLatitude()), 2)
                     + Math.pow((TaskList.get(i - 1).getStartLongitude() - TaskList.get(j - 1).getStartLongitude()), 2));
  }
}