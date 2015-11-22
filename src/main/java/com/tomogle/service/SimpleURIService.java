package com.tomogle.service;

import com.google.inject.Singleton;

@Singleton
public class SimpleURIService implements URIService {
  public String lookupURI(final String path) {
    return "http://www.google.com";
  }
}
