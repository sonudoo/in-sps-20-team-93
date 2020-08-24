package com.google.sps.lib.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * The wrapper for database related operations. The class (and all its methods)
 * is package-private because it is an implementation detail of the handler.
 * Servlets must not try to instantiate this class.
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
    nJob.setProperty(DatastoreJobEntityParams.ENTITY_LAT_PROPERTY_NAME, datastoreJob.getLatitudes());
    nJob.setProperty(DatastoreJobEntityParams.ENTITY_LONG_PROPERTY_NAME, datastoreJob.getLongitudes());
    datastore.put(nJob);
  }

  /**
   * Returns all the jobs from the datastore. If the number of jobs exceeds a
   * maximum {@param maximumJobs}, then the list will be trimmed to contain the
   * last {@param maximumJobs} elements.
   */
  List<DatastoreJob> getAllJobs(int maximumJobs) {
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
    return datastoreJobs.stream().skip(Math.max(0, datastoreJobs.size() - maximumJobs)).collect(Collectors.toList());
  }

  /**
   * Deletes all entries from the datastore.
   */  
  void cleanDataStore() {
    Query query = new Query(DatastoreJobEntityParams.ENTITY_NAME);
    PreparedQuery results = datastore.prepare(query);
    for (Entity entity : results.asIterable()) {
      datastore.delete(entity.getKey());
    }
  }
}