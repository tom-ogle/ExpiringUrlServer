package com.tomogle.dependencyinjection;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.util.Properties;

public class HttpServerModule extends AbstractModule {

  private final Properties properties;

  public HttpServerModule(final Properties properties) {
    this.properties = properties;
  }

  @Override protected void configure() {
    Names.bindProperties(binder(), properties);
  }
}
