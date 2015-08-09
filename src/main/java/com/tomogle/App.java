package com.tomogle;

import com.tomogle.server.HttpServer;

public class App {

  private static HttpServer httpServer;

  public static void main(String[] args) {
    // TODO: Make port, etc. configurable
    int port = 8080;
    httpServer = new HttpServer(port);
    httpServer.startServer();
  }
}
