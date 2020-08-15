package com.google.sps.lib;

import java.util.UUID;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

/**
 * The wrapper for database related operations.
 * 
 * God, please apologize me for marking this class as a Singleton. I had never
 * learned from my mistake. Please forgive me this time and I promise to add a
 * factory for this class.
 */
public class DatabaseWrapper {
  private static DatabaseWrapper instance;
  private final DatastoreService datastore;

  private DatabaseWrapper() {
    datastore = DatastoreServiceFactory.getDatastoreService();
  }

  public static DatabaseWrapper getInstance() {
    if (instance == null) {
      instance = new DatabaseWrapper();
    }
    return instance;
  }

  /**
   * Inserts a job to the database.
   * 
   * @param nName      Name of the customer.
   * @param nPhone     Phone number of the customer.
   * @param nStartLat  The starting latitude of the delivery.
   * @param nStartLong The starting longitude of the delivery.
   * @param nEndLat    The end latitude of the delivery.
   * @param nEndLong   The end longitude of the delivery.
   */
  public void addJob(final String nName, final String nPhone, final double nStartLat, final double nStartLong,
      final double nEndLat, final double nEndLong) {
    final String nJobId = UUID.randomUUID().toString();
    final Entity nJob = new Entity(DataStoreEntityParams.ENTITY_NAME);
    nJob.setProperty(DataStoreEntityParams.ENTITY_JOBID_PROPERTY_NAME, nJobId);
    nJob.setProperty(DataStoreEntityParams.ENTITY_USER_PROPERTY_NAME, nName);
    nJob.setProperty(DataStoreEntityParams.ENTITY_PHONE_PROPERTY_NAME, nPhone);
    nJob.setProperty(DataStoreEntityParams.ENTITY_START_LAT_PROPERTY_NAME, nStartLat);
    nJob.setProperty(DataStoreEntityParams.ENTITY_START_LONG_PROPERTY_NAME, nStartLong);
    nJob.setProperty(DataStoreEntityParams.ENTITY_END_LAT_PROPERTY_NAME, nEndLat);
    nJob.setProperty(DataStoreEntityParams.ENTITY_END_LONG_PROPERTY_NAME, nEndLong);
    datastore.put(nJob);
  }

}