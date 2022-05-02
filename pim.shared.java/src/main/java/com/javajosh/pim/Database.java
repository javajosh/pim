package com.javajosh.pim;

import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Database Class represents a running database, to which we want to connect. We can even work to keep track of different database
 * states, past present and future. Rather than use a tool like Liquibase to keep your database at a particular DDL level, I propose
 * that we model our database as a monotonically increasing system, a list of DDL strings, but that a client program can productively
 * interface with a database at any DDL level. And it should be able to do this on the fly.<p>
 *
 * We can do this by adopting a simple policy of using the "best possible ddl level" for every request. So this means that
 * we must support two in-flight requests, each of which can be using different assumptions about the same database, until they complete.
 * This requirement puts a constraint on our DDL, btw, implying that DDL changes will only add and never delete tables and columns.
 * We need to add something to the relational model, in particular we need a richer metadata model, to allow us to associate more
 * information with a column. In particular, bare values almost certainly have no place in a database. but this highlights how
 * much data simply is NOT independent data, but rather highly unreliable dependent data reported by people who are, to put it
 * mildly, crazy.<p>
 *
 * We limit ourselves to the following DDL statements:
 * <ol>
 *   <li>CREATE TABLE [server.schema.table]
 *   <li>ALTER TABLE [server.schema.table] ADD COLUMN [column] (some further guidance on datatypes)
 * </ol>
*/
public class Database {
  static final String JDBC = "jdbc:postgresql://localhost:7070/javajosh?currentSchema=javajosh_pim";
  static final String USER = "postgres";
  static final String PASS = "foobar";
  static final String PING = "select 1";
  static List<String> ddl = new ArrayList<>(); // "What I know apriori about this database" This should change over time.
  private final String jdbc;
  private final String user;
  private final String pass;
  volatile Connection conn; // usefully navigate into and observe, as with a debugger

  static {
    ddl.add("""
        id         serial primary key,
        shortname  varchar(10) not null,
        name       text,
        purchased  date,
        price_paid numeric(2),
        at_home    boolean
      """);
  }

  public static void main(String[] args) throws InterruptedException {
    String time = getTimeString();
    String program = Database.class.getCanonicalName() + "(" + Arrays.toString(args) + ")";
    String user = System.getenv("USERNAME");
    System.out.println("====================================================================================");
    System.out.format("Hello from %s on %s by %s\n\n", program, time, user);
    Database db = new Database(JDBC, USER, PASS); // hopefully this blocks!
    System.out.println(db.getAllItems().get(0).toString());
    Thread.sleep(20*1000);
    System.out.println(db.getAllItems().get(0).toString());
  }

  Database(String jdbc, String user, String pass) {
    this.jdbc = jdbc;
    this.user = user;
    this.pass = pass;
    this.conn = tryToConnect();
  }

  boolean isConnected() {
    return this.conn != null;
  }

  Connection tryToConnect() {
    if (isConnected()) return this.conn;
    // As of JDBC 4.0, all drivers that are found in the classpath are automatically loaded. - no Class.forName() needed.
    Connection c = null;
    try {
      c = DriverManager.getConnection(jdbc, user, pass);
      Statement stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(PING);
      // TODO: verify metadata and make sure we know the lay of the land.
      rs.next();
      System.out.println("connected without error.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return c;
  }

  public List<Item> getAllItems() {
    List<Item> result = new ArrayList<>();
    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("""
          select id, shortname, name, purchased, price_paid, at_home from item
        """);
      boolean noRecords = true;
      while (rs.next()) {
        noRecords = false;

        result.add(new Item(
          rs.getInt("id"),
          rs.getString("shortname"),
          rs.getString("name"),
          rs.getDate("purchased").toLocalDate().atStartOfDay(),
          (int) rs.getFloat("price_paid") * 100,
          rs.getBoolean("at_home"))
        );
      }

      if (noRecords) {
        System.out.println("No records found.");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  /**
   * @return the offset we are at.
   */
  int inspect() {
    return 0;
  }

  private final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss z");

  /**
   * Human readable, and a valid string value for a 'date' field in postgres.
   *
   * @return A string representing of local time in yyyy/MM/dd HH:mm:ss z format.
   */
  private static String getTimeString() {
    ZonedDateTime zdt = ZonedDateTime.now( // wall-clock time used by the people of a certain region (a time zone).
      ZoneId.systemDefault()   // JVM’s current default time zone. Mutable during runtime. If important, confirm with the user.
    );
    return dtf.format(zdt);
  }
}
