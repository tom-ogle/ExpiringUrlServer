package com.tomogle.server;

import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {

  private static Logger logger = LoggerFactory.getLogger(HttpServer.class);

  private Server httpServer = new Server();

  public HttpServer(final int port) {
    httpServer = new Server(port);
  }

  public void startServer() {
    try {
      httpServer.start();
      httpServer.setStopAtShutdown(true);
      httpServer.join();
    } catch(Exception e) {
      logger.error("Caught an Exception when starting the server", e);
    }
  }
}
