package com.google.sps.lib;

import javax.servlet.http.HttpServletResponse;

public class SuccessResponse implements IResponse {

  private final static String RESPONSE_MESSAGE_TITLE = "Success";

  private final int status;
  private final ResponseMessage message;

  public SuccessResponse() {
    this.status = HttpServletResponse.SC_OK;
    this.message = ResponseMessage.create(RESPONSE_MESSAGE_TITLE, "");
  }

  @Override
  public int getStatus() {
    return status;
  }

  @Override
  public ResponseMessage getMessage() {
    return message;
  }
}