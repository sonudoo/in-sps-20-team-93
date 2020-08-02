package com.google.sps.lib;

public class ResponseMessage {
  private final String messageTitle;
  private final String messageBody;

  private ResponseMessage(String messageTitle, String messageBody) {
    this.messageTitle = messageTitle;
    this.messageBody = messageBody;
  }

  public static ResponseMessage create(String messageTitle, String messageBody) {
    return new ResponseMessage(messageTitle, messageBody);
  }
}