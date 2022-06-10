package com.javajosh.pim.server.quarkus;


import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

  public String greeting(String name) {
    return "yo " + name;
  }

}
