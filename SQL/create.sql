/* 
 * File create.sql
 * Creates the schema of the database for the Makerspace Project Manager
 * Group members: 
 *    Remo Scolati:   remo_scolati@hotmail.com
 *    Mikel Grabocka: mgrabocka@unibz.it
 *    Matteo Nardini: mnardini@unibz.it
 */

/*
    Cleaning the database
*/
DROP TABLE IF EXISTS PURCHASE CASCADE;
DROP TABLE IF EXISTS BOOKING CASCADE;
DROP TABLE IF EXISTS PARTICIPATION CASCADE;
DROP TABLE IF EXISTS PROJECT CASCADE;
DROP TABLE IF EXISTS MAKERSPACE_USER CASCADE;
DROP TABLE IF EXISTS GENERAL_ROLE CASCADE;
DROP TABLE IF EXISTS FREE_INF CASCADE;
DROP TABLE IF EXISTS TECHNICAL_INF CASCADE;
DROP TABLE IF EXISTS REQUEST CASCADE;
DROP TABLE IF EXISTS MESSAGE CASCADE;
DROP TABLE IF EXISTS MATERIAL CASCADE;

DROP TYPE IF EXISTS p_role;
DROP TYPE IF EXISTS p_status;

/*
  Creates the enumerated types used in this database. They are p_role, which describes
  the role a user has in a project, and p_status, which describes the status of a project.
*/
CREATE TYPE p_role AS ENUM ('administrator', 'collaborator', 'retired');
CREATE TYPE p_status AS ENUM ('proposed', 'active', 'completed');

/*
    The main unit of work in the Makerspace is a project.
*/
CREATE TABLE PROJECT(
  project_id INTEGER PRIMARY KEY,
  title VARCHAR(50) NOT NULL,
  description VARCHAR(500),
  status p_status NOT NULL DEFAULT 'proposed',
  seeking_collaboration BOOLEAN DEFAULT FALSE);

/*
    Each user has a general role in the Makerspace: for example, simple user, technician,
    trusted user and similar.
    A user can only be associated to one of these roles.
*/
CREATE TABLE GENERAL_ROLE(
  role_id INTEGER PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(500));  

/*
    Users are the other fundamental unit. Every project is associated to one ore more users.
  
    Foreign key user_role:
    The user_role field allow to link each user to its general role in the Makerspace. This
    role is different from the role that this user has in the different projects and describes
    its general position in the Makerspace. If a role changes ID, we want reflect this change on
    this table, therefore the foreign key has ON UPDATE CASCADE. Instead, we do not want to allow
    for the deletion of a general role, unless is is not assigned to any user.
*/
CREATE TABLE MAKERSPACE_USER(
  user_id INTEGER PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  user_role INTEGER NOT NULL,
  email VARCHAR(50) NOT NULL,
  FOREIGN KEY(user_role) REFERENCES GENERAL_ROLE(role_id)
  ON UPDATE CASCADE);

/*
    Each user participates in a certain project with a certain role. Each user can have only a single
    role for each project. We wanted to keep the "permissions" system fairly easy, because our target
    is a small-medium sized Makerspace: in this scenario, a complex role system is not required and
    would only add overhead.

    Foreign key project_id:
    Whenever a project changes id, we want that change to be applied also here, so we added ON UPDATE 
    CASCADE. We generally don't want to allow for user to delete projects, in order not to lose the
    history of the Makerspace, so this option probably will not be available in the user interface.
    However, if it really becomes necessary to delete a project (for example as a special action done by
    system administrators), we want to keep the process as easy as possible. 
    Therefore, we added ON DELETE CASCADE: in this way, it is enough to delete the project record 
    to delete also all the associated participations to that project, reducing the amount of steps to be
    done at a minimum.
  
    Foreign key user_id:
    If a user changes id, we want to update also all the associated participations, thus we added
    ON UPDATE CASCADE. However, we do not want to allow the deletion of a user if it is involved
    in some project, in order to preserve a correct history of who worked in which project. Therefore,
    we did not add ON DELETE CASCADE.
*/
CREATE TABLE PARTICIPATION(
  project_id INTEGER REFERENCES PROJECT(project_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  user_id INTEGER REFERENCES MAKERSPACE_USER(user_id) 
    ON UPDATE CASCADE,
  project_role p_role NOT NULL DEFAULT 'collaborator',
  PRIMARY KEY (project_id, user_id));

/*
    Every item available for purchase through the Makerspace infrastructure is listed in this
    table.

    The units_available field represents the quantity of a specific material that is available. It has to be
    non negative, because it can be zero if that particular material stock is exhausted. The field
    units_of_measure allows to store the measure unit of the quantity units_available. Examples are
    kg, blocks, ...
*/
CREATE TABLE MATERIAL(
  material_id INTEGER PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(500),
  unitary_price DECIMAL(9,2) NOT NULL CHECK(unitary_price>=0),
  units_of_measure VARCHAR(50) NOT NULL,
  units_available INTEGER NOT NULL CHECK(units_available>=0));

/*
    Each material can be purchased by a certain project. Each transaction regards a single
    material and a certain quantity greater than zero of that material. This project targets
    a small-medium sized Makerspace, therefore we did not want to implement a fully fledged transaction
    management system.

    Foreign key project_id:
    The two clauses ON UPDATE CASCADE and ON DELETE CASCADE are added for reasons similar to those
    explained in the description of the PARTICIPATION table.

    Foreign key material_id:
    If a materials changes id, we want that change to be performed also on this foreign key, therefore
    we added ON UPDATE CASCADE.
    However, we don't want to allow the deletion of a material if it has been purchased, even only
    once, because it would break the history of the transtactions: thus, we did not add ON DELETE CASCADE.
*/
CREATE TABLE PURCHASE(
  purchase_id INTEGER PRIMARY KEY,
  project_id INTEGER REFERENCES PROJECT(project_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  material_id INTEGER REFERENCES MATERIAL(material_id)
    ON UPDATE CASCADE,
  units INTEGER NOT NULL CHECK(units>0),
  total_price DECIMAL(9,2) NOT NULL CHECK(total_price>=0));

/*
    This table contains the list of the free infrastructures, that is, those that can be used
    directly by the groups, without the help of a technician. Examples are meeting rooms and similar.
    If a free infrastructure is currently available for booking, the field available is TRUE.
*/
CREATE TABLE FREE_INF(
  free_inf_id INTEGER PRIMARY KEY,
  available BOOLEAN DEFAULT TRUE,
  name VARCHAR(50) NOT NULL);

/*
    This table stores all the bookings for the free infrastructures. start_time is the time at
    which the reservation of this infrastructure for that project begins, end_time is the time
    at which it ends. Of course, end_time has to be later than start_time, therefore we enforced it
    with a CHECK clause.

    Foreign key project_id:
    Each booking is associated with a single project. We added ON DELETE CASCADE and ON UPDATE CASCADE
    for the reasons already explained in the description of the table PARTICIPATION.

    Foreign key free_inf_id:
    If a free infrastructure changes id, we want to update also all the bookings. Therefore, we added
    ON UPDATE CASCADE. However, we do not want to allow for deletion if that infrastructure is or
    has been reserved, so we did not add ON DELETE CASCADE.
*/
CREATE TABLE BOOKING(
  booking_id INTEGER PRIMARY KEY,
  project_id INTEGER REFERENCES PROJECT(project_id) 
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  free_inf_id INTEGER REFERENCES FREE_INF(free_inf_id) 
    ON UPDATE CASCADE,
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP NOT NULL CHECK(end_time>start_time));

/*
    This table contains the list of the technical infrastructures, that is, those that can only
    be used with the help of a technician. Examples are 3D printers, 3D scanners and so on.
    If a technical infrastructure is currently available for booking, the field available is TRUE.
*/
CREATE TABLE TECHNICAL_INF(
  technical_inf_id INTEGER PRIMARY KEY,
  available BOOLEAN DEFAULT TRUE,
  name VARCHAR(50) NOT NULL);

/*
    In order to use a technical infrastructure, a project (intended as a team) makes a request.
    Each request is "atomic", that is, contains a single request for a single technical infrastructure.
    Examples of valid requests are "3D print this model" or "3D scann this object".
  
    Foreign key project_id:
    Each request is associated to a single project. When the id of a project is updated, we want to
    reflect this change also on this foreign key, so we used ON UPDATE CASCADE. For reasons similar
    to those written on the PARTICIPATION table, we also added ON DELETE CASCADE. Deleting a project
    is not an action that can commonly be done by a user, but when it has to be performed, we want to keep
    the number of required steps at minimum, that is, simply deleting the project record.

    Foreign key techincal_inf_id:
    If a technical infrastructure changes id, we want to update also all the requests for that
    infrastructure. Therefore, we added ON UPDATE CASCADE. However, we do not want to allow 
    for deletion if that infrastructure is or has been requested, so we did not add ON DELETE CASCADE.

    Foreign key handled_by_user:
    If a user changes id, we want to update also all the associated requests, thus we added
    ON UPDATE CASCADE. However, we do not want to allow the deletion of a user if it is involved
    in some requests, in order to preserve a correct history of who worked in which project. Therefore,
    we did not add ON DELETE CASCADE.
*/
CREATE TABLE REQUEST(
  request_id INTEGER PRIMARY KEY,
  title VARCHAR(50) NOT NULL,
  project_id INTEGER NOT NULL,
  technical_inf_id INTEGER NOT NULL,
  handled_by_user INTEGER NOT NULL,
  FOREIGN KEY(project_id) REFERENCES PROJECT(project_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  FOREIGN KEY(technical_inf_id) REFERENCES TECHNICAL_INF(technical_inf_id)
  ON UPDATE CASCADE,
  FOREIGN KEY(handled_by_user) REFERENCES MAKERSPACE_USER(user_id)
  ON UPDATE CASCADE);

/*
    This tables allows to implement a basic ticketing system for each request. Every message is linked
    to a single request and has a certain message associated to it. Each message is associated with the
    timestamp in which it was issued, in order to be able to recostruct the correct sequence of messages.

    Foreign key request_id:
    When the id of a request is updated, we want to reflect the change on the associated messages. Therefore,
    we added ON UPDATE CASCADE. If a request is deleted, also all the messages associated to that request
    should be deleted, thus ON DELETE CASCADE.
*/
CREATE TABLE MESSAGE(
  message_id INTEGER PRIMARY KEY,
  message_text TEXT NOT NULL,
  request_id INTEGER NOT NULL,
  message_time TIMESTAMP NOT NULL,
  message_author INTEGER NOT NULL,
  FOREIGN KEY(request_id) REFERENCES REQUEST(request_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  FOREIGN KEY(message_author) REFERENCES MAKERSPACE_USER(user_id)
  ON UPDATE CASCADE);