/* 
 * Query: Lists all the projects for a given user, reporting also its role.
 * Group members: 
 * 		Remo Scolati: 	remo_scolati@hotmail.com
 * 		Mikel Grabocka: mgrabocka@unibz.it
 * 		Matteo Nardini: mnardini@unibz.it
 */

SELECT		title, project_role
FROM		MAKERSPACE_USER NATURAL JOIN PARTICIPATION NATURAL JOIN PROJECT
WHERE		name = 'Remo Scolati'
ORDER BY	title;