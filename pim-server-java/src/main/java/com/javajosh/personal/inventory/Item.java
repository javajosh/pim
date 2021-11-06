package com.javajosh.personal.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
  private long id;
  private String name;

  public Item() { /* Public required by Jackson deserialization */
  }

  public Item(long id, String name) {
    this.id = id;
    this.name = name;
  }

  @JsonProperty
  long getId() {
    return this.id;
  }

  @JsonProperty
  String getName() {
    return this.name;
  }
}
