import React from "react";
const $ = require('jquery');
import "./Style.css";

export class SubmitJobForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      phone: "",
    };
  }

  onPhoneNumberChangeHandler = (event) => {
    this.setState({
      phone: event.target.value,
    });
  };

  onSubmitHandler = (event) => {
    event.preventDefault();
    $.ajax({
      url: "http://localhost:8080/api/submitJob",
      type: "POST",
      data: {
        Name: this.props.name,
        Phone: this.state.phone,
        Latitude: this.props.latitudes,
        Longitude: this.props.longitudes,
      },
      success: () => {
        alert("Request Submitted!");
      },
      error: () => {
        alert("Bad Request!");
      },
    });
  };

  render() {
    return (
      <form className="SubmitJobFormContainer" onSubmit={this.onSubmitHandler}>
        <input
          className="DisabledInputContainer"
          name="Name"
          type="string"
          placeholder="Name"
          value={this.props.name}
          disabled={true}
        />
        <input
          className="InputContainer"
          name="Phone"
          type="string"
          placeholder="Phone"
          onChange={this.onPhoneNumberChangeHandler}
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
        <input className="SubmitButtonContainer" id="SubmitButton" type="submit" />
      </form>
    );
  }
}
