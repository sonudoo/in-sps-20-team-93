package com.google.sps.lib;

import java.util.List;

public interface ICalculateDistance {
  double findDistance(List<Task> taskList, int i, int j);
}

class calculateDistanceUsingEuclidean implements ICalculateDistance {

  @Override
  public double findDistance(List<Task> taskList, int i, int j) {
    double xCoordinate = taskList.get(i - 1).getStartLatitude() - taskList.get(j - 1).getStartLatitude();
    double yCoordinate = taskList.get(i - 1).getStartLongitude() - taskList.get(j - 1).getStartLongitude();
    return Math.sqrt(((xCoordinate * xCoordinate) + (yCoordinate * yCoordinate)));
  }
}

class calculateDistanceUsingMapsApi implements ICalculateDistance {
  
  //TODO[samii9914]: calculate the distance using maps api
  @Override
  public double findDistance(List<Task> taskList, int i, int j) {
    return 0;
  }
}