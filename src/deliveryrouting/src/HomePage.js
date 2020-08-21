import React, { Component } from "react";
import { Map, GoogleApiWrapper, InfoWindow, Marker } from "google-maps-react";
import "./Style.css";
import { SubmitJobForm } from "./SubmitJobForm";

export class SubmitJobContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      showInfoWindow: false,
      activeMarker: {},
      marker: {
        position: {
          lat: 28.6129,
          lng: 77.2295,
        },
      },
    };
  }
  onMarkerClick = (props, marker, e) =>
    this.setState({
      selectedPlace: props,
      activeMarker: marker,
      showingInfoWindow: true,
    });

  onClose = (props) => {
    if (this.state.showingInfoWindow) {
      this.setState({
        showingInfoWindow: false,
        activeMarker: null,
      });
    }
  };

  onMarkerDragEnd(coord) {
    const { latLng } = coord;
    let newPosition = {
      lat: latLng.lat().toFixed(5),
      lng: latLng.lng().toFixed(5),
    };
    this.setState({
      marker: {
        position: newPosition,
      },
    });
  }

  render() {
    return (
      <div className="HomePageBodyContainer">
        <div>
          <SubmitJobForm
            latitudes={this.state.marker.position.lat}
            longitudes={this.state.marker.position.lng}
          />
        </div>
        <div>
          <Map
            className="MapsContainer"
            google={this.props.google}
            zoom={6}
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
            ></Marker>
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
