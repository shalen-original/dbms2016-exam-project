/* 
 * Query: lists all the participants with their role for all projects
 * Group members: 
 * 		Remo Scolati: 	remo_scolati@hotmail.com
 * 		Mikel Grabocka: mgrabocka@unibz.it
 * 		Matteo Nardini: mnardini@unibz.it
 */

SELECT		name, project_role, title
FROM		PROJECT NATURAL JOIN MAKERSPACE_USER NATURAL JOIN PARTICIPATION
ORDER BY 	name, title;


--Another possible solution:
/*
SELECT		u.name, part.project_role, p.title
FROM		PROJECT p, MAKERSPACE_USER u, PARTICIPATION part
WHERE		p.project_id = part.project_id 
			AND part.user_id = u.user_id
ORDER BY	u.name, p.title;
*/