/* 
 * Query: Lists all the materials bought a given project
 * Group members: 
 * 		Remo Scolati: 	remo_scolati@hotmail.com
 * 		Mikel Grabocka: mgrabocka@unibz.it
 * 		Matteo Nardini: mnardini@unibz.it
 */

SELECT		name, units, units_of_measure, unitary_price, total_price
FROM		PROJECT NATURAL JOIN PURCHASE INNER JOIN MATERIAL ON (PURCHASE.material_id = MATERIAl.material_id)
WHERE		title = 'Arduino Workshop';