/* global google */
import React, { Component } from 'react';
import {
  withGoogleMap,
  GoogleMap,
  withScriptjs,
  DirectionsRenderer,
} from "react-google-maps";
import { MapsApi, ServerApi } from './config';
import './GetPathComponent.css';

const GoogleMapComponent = withScriptjs(withGoogleMap((props) =>
  <GoogleMap
    defaultZoom={1}
    defaultCenter={
      MapsApi.MAP_CENTRE
    }
  >
    <DirectionsRenderer
      directions={props.directions}
    />
  </GoogleMap>
));

/**
 * This class renders all the delivery paths for shortest distance.
*/
export default class GetPathComponent extends Component {
  constructor(props) {
    super(props);
    // Location initially remains invalid to indicate that no path has been returned from server yet.
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

  componentDidMount = async () => {
    await Promise.all([this.fetchJobCoordinates_(), this.waitForMapsApiToLoad_()]);
    this.directionsService_ = new google.maps.DirectionsService();
    this.displayDirections_(0, 1);
  }

  render() {
    return (
      <div>
        <GoogleMapComponent directions={this.state.directions}
          googleMapURL={MapsApi.MAPS_API_URL}
          loadingElement={<div />}
          containerElement={<div style={{ height: '80vh' }} />}
          mapElement={<div style={{ height: '100%' }} />}
        />
        <p className="PathInformation" title="Path Info Success"
          style={this.state.currStartLocation === 'Invalid Location' ? { display: 'none' } : {}}>
          This delivery path goes from {this.state.currStartLocation} to {this.state.currEndLocation}.
          <br></br>
          (Click on markers for more location information)
        </p>
        <p className="PathInformation" title="Path Info Failure"
          style={this.state.currStartLocation === 'Invalid Location' ? {} : { display: 'none' }}>
          Looks like there are no pending deliveries in the system!
        </p>
        <div className="ButtonContainer">
          <button tabIndex="0" className="PreviousPathButton"
            id="PreviousPathButton" onClick={this.onPreviousClick}
            disabled={this.state.currStartIdx <= 0}>
            Previous Path
          </button>
          <button tabIndex="0" className="NextPathButton"
            id="NextPathButton" onClick={this.onNextClick}
            disabled={this.state.currEndIdx >= this.responseJobs_.length}>
            Next Path
          </button>
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

  fetchJobCoordinates_ = async () => {
    return fetch(ServerApi.SERVER_API_URL)
      .then(response => response.json())
      .then(responseJson => {
        this.responseJobs_ = responseJson.responseJobs;
      });
  }

  waitForMapsApiToLoad_ = async () => {
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