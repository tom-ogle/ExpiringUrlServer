package com.tomogle;

import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import org.eclipse.jetty.server.Server;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class AppTest {

  @Test
  public void testMainStartsJettyServer(final @Mocked Server server) throws Exception {
    new NonStrictExpectations() {{
      server.start(); times = 1;
    }};
    App.main(new String[]{});
  }

  @Test
  public void testMainJoinsWithJettyServer(final @Mocked Server server) throws Exception {
    new NonStrictExpectations() {{
      server.join(); times = 1;
    }};
    App.main(new String[]{});
  }
}
