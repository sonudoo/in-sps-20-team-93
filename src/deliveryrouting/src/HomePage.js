import React from "react";
import { Map, GoogleApiWrapper, InfoWindow, Marker } from "google-maps-react";
import Geocode from "react-geocode";
import "./Style.css";
import { SubmitJobForm } from "./SubmitJobForm";

export class SubmitJobContainer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      showInfoWindow: false,
      activeMarker: {},
      marker: {
        locationName: "New Delhi",
        position: {
          lat: 28.6129,
          lng: 77.2295,
        },
      },
    };
  };
  
  onMarkerClick = (props, marker, e) => {
    this.setState({
      selectedPlace: props,
      activeMarker: marker,
      showingInfoWindow: true,
    });
  }

  onClose = (props) => {
    if (this.state.showingInfoWindow) {
      this.setState({
        showingInfoWindow: false,
        activeMarker: null,
      });
    }
  }

  onMarkerDragEnd = (coord) => {
    const { latLng } = coord;
    let newPosition = {
      lat: latLng.lat().toFixed(5),
      lng: latLng.lng().toFixed(5),
    };
    let newLocation="";
    Geocode.setApiKey("AIzaSyAwCLmaC1sT7oGeoQDGAo97Z8qBGjlgyrU");
    Geocode.fromLatLng(newPosition.lat, newPosition.lng).then(
      response => {
        newLocation = response.results[0].formatted_address;
        this.setState({
          marker: {
            locationName: newLocation,
            position: newPosition,
          },
        });
      },
      error => {
        console.error(error);
      }
    ); 
  };

  render() {
    return (
      <div className="HomePageBodyContainer">
        <div>
        <h3 className="HeadingContainer">Welcome to DELIVERY SYSTEM!<span>😃</span></h3>
        <h4 className="HeadingContainer">Get the shortest route possible to complete all your deliveries !<span>🚛</span></h4>
        <div className="InstructionsContainer">
          <p>To add the delivery location, follow the instructions below</p>
          <ul type="circle">
            <li>Enter your phone number.</li>
            <li>Move the marker  on the map 
            to the location where you want to deliver your goods.</li>
            <li>Press Submit button.</li>
          </ul>
          <p>And you are all done!</p>
          </div>
          <SubmitJobForm
            latitudes={this.state.marker.position.lat}
            longitudes={this.state.marker.position.lng}
            name={this.state.marker.locationName}
          />
        </div>
        <div>
          <Map
            className="MapsContainer"
            google={this.props.google}
            zoom={13}
            initialCenter={{
              lat: 28.6129,
              lng: 77.2295,
            }}
          >
            <Marker
              onClick={this.onMarkerClick}
              draggable={true}
              position={this.state.marker.position}
              title={"Location"}
              onDragend={(t, map, coord) => this.onMarkerDragEnd(coord)}
            />
            <InfoWindow
              marker={this.state.activeMarker}
              visible={this.state.showingInfoWindow}
              onClose={this.onClose}
            >
              <div>
                <h4>
                  {this.state.marker.position.lat},
                  {this.state.marker.position.lng}
                </h4>
                <h4>
                  {this.state.marker.locationName}
                </h4>
              </div>
            </InfoWindow>
          </Map>
        </div>
      </div>
    );
  }
}

export default GoogleApiWrapper({
  apiKey: "AIzaSyAwCLmaC1sT7oGeoQDGAo97Z8qBGjlgyrU",
})(SubmitJobContainer);
