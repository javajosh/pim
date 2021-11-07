package com.javajosh.postgres;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

// Setup with pgadmin or psql C:\java\pgsql\14\bin> .\psql -h localhost -p 7070 -U postgres -W
public class SimpleConnectJDBC {
  // @docs: https://jdbc.postgresql.org/documentation/head/connect.html
  // Postgres default port is 5432
  static final String DB_URL = "jdbc:postgresql://localhost:7070/javajosh?currentSchema=javajosh_pim";
  static final String USER = "postgres";
  static final String PASS = "foobar";

  public static void main(String[] args) {
    // Print header
    String time = getTimeString();
    String program = SimpleConnectJDBC.class.getCanonicalName() + "(" + Arrays.toString(args) + ")";
    String user = System.getenv("USERNAME");
    System.out.println("====================================================================================");
    System.out.format("Hello from %s on %s by %s\n\n", program, time, user);

    // As of JDBC 4.0, all drivers that are found in the classpath are automatically loaded. - no Class.forName() needed.
    try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("""
        SELECT id, shortname, name, purchased, price_paid, at_home FROM item
      """);
      String ddl = """
        id         serial primary key,
        shortname  varchar(10) not null,
        name       text,
        purchased  date,
        price_paid numeric(2),
        at_home    boolean
      """; // yay for multi-line strings which hit with java 15!
      System.out.println(ddl);
      boolean noRecords = true;
      while (rs.next()) {
        noRecords = false;
        System.out.format("{\n id: %d,\n shortname: %s,\n name: %s,\n purchased: %tF,\n price_paid: %f,\n at_home: %b\n}\n\n",
          rs.getInt("id"),
          rs.getString("shortname"),
          rs.getString("name"),
          rs.getDate("purchased"),
          rs.getFloat("price_paid"),
          rs.getBoolean("at_home")
        );
      }
      if (noRecords) {
        throw new RuntimeException(program + " returned no results");
      }
    } catch (SQLException e) {
      System.out.println(program + " halted with SQLException");
      e.printStackTrace();
      return;
    }
    System.out.println(program + " completed without error.");
  }

  private static String getTimeString() {
    ZonedDateTime zdt = ZonedDateTime.now( // wall-clock time used by the people of a certain region (a time zone).
      ZoneId.systemDefault()   // JVM’s current default time zone. Mutable during runtime. If important, confirm with the user.
    );
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss z");
    return dtf.format(zdt);
  }

}
