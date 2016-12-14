/* 
 * Query: For each free infrastructure, shows if it is free or booked in the given time interval. 
 			If it is booked, the queri also shows who booked it and for which time interval.
 * Group members: 
 * 		Remo Scolati: 	remo_scolati@hotmail.com
 * 		Mikel Grabocka: mgrabocka@unibz.it
 * 		Matteo Nardini: mnardini@unibz.it
 */

SELECT FREE_INF.name, title, start_time, end_time
FROM FREE_INF LEFT OUTER JOIN (
SELECT		title, name, start_time, end_time, fi.free_inf_id
FROM		PROJECT p, FREE_INF fi, BOOKING b
WHERE		p.project_id = b.project_id
			AND b.free_inf_id = fi.free_inf_id
            AND fi.available = TRUE
            AND (
                (b.start_time BETWEEN '2016-1-16 08:10:00' AND '2016-1-18 15:10:00')
            	OR (b.end_time BETWEEN '2016-1-16 08:10:00' AND '2016-1-18 15:10:00')
            	OR (b.start_time < '2016-1-16 08:10:00' AND b.end_time > '2016-1-18 15:10:00')
            )) AS T ON (FREE_INF.free_inf_id = T.free_inf_id);