-- alter table question modify creator bigint not null;
-- alter table comment modify commentator bigint not null;
alter table question alter column creator bigint not null;
alter table comment alter column commentator bigint not null;