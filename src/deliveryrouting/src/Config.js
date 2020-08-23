const MAPS_API_KEY = "__GOOGLE_MAPS_API_KEY__";
const SUBMIT_JOB_SERVER_API_URL = "/api/submitJob";
const SUBMITTED_REQUEST_RESPONSE = "Request Submitted";
const BAD_REQUEST_RESPONSE = "Bad Request";
const MAP_CENTRE = { locationName: "India Gate, New Delhi, India", lat: 28.6129, lng: 77.2295 };

export const getMapsApiKey = () => {
  return MAPS_API_KEY;
};

export const getServerApiUrl = () => {
  return SUBMIT_JOB_SERVER_API_URL;
};

export const getSubmittedRequestResponse = () => {
  return SUBMITTED_REQUEST_RESPONSE;
};

export const getBadRequestResponse = () => {
  return BAD_REQUEST_RESPONSE;
};

export const getMapCentre = () => {
  return MAP_CENTRE;
};
