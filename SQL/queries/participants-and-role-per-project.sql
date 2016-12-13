/* 
 * Query: lists all the participants with their role for a given project
 * Note: changing the string 'Arduino Worskhop' with the name of another
 		 project allows to see the members of that project.
 * Group members: 
 * 		Remo Scolati: 	remo_scolati@hotmail.com
 * 		Mikel Grabocka: mgrabocka@unibz.it
 * 		Matteo Nardini: mnardini@unibz.it
 */

SELECT		name, project_role
FROM		PROJECT NATURAL JOIN MAKERSPACE_USER NATURAL JOIN PARTICIPATION
WHERE		title = 'Arduino Workshop';


--Another possible solution:
/*
SELECT		u.name, part.project_role
FROM		PROJECT p, MAKERSPACE_USER u, PARTICIPATION part
WHERE		p.project_id = part.project_id 
			AND part.user_id = u.user_id 
			AND p.title = 'Arduino Workshop';
*/