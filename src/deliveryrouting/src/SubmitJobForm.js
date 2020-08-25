import React from "react";
import "./SubmitJobComponent.css";
import { ServerApi, RequestResponse } from "./config";
const $ = require("jquery");

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
      responseStatus: "",
      disablePhone: false,
      disableSubmitButton: false,
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
      url: ServerApi.SERVER_SUBMIT_JOB_URL,
      type: "POST",
      data: {
        Name: this.props.name,
        Phone: this.state.phone,
        Latitude: this.props.latitudes,
        Longitude: this.props.longitudes,
      },
      success: () => {
        this.setState({
          responseMessage: RequestResponse.SUBMITTED_REQUEST_RESPONSE,
          responseStatus: "1",
          showResponse: true,
          disablePhone: true,
          disableSubmitButton: true,
        });
      },
      error: () => {
        this.setState({
          responseMessage: RequestResponse.BAD_REQUEST_RESPONSE,
          responseStatus: "0",
          showResponse: true,
          disablePhone: true,
          disableSubmitButton: true,
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
      responseStatus: "",
      disablePhone: false,
      disableSubmitButton: false,
    });
  };

  render() {
    return (
      <div>
        <form
          className="SubmitJobFormContainer"
          onSubmit={this.onSubmitHandler}
        >
          {this.state.showResponse ? (
            <div className="ResponseContainer">
              <div
                className={
                  this.state.responseStatus === "1"
                    ? "SubmittedReqContainer"
                    : "BadReqContainer"
                }
              >
                {this.state.responseStatus === "1" ? (
                  <span role="img" aria-label="RequestSubmit">
                    ✅
                  </span>
                ) : (
                  <span role="img" aria-label="BadRequest">
                    ❌
                  </span>
                )}
                {this.state.responseMessage}
                <button
                  className="ButtonContainer"
                  onClick={this.removeResponseMessage}
                  title="Click to submit new request"
                >
                  <b>OK</b>
                </button>
              </div>
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
            className={
              this.state.disableSubmitButton
                ? "DisabledSubmitButtonContainer"
                : "SubmitButtonContainer"
            }
            id="SubmitButton"
            type="submit"
            disabled={this.state.disableSubmitButton}
          />
        </form>
      </div>
    );
  }
}