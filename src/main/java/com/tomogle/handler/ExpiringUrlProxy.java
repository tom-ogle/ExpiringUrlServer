package com.tomogle.handler;

import org.eclipse.jetty.proxy.ProxyServlet;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public class ExpiringUrlProxy extends ProxyServlet {
  private static final long serialVersionUID = 7534388690996690187L;

  @Override protected URI rewriteURI(final HttpServletRequest request) {
    return URI.create("http://www.google.com");
  }
}
