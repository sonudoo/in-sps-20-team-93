package com.google.sps.lib;

import com.google.appengine.repackaged.com.google.gson.Gson;

public class LibUtils {

  public static String convertResponseToJson(IResponse response) {
    return new Gson().toJson(response);
  }
}