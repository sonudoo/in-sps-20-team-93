const MAPS_API_KEY = "__GOOGLE_MAPS_API_KEY__";
const MAPS_API_URL = `https://maps.googleapis.com/maps/api/js?key=${MAPS_API_KEY}&callback=initMap`;
const SERVER_API_URL = '/api/getPath';
const MAP_CENTRE = { lat: 28.6143, lng: 77.1994 };

export const MapsApi = {
  MAPS_API_URL: MAPS_API_URL,
  SERVER_API_URL: SERVER_API_URL,
  MAP_CENTRE: MAP_CENTRE,
};

