package com.google.sps.lib;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * TODO(shradha-khapra)
 */
public class URLWrapper {

  public HttpURLConnection openConnection(String url) throws IOException {
    return (HttpURLConnection) new URL(url).openConnection();
  }
}