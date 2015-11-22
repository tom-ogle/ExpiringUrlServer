package com.tomogle.dependencyinjection;

import com.google.inject.Provider;
import com.tomogle.service.SimpleURIService;
import com.tomogle.service.URIService;

public class URIServiceProvider implements Provider<URIService> {
  public URIService get() {
    return new SimpleURIService();
  }
}
