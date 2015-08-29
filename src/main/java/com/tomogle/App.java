package com.tomogle;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tomogle.constants.Constants;
import com.tomogle.dependencyinjection.HttpServerModule;
import com.tomogle.server.ExpiringUrlServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

import static java.lang.String.format;

public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    // TODO: Validate expected properties exist on app load via a Config class
    Properties properties = new Properties();
    try {
      LOGGER.info("Loading properties from file");
      properties.load(App.class.getClassLoader().getResourceAsStream(Constants.PROPERTY_FILE_NAME));
      LOGGER.info("Successfully properties from file");
    } catch(IOException e) {
      LOGGER.error(format("Could not load the Property file '%s'", Constants.PROPERTY_FILE_NAME), e);
      exit(1);
    }
    LOGGER.info("Initializing dependency injection");
    Injector injector = Guice.createInjector(new HttpServerModule(properties));
    LOGGER.info("Starting ExpiringUrlServer");
    ExpiringUrlServer expiringUrlServer = injector.getInstance(ExpiringUrlServer.class);
    expiringUrlServer.runServer();
  }

  private static void exit(final int status) {
    System.exit(status);
  }

}
