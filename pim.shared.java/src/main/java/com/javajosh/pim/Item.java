package com.javajosh.pim;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * An Item is something that you own. This class could be auto-generated from the Item table DDL string.
 * <p>
 * com.javajosh.postgres.SimpleConnectJDBC
 */
public class Item {
  /**
   * The PostgreSQL DDL string used to produce the table that this class describes.
   * Special care should be taken that this string is consistent with the database against
   * which I am currently connected.
   */
  String ddl = """
      id         serial primary key, int
      shortname  varchar(10) not null, string
      name       text, string
      purchased  date, zonedatetime
      price_paid numeric(2), string
      at_home    boolean, boolean
    """;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getShortname() {
    return shortname;
  }

  public void setShortname(String shortname) {
    this.shortname = shortname;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getPurchased() {
    return purchased;
  }

  public void setPurchased(LocalDateTime purchased) {
    this.purchased = purchased;
  }

  public int getPrice_paid() {
    return price_paid;
  }

  public void setPrice_paid(int price_paid) {
    this.price_paid = price_paid;
  }

  public boolean isAt_home() {
    return at_home;
  }

  public void setAt_home(boolean at_home) {
    this.at_home = at_home;
  }

  Integer id;
  String shortname;
  String name;
  LocalDateTime purchased;
  Integer price_paid;
  Boolean at_home;

  public Item(Integer id, String shortname, String name, LocalDateTime purchased, int price_paid, boolean at_home) {
    this.id = id;
    this.shortname = shortname;
    this.name = name;
    this.purchased = purchased;
    this.price_paid = price_paid;
    this.at_home = at_home;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Item item = (Item) o;
    return
      Objects.equals(id, item.id) &&
        at_home == item.at_home &&
        ddl.equals(item.ddl) &&
        shortname.equals(item.shortname) &&
        Objects.equals(name, item.name) &&
        Objects.equals(purchased, item.purchased) &&
        Objects.equals(price_paid, item.price_paid)
      ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(ddl, id, shortname, name, purchased, price_paid, at_home);
  }

  @Override
  public String toString() {
    return String.format("{\n id: %d,\n shortname: %s,\n name: %s,\n purchased: %tF,\n price_paid: %s,\n at_home: %b\n}\n\n",
      id,
      shortname,
      name,
      purchased,
      price_paid,
      at_home
    );
  }
}
