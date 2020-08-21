import React from "react";
import "./Style.css";

export class SubmitJobForm extends React.Component {
  render() {
    return (
      <form
        className="SubmitJobFormContainer"
        action="http://localhost:8080/api/submitJob"
        method="POST"
      >
        <input
          className="DisabledInputContainer"
          name="Name"
          type="String"
          name="Name"
          placeholder="Name"
          disabled={true}
        />
        <input
          className="InputContainer"
          name="Phone"
          type="String"
          placeholder="Phone"
        />
        <input
          className="DisabledInputContainer"
          type="number"
          placeholder="Latitudes"
          name="Latitude"
          value={this.props.latitudes}
          disabled={true}
        />
        <input
          className="DisabledInputContainer"
          type="number"
          placeholder="Longitudes"
          name="Longitude"
          value={this.props.longitudes}
          disabled={true}
        />
        <input className="SubmitButtonContainer" type="submit"/>
      </form>

      
    );
  }
}
