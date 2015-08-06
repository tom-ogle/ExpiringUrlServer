package com.tomogle;

import mockit.Deencapsulation;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import org.eclipse.jetty.server.Server;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class AppTest {

  @Mocked
  private Server server;

  @Test
  public void testMainStartsJettyServer() throws Exception {
    new NonStrictExpectations() {{
      server.start(); times = 1;
    }};
    App.main(new String[]{});
  }

  @Test
  public void testMainJoinsWithJettyServer() throws Exception {
    new NonStrictExpectations() {{
      server.join(); times = 1;
    }};
    App.main(new String[]{});
  }
  
  @Test
  public void testSetsJettyServerToStopAtShutdown() throws Exception {
    new NonStrictExpectations() {{
      server.setStopAtShutdown(true); times = 1;
    }};
    App.main(new String[]{});
  }
}
