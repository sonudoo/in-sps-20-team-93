/* global google */
import React, { Component } from "react";
import {
  withGoogleMap,
  GoogleMap,
  withScriptjs,
  DirectionsRenderer,
} from "react-google-maps";
import { MapsApi, ServerApi } from "./config";
import "./GetPathComponent.css";

const GoogleMapComponent = withScriptjs(
  withGoogleMap((props) => (
    <GoogleMap defaultZoom={1} defaultCenter={MapsApi.MAP_CENTRE}>
      <DirectionsRenderer directions={props.directions} />
    </GoogleMap>
  ))
);

/**
 * This class renders all the delivery paths for shortest distance.
 */
export default class GetPathComponent extends Component {
  constructor(props) {
    super(props);
    // Location initially remains invalid to indicate that no path has been returned from server yet.
    this.state = {
      directions: {},
      currStartLocation: "",
      currEndLocation: "",
      currStartIdx: 0,
      currEndIdx: 1,
    };
    this.responseJobs_ = [];
    this.directionsService_ = {};
  }

  componentDidMount = async () => {
    await Promise.all([
      this.fetchJobCoordinates_(),
      this.waitForMapsApiToLoad_(),
    ]);
    this.directionsService_ = new google.maps.DirectionsService();
    this.displayDirections_(0, 1);
  };

  render() {
    return (
      <div>
        <GoogleMapComponent
          directions={this.state.directions}
          googleMapURL={MapsApi.MAPS_API_URL}
          loadingElement={<div />}
          containerElement={<div style={{ height: "80vh" }} />}
          mapElement={<div style={{ height: "100%" }} />}
        />
        <p
          className="PathInformation"
          title="Path Info Success"
          style={this.state.currStartLocation === "" ? { display: "none" } : {}}
        >
          {this.state.currEndIdx}/{this.responseJobs_.length - 1}&nbsp; This
          delivery path goes
          <br></br>
          <b>From</b> {this.state.currStartLocation}
          <br></br>
          <b>To</b> {this.state.currEndLocation}<br></br>
          (Click on markers for more location information)
        </p>
        <p
          className="PathInformation"
          title="Path Info Processing"
          style={this.state.currStartLocation === "" ? {} : { display: "none" }}
        >
          Fetching deliveries. Please wait!
        </p>
        <div className="ControlButtonContainer">
          <button
            tabIndex="0"
            className="PreviousPathButton"
            id="PreviousPathButton"
            onClick={this.onPreviousClick}
            disabled={this.state.currStartIdx <= 0}
          >
            Previous Path
          </button>
          <button
            tabIndex="0"
            className="NextPathButton"
            id="NextPathButton"
            onClick={this.onNextClick}
            disabled={this.state.currEndIdx >= this.responseJobs_.length - 1}
          >
            Next Path
          </button>
        </div>
      </div>
    );
  }

  onPreviousClick = () => {
    this.displayDirections_(
      this.state.currStartIdx - 1,
      this.state.currEndIdx - 1
    );
  };

  onNextClick = () => {
    this.displayDirections_(
      this.state.currStartIdx + 1,
      this.state.currEndIdx + 1
    );
  };

  fetchJobCoordinates_ = async () => {
    return fetch(ServerApi.SERVER_GET_PATH_URL)
      .then((response) => response.json())
      .then((responseJson) => {
        this.responseJobs_ = responseJson.responseJobs;
      });
  };

  waitForMapsApiToLoad_ = async () => {
    return new Promise((resolve, _) => {
      window.initMap = () => {
        resolve();
      };
    });
  };

  displayDirections_ = (startIdx, endIdx) => {
    this.directionsService_.route(
      {
        origin: {
          lat: this.responseJobs_[startIdx].latitude,
          lng: this.responseJobs_[startIdx].longitude,
        },
        destination: {
          lat: this.responseJobs_[endIdx].latitude,
          lng: this.responseJobs_[endIdx].longitude,
        },
        travelMode: google.maps.TravelMode.DRIVING,
      },
      (result, status) => {
        if (status === google.maps.DirectionsStatus.OK) {
          this.setState({
            directions: result,
            currStartLocation: this.responseJobs_[startIdx].name,
            currEndLocation: this.responseJobs_[endIdx].name,
            currStartIdx: startIdx,
            currEndIdx: endIdx,
          });
        } else {
          this.setState({ error: result });
        }
      }
    );
  };
}