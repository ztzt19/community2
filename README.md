##社区

##资料
[Spring 文档](https://spring.io/guides)
[Spring Web](https://spring.io/guides/gs/serving-web-content/)
[es](https://elasticsearch.cn/explore)
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)
[Bootstrap](https://v3.bootcss.com/getting-started/)
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
[Spring](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)
[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)

##工具
[Git](https://git-scm.com/download)
[Visual Paradigm](https://www.visual-paradigm.com) 
[flyway](https://flywaydb.org/documentation/getstarted/firststeps/maven)
[Lombok](https://projectlombok.org/)

#脚本
```sql
create table USER
(
	ID INT auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);

create table comment
(
	id bigint,
	parent_id bigint not null,
	type int not null,
	commentator int not null,
	gmt_create bigint not null,
	gmt_modefied bigint not null,
	like_count bigint default 0,
	constraint comment_pk
		primary key (id)
);
comment on column comment.parent_id is '父类ID';
comment on column comment.type is '父类类型';
comment on column comment.commentator is '评论人ID';
comment on column comment.gmt_create is '创建时间';
comment on column comment.gmt_modefied is '更新时间';
comment on column comment.like_count is '点赞数';
```
```bash
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```