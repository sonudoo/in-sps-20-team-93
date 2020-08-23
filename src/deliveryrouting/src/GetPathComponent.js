/* global google */
import React, { Component } from 'react';
import {
  withGoogleMap,
  GoogleMap,
  withScriptjs,
  DirectionsRenderer,
} from "react-google-maps";
import { getMapsApiKey, getMapsApiUrl, getMapCentre, getServerApiUrl } from './Config';
import './GetPathComponent.css';

const MAPS_API_KEY = getMapsApiKey();
const MAPS_API_URL = getMapsApiUrl();

const GoogleMapComponent = withScriptjs(withGoogleMap((props) =>
  <GoogleMap
    defaultZoom={1}
    defaultCenter={
      getMapCentre()
    }
  >
    <DirectionsRenderer
      directions={props.directions}
    />
  </GoogleMap>
));

export default class GetPathComponent extends Component {
  constructor(props) {
    super(props);
    this.state = { 
      directions: {},
      currStartLocation: "Invalid Location",
      currEndLocation: "Invalid Location",
      currStartIdx: 0,
      currEndIdx: 1,
    };
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
      <div>
        <GoogleMapComponent directions={this.state.directions}
          googleMapURL={MAPS_API_URL}
          loadingElement={<div/>}
          containerElement={<div style={{ height: '80vh'}} />}
          mapElement={<div style={{ height: '100%' }} />}
        />
        <p className="PathInformation" title="PathInfoSuccess" style={this.state.currStartLocation === 'Invalid Location' ? { display: 'none' } : {}}>
          This delivery path goes from {this.state.currStartLocation} to {this.state.currEndLocation}.
          <br></br>
          (Click on markers for more location information)
        </p>
        <p className="PathInformation" titel="PathInfoFailure" style={this.state.currStartLocation === 'Invalid Location' ? {} : { display: 'none' }}>
          Looks like there are no pending deliveries in the system!
        </p>
        <div className="ButtonContainer">
          <button tabIndex="0" className="PreviousPathButton" role="DisplayPreviousPath" onClick={this.onPreviousClick} disabled={this.state.currStartIdx <= 0}> Previous Path </button>
          <button tabIndex="0" className="NextPathButton" role="DisplayNextPath" onClick={this.onNextClick} disabled={this.state.currEndIdx >= this.responseJobs_.length - 1}> Next Path </button>
        </div>
     </div>
    );
  }

  onPreviousClick = () => { 
    this.setState({
      currStartIdx: this.state.currStartIdx - 1,
      currEndIdx: this.state.currEndIdx - 1,
    });
    this.displayDirections_(this.state.currStartIdx, this.state.currEndIdx);
  }

  onNextClick = () => {
    this.setState({
      currStartIdx: this.state.currStartIdx + 1,
      currEndIdx: this.state.currEndIdx + 1,
    });
    this.displayDirections_(this.state.currStartIdx, this.state.currEndIdx);
  }

  async fetchJobCoordinates_() {
    return fetch(getServerApiUrl())
      .then(response => response.json())
      .then(responseJson => {
        this.responseJobs_ = responseJson.responseJobs;
      });
  }

  async waitForMapsApiToLoad_() {
    return new Promise((resolve, _) => {
      window.initMap = () => {
        resolve();
      }
    });
  }

  displayDirections_ = (startIdx, endIdx) => {
    this.directionsService_.route(
      {
        origin: { lat: this.responseJobs_[startIdx].latitude, lng: this.responseJobs_[startIdx].longitude },
        destination: { lat: this.responseJobs_[endIdx].latitude, lng: this.responseJobs_[endIdx].longitude },
        travelMode: google.maps.TravelMode.DRIVING,
      },
      (result, status) => {
        if (status === google.maps.DirectionsStatus.OK) {
          this.setState({
            directions: result,
            currStartLocation: this.responseJobs_[startIdx].name,
            currEndLocation: this.responseJobs_[endIdx].name, 
          });
        } else {
          this.setState({ error: result });
        }
      }
    );
  }
}