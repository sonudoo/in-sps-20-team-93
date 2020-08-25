/**
 *  @const {string} Google Maps Api key
 */
const MAPS_API_KEY = '__GOOGLE_MAPS_API_KEY__';

/**
 *  @const {string} Google Maps Api Url
 */
const MAPS_API_URL = `https://maps.googleapis.com/maps/api/js?key=${MAPS_API_KEY}&callback=initMap`;

/**
 *  @const {string} Server Api Url for getting all paths
 */
const SERVER_GET_PATH_URL = '/api/getPath';

/**
 *  @const {string} Server Api Url for submitting job
 */
const SERVER_SUBMIT_JOB_URL = '/api/submitJob';

/**
 *  @const {!Object} Google Map centre Coordinates
 */
const MAP_CENTRE = { lat: 28.6143, lng: 77.1994 };

/**
 *  @const {string} Google Map centre Coordinates Location Name
 */
const MAP_CENTRE_LOCATION_NAME = 'India Gate, New Delhi, India';

/**
 *  @const {string} Bad Request Response message
 */
const BAD_REQUEST_RESPONSE = 'Phone is empty or incorrect';

/**
 *  @const {string} Request Submitted response message
 */
const SUBMITTED_REQUEST_RESPONSE = 'Request Submitted';

export const MapsApi = {
  MAPS_API_KEY: MAPS_API_KEY,
  MAPS_API_URL: MAPS_API_URL,
  MAP_CENTRE: MAP_CENTRE,
  MAP_CENTRE_LOCATION_NAME: MAP_CENTRE_LOCATION_NAME,
};

export const ServerApi = {
  SERVER_GET_PATH_URL: SERVER_GET_PATH_URL,
  SERVER_SUBMIT_JOB_URL: SERVER_SUBMIT_JOB_URL
}

export const RequestResponse = {
  BAD_REQUEST_RESPONSE: BAD_REQUEST_RESPONSE,
  SUBMITTED_REQUEST_RESPONSE: SUBMITTED_REQUEST_RESPONSE,
};