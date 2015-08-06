package com.tomogle;

import com.tomogle.server.HttpServer;

public class App {

  private static final HttpServer httpServer = new HttpServer();

  public static void main(String[] args) {
    httpServer.startServer();
  }
}
