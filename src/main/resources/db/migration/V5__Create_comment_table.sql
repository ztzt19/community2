create table comment
(
	id bigint primary key,
	parent_id bigint not null,
	type int not null,
	commentator int not null,
	gmt_create bigint not null,
	gmt_modefied bigint not null,
	like_count bigint default 0
);


