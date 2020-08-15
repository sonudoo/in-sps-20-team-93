package com.google.sps.lib.handler;

import java.util.ArrayList;
import java.util.List;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

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
   * Returns all the jobs from the datastore.
   */
  List<DatastoreJob> getAllJobs() {
    Query query = new Query(DatastoreJobEntityParams.ENTITY_NAME);
    PreparedQuery results = datastore.prepare(query);
    List<DatastoreJob> datastoreJobs = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      String jobId = (String) entity.getProperty(DatastoreJobEntityParams.ENTITY_JOBID_PROPERTY_NAME);
      String name = (String) entity.getProperty(DatastoreJobEntityParams.ENTITY_USER_PROPERTY_NAME);
      String phone = (String) entity.getProperty(DatastoreJobEntityParams.ENTITY_PHONE_PROPERTY_NAME);
      double latitudes = (double) entity.getProperty(DatastoreJobEntityParams.ENTITY_LAT_PROPERTY_NAME);
      double longitudes = (double) entity.getProperty(DatastoreJobEntityParams.ENTITY_LONG_PROPERTY_NAME);
      datastoreJobs.add(new DatastoreJob(jobId, name, phone, latitudes, longitudes));
    }
    return datastoreJobs;
  }
}