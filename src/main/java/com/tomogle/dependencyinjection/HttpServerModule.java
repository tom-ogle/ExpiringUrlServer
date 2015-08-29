package com.tomogle.dependencyinjection;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.tomogle.constants.PropertyNames;
import com.tomogle.handler.ExpiringUrlProxy;
import com.tomogle.server.ExpiringUrlServer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Properties;

public class HttpServerModule extends AbstractModule {

  private final Properties properties;

  public HttpServerModule(final Properties properties) {
    this.properties = properties;
  }

  @Override protected void configure() {
    Names.bindProperties(binder(), properties);
    bind(ExpiringUrlServer.class).annotatedWith(Names.named(PropertyNames.SERVER_HTTP_PORT)).to(ExpiringUrlServer.class);
  }

  @Provides
  Handler provideHandler() {
    ServletContextHandler context = new ServletContextHandler();
    context.addServlet(new ServletHolder(new ExpiringUrlProxy()), "/*");
    return context;
  }

  @Provides @Inject
  Server provideServerWithConnectors(@Named(PropertyNames.SERVER_HTTP_PORT) final int httpPort) {
    Server server = new Server();
    // This is overkill for now, but sets us up for adding e.g. HTTPS connector
    ServerConnector httpConnector = new ServerConnector(server);
    httpConnector.setPort(httpPort);
    server.addConnector(httpConnector);
    return server;
  }
}
