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
const SERVER_API_URL = '/api/getPath';

/**
  *  @type {Object} Google Map centre Coordinates
  */
const MAP_CENTRE = { lat: 28.6143, lng: 77.1994 };

export const MapsApi = {
  MAPS_API_URL: MAPS_API_URL,
  MAP_CENTRE: MAP_CENTRE,
};

export const ServerApi = {
  SERVER_API_URL: SERVER_API_URL,
}

