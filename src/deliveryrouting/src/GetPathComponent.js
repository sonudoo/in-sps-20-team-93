/* global google */
import React, { Component } from 'react';
import {
  withGoogleMap,
  GoogleMap,
  withScriptjs,
  DirectionsRenderer,
} from "react-google-maps";

const MAPS_API_KEY = "";
const MAPS_API_URL = `https://maps.googleapis.com/maps/api/js?key=${MAPS_API_KEY}&callback=initMap`;

const GoogleMapComponent = withScriptjs(withGoogleMap((props) =>
  <GoogleMap
    defaultZoom={1}
    defaultCenter={{
      lat: 28.6143,
      lng: 77.1994
    }}
  >
    <DirectionsRenderer
      directions={props.directions}
    />
  </GoogleMap>
));

export default class GetPathComponent extends Component {
  constructor(props) {
    super(props);
    this.state = { directions: {} };
    this.responseJobs_ = [];
    this.directionsService_ = {};
  }

  async componentDidMount() {
    await Promise.all([this.fetchJobCoordinates_(), this.waitForMapsApiToLoad_()]);
    this.directionsService_ = new google.maps.DirectionsService();
    this.displayDirections_(0, 1);
  }

  render() {
    return (
      <GoogleMapComponent directions={this.state.directions}
        googleMapURL={MAPS_API_URL}
        loadingElement={<div style={{ height: `100px` }} />}
        containerElement={<div style={{ height: `1000px` }} />}
        mapElement={<div style={{ height: `1000px` }} />}
      />
    );
  }

  async fetchJobCoordinates_() {
    return fetch('http://localhost:8080/api/getPath')
      .then(response => response.json())
      .then(responseJson => {
        this.responseJobs_ = responseJson.responseJobs
      });
  }

  async waitForMapsApiToLoad_() {
    return new Promise((resolve, _) => {
      window.initMap = () => {
        resolve();
      }
    });
  }

  displayDirections_(startIdx, endIdx) {
    this.directionsService_.route(
      {
        origin: { lat: this.responseJobs_[startIdx].latitude, lng: this.responseJobs_[startIdx].longitude },
        destination: { lat: this.responseJobs_[endIdx].latitude, lng: this.responseJobs_[endIdx].longitude },
        travelMode: google.maps.TravelMode.DRIVING,
      },
      (result, status) => {
        if (status === google.maps.DirectionsStatus.OK) {
          this.setState({ directions: result });
        } else {
          this.setState({ error: result });
        }
      }
    );
  }
}