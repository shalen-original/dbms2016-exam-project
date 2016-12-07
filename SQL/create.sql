/* TITLE GOES HERE
 * DESCRIPTION?
 * AUTHORS (and stuff) 
 */
 
drop table PARTICIPATES;
drop table USER_ROLE;
drop table BUYS;
drop table BOOKS;
drop table MAKES_REQ;
drop table BOUND_TO;
drop table HAS_MESSAGE;
drop table HANDLES;

drop table PROJECT;
drop table M_USER;
drop table GEN_ROLE;
drop table FREE_INF;
drop table TECHNICAL_INF;
drop table REQUEST;
drop table MESSAGE;
drop table MATERIAL;

create table PROJECT(
  project_id integer primary key,
  title varchar(30) not null,
  description varchar(100),
  status varchar(30) not null);

create table M_USER(
  user_id integer primary key,
  name varchar(30) not null,
  email varchar(30));
  
create table GEN_ROLE(
  role_id integer primary key,
  name varchar(30) not null,
  description varchar(30));

create table MATERIAL(
  mat_id integer primary key,
  name varchar(30) not null,
  description varchar(30),
  price decimal(9,2) not null check(price>=0),
  units integer not null check(units>0));
  
create table FREE_INF(
  fi_id integer primary key,
  name varchar(30) not null);
  
create table TECHNICAL_INF(
  ti_id integer primary key,
  name varchar(30) not null);
  
create table REQUEST(
  req_id integer primary key,
  title varchar(30) not null);
  
create table MESSAGE(
  mess_id integer primary key,
  m_text text);

/* NOTE: Cascade to adapt where necessary 
   (will open discussion in Google doc) */

create table PARTICIPATES(
  project_id integer references PROJECT
    on update cascade,
  user_id integer references M_USER 
    on update cascade
    on delete cascade,
  project_role varchar(30) not null,
  primary key (project_id, user_id));
  
create table USER_ROLE(
  user_id integer references M_USER 
    on update cascade
    on delete cascade,
  role_id integer references GEN_ROLE
    on update cascade
    on delete cascade,
  primary key (user_id, role_id));

create table BUYS(
  project_id integer references PROJECT
    on update cascade,
  mat_id integer references MATERIAL
    on update cascade,
  units integer not null,
  primary key (project_id, mat_id));
  
create table BOOKS(
  project_id integer references PROJECT 
    on update cascade,
  fi_id integer references FREE_INF 
    on update cascade
    on delete cascade,
  start_time date not null,
  end_time date not null,
  primary key (project_id, fi_id));
  
create table MAKES_REQ(
  req_id integer references REQUEST 
    on update cascade,
  project_id integer references PROJECT 
    on update cascade,
  primary key (req_id, project_id));
  
create table BOUND_TO(
  req_id integer references REQUEST 
    on update cascade,
  ti_id integer references TECHNICAL_INF 
    on update cascade
    on delete cascade,
  primary key (req_id, ti_id));
  
create table HAS_MESSAGE(
  req_id integer references REQUEST 
    on update cascade,
  mess_id integer references MESSAGE 
    on update cascade,
  primary key (mess_id, req_id));
  
create table HANDLES(
  user_id integer references M_USER 
    on update cascade
    on delete cascade,
  req_id integer references REQUEST 
    on update cascade,
  primary key (user_id, req_id));