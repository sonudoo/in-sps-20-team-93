package com.google.sps.lib.handler;

import java.util.List;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

/**
 * The wrapper for database related operations.
 */
class DatastoreWrapper {

  // TODO(sonudoo): Tests are missing for this class as well as it requires
  // setting up the entire app engine integration environment.

  private final DatastoreService datastore;

  DatastoreWrapper() {
    datastore = DatastoreServiceFactory.getDatastoreService();
  }

  /**
   * Inserts the job into the datastore.
   * 
   * @param datastoreJob
   */
  void addJob(final DatastoreJob datastoreJob) {
    final Entity nJob = new Entity(DatastoreJobEntityParams.ENTITY_NAME);
    nJob.setProperty(DatastoreJobEntityParams.ENTITY_JOBID_PROPERTY_NAME, datastoreJob.getJobId());
    nJob.setProperty(DatastoreJobEntityParams.ENTITY_USER_PROPERTY_NAME, datastoreJob.getName());
    nJob.setProperty(DatastoreJobEntityParams.ENTITY_PHONE_PROPERTY_NAME, datastoreJob.getPhone());
    nJob.setProperty(DatastoreJobEntityParams.ENTITY_LAT_PROPERTY_NAME, datastoreJob.getLatitude());
    nJob.setProperty(DatastoreJobEntityParams.ENTITY_LONG_PROPERTY_NAME, datastoreJob.getLongitude());
    datastore.put(nJob);
  }

  /**
   * Inserts a job to the database.
   */
  List<DatastoreJob> getAllJobs() {

    // TODO(sonudoo): Implement this.
    return null;
  }

}