/* 
 * Query: Computes the total usage time by all projects
 			for each free infrastructure.
 * Group members: 
 * 		Remo Scolati: 	remo_scolati@hotmail.com
 * 		Mikel Grabocka: mgrabocka@unibz.it
 * 		Matteo Nardini: mnardini@unibz.it
 */

SELECT		title, name, SUM(end_time - start_time)
FROM		PROJECT NATURAL JOIN BOOKING NATURAL JOIN FREE_INF
GROUP BY 	name, title
ORDER BY 	title, name;