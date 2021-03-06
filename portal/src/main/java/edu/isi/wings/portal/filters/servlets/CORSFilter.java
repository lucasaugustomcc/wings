package edu.isi.wings.portal.filters.servlets;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.configuration.plist.PropertyListConfiguration;

import edu.isi.wings.portal.classes.config.Config;

public class CORSFilter implements Filter{

  @Override
  public void destroy() {
    // TODO Auto-generated method stub
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    Config config = new Config();
    PropertyListConfiguration plist = config.getPortalConfiguration(
        (HttpServletRequest) request);
    HttpServletResponse res = (HttpServletResponse) response;
    String clients = plist.getString("clients");
    if(clients != null) {
      res.setHeader("Access-Control-Allow-Origin", clients);
      res.setHeader("Access-Control-Allow-Credentials", "true");
      res.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
      res.setHeader("Access-Control-Allow-Headers",
          "X-Requested-With, Content-Type, X-HTTP-Method-Override");
    }    
    chain.doFilter(request, response);
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
    // TODO Auto-generated method stub
  }

}
