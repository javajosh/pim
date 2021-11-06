create table inventory
(
    id      serial constraint inventory_pk primary key,
    shortname  varchar(10) not null,
    name       text,
    purchased  date,
    price_paid numeric(2),
    at_home    boolean
);

comment on column inventory.name is 'Input will be truncated without raising an error.';
comment on column inventory.price_paid is 'Decimal is an alias for numeric.';
comment on column inventory.at_home is 'Boolean fields can have 3 values, true false or null';

alter table inventory
    owner to postgres;

create unique index inventory_id_uindex
    on inventory (id);