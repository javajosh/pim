package com.javajosh.pim;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface ItemDAO {
  @SqlUpdate("insert into item (name, shortname) values (:name, :name)")
  void insert(@Bind("name") String name);

  @SqlQuery("select id, name from item where id = :id")
  @RegisterBeanMapper(Item.class)
  Item findById(@Bind("id") int id);

}
