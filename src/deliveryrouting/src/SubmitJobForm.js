import React from "react";
import "./Style.css";
import {
  getServerApiUrl,
  getBadRequestResponse,
  getSubmittedRequestResponse,
} from "./Config";
const $ = require("jquery");

const SERVER_API_URL = getServerApiUrl();
const REQUEST_SUBMITTED_RESPONSE = getSubmittedRequestResponse();
const BAD_REQUEST_RESPONSE = getBadRequestResponse();

/**
 * This class displays a form to submit job details.
 */
export class SubmitJobForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      phone: "",
      responseMessage: "",
      showResponse: false,
      disablePhone: false,
    };
  }

  /**
   * Sets the value of phone to value entered by user.
   * @param {!Object} event
   */
  onPhoneNumberChangeHandler = (event) => {
    this.setState({
      phone: event.target.value,
    });
  };

  /**
   * Calls the $.ajax request when Submit button is clicked to submit the
   * job details.
   * @param {!Object} event
   */
  onSubmitHandler = (event) => {
    event.preventDefault();
    $.ajax({
      url: SERVER_API_URL,
      type: "POST",
      data: {
        Name: this.props.name,
        Phone: this.state.phone,
        Latitude: this.props.latitudes,
        Longitude: this.props.longitudes,
      },
      success: () => {
        this.setState({
          responseMessage: REQUEST_SUBMITTED_RESPONSE,
          showResponse: true,
          disablePhone: true,
        });
      },
      error: () => {
        this.setState({
          responseMessage: BAD_REQUEST_RESPONSE,
          showResponse: true,
          disablePhone: true,
        });
      },
    });
  };

  /**
   * Removes the response message from the screen when OK button
   * is clicked.
   */
  removeResponseMessage = () => {
    this.setState({
      showResponse: false,
      disablePhone: false,
    });
  };

  render() {
    return (
      <form className="SubmitJobFormContainer" onSubmit={this.onSubmitHandler}>
        {this.state.showResponse ? (
          <div className="ResponseContainer">
            {this.state.responseMessage}
            <button
              className="ButtonContainer"
              onClick={this.removeResponseMessage}
              title="Click to submit new request"
            >
              <b>OK</b>
            </button>
          </div>
        ) : (
          <div></div>
        )}
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
          disabled={this.state.disablePhone}
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
        <input
          className="SubmitButtonContainer"
          id="SubmitButton"
          type="submit"
        />
      </form>
    );
  };
};