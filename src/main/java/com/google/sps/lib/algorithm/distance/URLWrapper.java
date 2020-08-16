package com.google.sps.lib.algorithm.distance;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A wrapper over {@link URL}. This is because {@link URL} is final which makes
 * testing difficult.
 */
public class URLWrapper {

  /**
   * Opens a connection to the provided HTTP Url and returns a
   * {@link HttpURLConnection} object.
   * 
   * @param url
   * @throws IOException If the connection cannot be established.
   */
  public HttpURLConnection openConnection(String url) throws IOException {
    return (HttpURLConnection) new URL(url).openConnection();
  }
}