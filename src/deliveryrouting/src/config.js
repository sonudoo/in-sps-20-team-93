/**
 *  @type {string} Google Maps Api key
 */
const MAPS_API_KEY = "__GOOGLE_MAPS_API_KEY__";

/**
 *  @type {string} Google Maps Api Url
 */
const MAPS_API_URL = `https://maps.googleapis.com/maps/api/js?key=${MAPS_API_KEY}&callback=initMap`;

/**
 *  @type {string} Server Api Url
 */
const SERVER_API_URL = "/api/getPath";

/**
 *  @type {string} Submit Job server Api Url
 */
const SUBMIT_JOB_SERVER_API_URL = "/api/submitJob";

/**
 *  @type {Object} Google Map centre Coordinates
 */
const MAP_CENTRE = { lat: 28.6143, lng: 77.1994 };

/**
 *  @type {string} Google Map centre Coordinates Location Name
 */
const MAP_CENTRE_LOCATION_NAME = "India Gate, New Delhi, India";

/**
 *  @type {string} Bad Request Response message
 */
const BAD_REQUEST_RESPONSE = "Bad Request";

/**
 * @type {string} Request Submitted response message
 */
const SUBMITTED_REQUEST_RESPONSE = "Request Submitted";

export const MapsApi = {
  MAPS_API_KEY: MAPS_API_KEY,
  MAPS_API_URL: MAPS_API_URL,
  MAP_CENTRE: MAP_CENTRE,
  MAP_CENTRE_LOCATION_NAME: MAP_CENTRE_LOCATION_NAME,
};

export const ServerApi = {
  SERVER_API_URL: SERVER_API_URL,
  SUBMIT_JOB_SERVER_API_URL: SUBMIT_JOB_SERVER_API_URL,
};

export const RequestResponse = {
  BAD_REQUEST_RESPONSE: BAD_REQUEST_RESPONSE,
  SUBMITTED_REQUEST_RESPONSE: SUBMITTED_REQUEST_RESPONSE,
};