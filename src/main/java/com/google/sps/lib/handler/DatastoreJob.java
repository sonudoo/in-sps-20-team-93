package com.google.sps.lib.handler;

/**
 * A job object stored in the datastore.
 */
class DatastoreJob {
  private final String jobId;
  private final String name;
  private final String phone;
  private final double latitude;
  private final double longitude;

  DatastoreJob(final String jobId, final String name, final String phone, final double latitude,
      final double longitude) {
    this.jobId = jobId;
    this.name = name;
    this.phone = phone;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public String getPhone() {
    return phone;
  }

  public String getName() {
    return name;
  }

  public String getJobId() {
    return jobId;
  }

}