import React from 'react';

const MAPS_API_KEY = "__GOOGLE_MAPS_API_KEY__";
const MAPS_API_URL = `https://maps.googleapis.com/maps/api/js?key=${MAPS_API_KEY}&callback=initMap`;
const SERVER_API_URL = '/api/getPath';
const MAP_CENTRE = { lat: 28.6143, lng: 77.1994 };

export const getMapsApiKey = () => {
  return MAPS_API_KEY;
}

export const getMapsApiUrl = () => {
  return MAPS_API_URL;
}

export const getServerApiUrl = () => {
  return SERVER_API_URL;
}

export const getMapCentre = () => {
  return MAP_CENTRE;
}
