package com.tomogle.server;

import org.eclipse.jetty.server.Server;

public class HttpServer {

  private Server httpServer = new Server();

  public void startServer() {
    try {
      httpServer.start();
      httpServer.setStopAtShutdown(true);
      httpServer.join();
    } catch(Exception e) {
      // TODO: Log this
    }
  }
}
