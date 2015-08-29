package com.tomogle;

import com.tomogle.handler.ExpiringUrlProxy;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class AppTest {

  private static final String[] EMPTY_ARGS = new String[] {};

  @Mocked
  private Server server;

  @Test
  public void testMainStartsJettyServer() throws Exception {
    new NonStrictExpectations() {{
      server.start(); times = 1;
    }};
    App.main(EMPTY_ARGS);
  }

  @Test
  public void testMainJoinsWithJettyServer() throws Exception {
    new NonStrictExpectations() {{
      server.join(); times = 1;
    }};
    App.main(EMPTY_ARGS);
  }
  
  @Test
  public void testMainSetsJettyServerToStopAtShutdown() throws Exception {
    new NonStrictExpectations() {{
      server.setStopAtShutdown(true); times = 1;
    }};
    App.main(EMPTY_ARGS);
  }

  @Test
  public void testMainSetsHandler() throws Exception {
    new NonStrictExpectations() {{
      server.setHandler((Handler)any); times = 1;
    }};
    App.main(EMPTY_ARGS);
  }

  @Test
  public void mainSetsTheHandler() throws Exception {
    new NonStrictExpectations() {{
      server.setHandler((Handler)any); times = 1;
    }};
    App.main(EMPTY_ARGS);
  }
}
