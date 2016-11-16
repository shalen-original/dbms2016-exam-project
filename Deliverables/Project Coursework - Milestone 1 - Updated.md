# Makerspace Project Manager - MPM

## Description
The Makerspace Project Manager (MPM) is a simple application database that allows to easily manage a small-medium sized Makerspace laboratory.

This database allows to simplify the allocation of resources to current projects, review past projects and in general to provide an interaction platform between the different users and the makerspace staff for what concerns the various projects.     
For each existing project there is a list of participants, each of which has a clearly assigned role. For each project the general and infrastructure related requests, the reserved or requested infrastructure and the materials used are tracked.    
Infrastructures are divided between those which need only reservation (rooms, simple tools) and those handled by the staff (e.g. 3D printers) after a request for a specific task. Each request should be “atomic”, that is, they should contain a single task (e.g. 3D print this file), in order to allow for easier request handling/job queueing for a certain project.     
Further we choose to provide a simple, request bound ticketing/messaging system for interaction between the technician handling a request and the team that issued it. Moreover, this database provides a simple list of available materials and a detailed listing of the materials purchased by each project.

## Sample queries
* Which projects were/are done in the makerspace?
* Which of the projects are active? Which are terminated?
* Who are the people behind the projects?
* Who is the contact person to talk about a certain project? Who is responsible for a project?
* How are the roles divided?
* Is the room xyz free today? If not, which group booked it?
* Which projects are open for collaboration?
* From the staff point of view, which are the part still to print in 3D? Which have been produced in the past?
* How many units of the material xyz are now in stock?
* Which group bought that material?
* How much do 10 units of material xyz cost?

## ER Model
See the attached file `mpm-db-er-schema.pdf`

## Group members
The members of the group working on this project are:

* [Remo Scolati](mailto:remo_scolati@hotmail.com)
* [Mikel Grabocka](mailto:mgrabocka@unibz.it) 
* [Matteo Nardini](mailto:mnardini@unibz.it) 