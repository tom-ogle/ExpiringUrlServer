package com.tomogle.handler;

import com.google.inject.Inject;
import com.tomogle.service.URIService;
import org.eclipse.jetty.proxy.ProxyServlet;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public class ExpiringUrlProxy extends ProxyServlet {

  private static final long serialVersionUID = 7534388690996690187L;

  private final URIService uriService;

  @Inject
  public ExpiringUrlProxy(final URIService uriService) {
    this.uriService = uriService;
  }

  @Override protected URI rewriteURI(final HttpServletRequest request) {
    return URI.create(uriService.lookupURI(request.getContextPath()));
  }
}
