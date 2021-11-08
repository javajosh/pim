package com.javajosh.pim;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface ItemDAO {
  @SqlUpdate("update item set name=:name, shortname=:name where id = :id")
  boolean updateItem(@Bind("id") int id, @Bind("name") String name);

  @SqlQuery("select id, name from item where id = :id")
  @RegisterBeanMapper(Item.class)
  Item findById(@Bind("id") int id);

}
