package com.tomogle.server;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.tomogle.constants.PropertyNames;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpiringUrlServer {

  private static Logger logger = LoggerFactory.getLogger(ExpiringUrlServer.class);

  @Inject
  private Server httpServer;
  @Inject
  private Handler handler;
  @Inject @Named(PropertyNames.SERVER_HTTP_PORT)
  private int httpPort;

  public void runServer() {
    try {
      ServerConnector httpConnector = new ServerConnector(httpServer);
      httpConnector.setPort(httpPort);
      httpServer.addConnector(httpConnector);
      httpServer.setHandler(handler);
      httpServer.setStopAtShutdown(true);
      httpServer.start();
      httpServer.join();
    } catch(Exception e) {
      logger.error("Caught an Exception when starting the server", e);
    }
  }

}
