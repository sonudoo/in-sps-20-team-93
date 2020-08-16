package com.google.sps.lib.algorithm.distance;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A wrapper over {@link URL}.
 */
public class URLWrapper {

  public HttpURLConnection openConnection(String url) throws IOException {
    return (HttpURLConnection) new URL(url).openConnection();
  }
}