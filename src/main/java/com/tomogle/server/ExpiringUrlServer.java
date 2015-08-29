package com.tomogle.server;

import com.google.inject.Inject;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpiringUrlServer {

  private static Logger LOGGER = LoggerFactory.getLogger(ExpiringUrlServer.class);

  @Inject
  private Server server;
  @Inject
  private Handler handler;

  public void runServer() {
    try {
      LOGGER.info("Initializing server");
      server.setHandler(handler);
      server.setStopAtShutdown(true);
      LOGGER.info("Starting server");
      server.start();
      LOGGER.info("Server started");
      server.join();
    } catch(Exception e) {
      LOGGER.error("Caught an Exception when starting the server", e);
    }
  }

}
