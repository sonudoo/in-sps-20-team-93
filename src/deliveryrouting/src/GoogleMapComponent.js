import React, { Component } from 'react';
import { Map, GoogleApiWrapper, Polyline, Marker } from 'google-maps-react';

const request = require('request');

let polylineCoordinates = [];
let markerCoordinates = [];

const mapStyles = {
  width: '100%',
  height: '100%'
};

export class GoogleMapComponent extends Component {
  render() {
    this.getJobsCoordinates();

    return (
      <Map
        google={this.props.google}
        zoom={13}
        style={mapStyles}
        initialCenter={{
         lat: 28.6143, 
         lng: 77.1994
        }}
        >
          
        {polylineCoordinates.map((polyCoord, index) => {
          return (
            <MapView.Polyline
              index={index}
              coordinates={polyCoord}
              strokeWidth={2}
              strokeColor="red"
            />
          )
        })}

        {markerCoordinates.map((markerCoord, index)=> {
          return (
            <Marker
              key={index}
              position={{lat: markerCoord.lat, lng: markerCoord.lng}}
              title={markerCoord.name}
            />
          )
        })}

      </Map>
    );
  }

  getJobsCoordinates() {
    request('http://localhost:8080/api/getPath', function (error, response, body) {
      body = JSON.parse(body);
      let responseJobs = body.responseJobs;
      let currPolyline = [];
      let singleJob = true;

      responseJobs.map((currJob) => {
        currPolyline.push({lat: currJob.latitude, lng: currJob.longitude});
        markerCoordinates.push({lat: currJob.latitude, lng: currJob.longitude, name: currJob.name});

        if(!singleJob) {
          polylineCoordinates.push(currPolyline);
          currPolyline = [];
          currPolyline.push({lat: currJob.latitude, lng: currJob.longitude});
        }
        
        singleJob = false;
      });
    });
  }
}

export default GoogleApiWrapper({
  apiKey: 'YOUR_API_KEY'
})(GoogleMapComponent);