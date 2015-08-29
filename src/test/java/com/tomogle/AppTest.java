package com.tomogle;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.tomogle.dependencyinjection.HttpServerModule;
import com.tomogle.server.ExpiringUrlServer;
import mockit.Deencapsulation;
import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@RunWith(JMockit.class)
public class AppTest {

  private static final String[] EMPTY_ARGS = new String[] {};

  @Mocked
  private Server server;

  @Test
  public void mainLoadsProperties(final @Mocked({"exit"}) App app) throws Exception {
    new MockUp<Properties>() {
      @Mock(minInvocations = 1)
      void load(Invocation inv, InputStream inputStream) throws IOException {
        inv.proceed();
      }
    };
    new NonStrictExpectations() {{
      // Ensure exit is not invoked
      Deencapsulation.invoke(App.class, "exit", anyInt); times = 0;
    }};
    App.main(EMPTY_ARGS);
  }
  
  @Test
  public void mainSetsJettyServerToStopAtShutdown() throws Exception {
    new NonStrictExpectations() {{
      server.setStopAtShutdown(true); times = 1;
    }};
    App.main(EMPTY_ARGS);
  }

  @Test
  public void mainSetsHandler() throws Exception {
    new NonStrictExpectations() {{
      server.setHandler((Handler) any); times = 1;
    }};
    App.main(EMPTY_ARGS);
  }

  @Test
  public void mainAddsConnector() throws Exception {
    new NonStrictExpectations() {{
      server.addConnector((Connector) any); times = 1;
    }};
    App.main(EMPTY_ARGS);
  }

  @Test
  public void mainStartsJettyServer() throws Exception {
    new NonStrictExpectations() {{
      server.start(); times = 1;
    }};
    App.main(EMPTY_ARGS);
  }

  @Test
  public void mainWaitsForJettyServerToExit() throws Exception {
    new NonStrictExpectations() {{
      server.join(); times = 1;
    }};
    App.main(EMPTY_ARGS);
  }

  @Test
  public void mainCreatesInjectorWithAnHttpServerModule(final @Mocked Guice guice, final @Mocked Injector injector,
      final @Mocked ExpiringUrlServer server) throws Exception {
    new NonStrictExpectations() {{
      Guice.createInjector((HttpServerModule)any); result = injector; times = 1;
      injector.getInstance(ExpiringUrlServer.class); result = server;
    }};
    App.main(EMPTY_ARGS);
  }

  @Test
  public void mainRetrievesTheExpiringUrlServerFromTheInjector(final @Mocked Guice guice, final @Mocked Injector injector,
      final @Mocked ExpiringUrlServer server) throws Exception {
    new NonStrictExpectations() {{
      Guice.createInjector((HttpServerModule)any); result = injector;
      injector.getInstance(ExpiringUrlServer.class); result = server; times = 1;
    }};
    App.main(EMPTY_ARGS);
  }
}
