package com.tomogle;

import org.eclipse.jetty.server.Server;

public class App {
  public static void main(String[] args) {
    Server httpServer = new Server();
    try {
      httpServer.start();
      httpServer.join();
    } catch(Exception e) {
      // TODO: Log this
    }
  }
}
