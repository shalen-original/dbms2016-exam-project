/* 
 * Query: finds the administrator(s) for all projects.
 * Group members: 
 * 		Remo Scolati: 	remo_scolati@hotmail.com
 * 		Mikel Grabocka: mgrabocka@unibz.it
 * 		Matteo Nardini: mnardini@unibz.it
 */

SELECT		name, title
FROM		MAKERSPACE_USER NATURAL JOIN PARTICIPATION NATURAL JOIN PROJECT
WHERE		project_role = 'administrator'
ORDER BY 	name, title;