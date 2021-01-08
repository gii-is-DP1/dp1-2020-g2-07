-- One admin user
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One client user
INSERT INTO users(username,password,enabled) VALUES ('juanma','hola',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'juanma','client');

INSERT INTO users(username,password,enabled) VALUES ('miguel','hola',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'miguel','employee');
INSERT INTO employees(id, first_name, last_name, address, category, IBAN, profession, email, username) VALUES(1, 'Jhon', 'Smith', 'Sevilla C/Tangana 1º A',1, 'ES3912341234250123456789', 'LIFE_GUARD', 'miguel_molina2@hotmail.es', 'miguel');

INSERT INTO clientes(id, first_name, last_name, address, category, IBAN, suscripcion, email, username) VALUES(1, 'Juan Manuel', 'Garcia Criado', 'C/ Pedro Sanchez', 0, 'ES3912341234250123456789', 'MORNING', 'juanma101099@gmail.com','juanma');

/*INSERT INTO clientes(id, first_name, last_name, address, category, IBAN, suscripcion) VALUES(2, 'Pepe', 'Díaz Oslo', 'Sevilla C/Inventada 2',0, 'ES72 2223 2334 3422', 'VESPERTINO');
INSERT INTO clientes(id, first_name, last_name, address, category, IBAN, suscripcion) VALUES(3, 'Manuel', 'Osuna Moreno', 'Sevilla C/Tangana 1º A',0, 'ES72 6789 4839 3928 2143', 'PREMIUM');
*/

/*INSERT INTO bonos(id, codigo, precio, date_available, descripcion, usado) VALUES (1,  'QWERT1', 5,  '2020-11-03', 'Token for a free Spa session.', 1);
INSERT INTO bonos(id, codigo, precio, date_available, descripcion, usado) VALUES (2, 'SADYT3', 3, '2020-11-03', 'Token for a free Cardio session.',1);
*/
INSERT INTO pagos(id, cliente_id, f_emision, cantidad) VALUES(1, 1, '2020-11-15', 32);
INSERT INTO pagos(id, cliente_id, f_emision, cantidad) VALUES(2, 1, '2020-11-27', 40);

INSERT INTO balances(id, month, year, subs, bonos, salaries,mante) VALUES(1, 'JANUARY', '2020', 200,200,202,200);

INSERT INTO salas(id, name, aforo, descripcion, room_type) VALUES(1, 'Jacuzzi', 7, 'A large circular bath which is fitted with a device that makes the water move around.', 'MASSAGIST');
INSERT INTO salas(id, name, aforo, descripcion, room_type) VALUES(2, 'Relax pool', 15, 'Indoor heated pool where you can relax','LIFE_GUARD');
INSERT INTO salas(id, name, aforo, descripcion, room_type) VALUES(3, 'Sauna', 5, 'A Finnish steam bath in which the steam is provided by water thrown on hot stones' , 'MASSAGIST');
INSERT INTO salas(id, name, aforo, descripcion, room_type) VALUES(4, 'Musical jacuzzi', 3, 'Have a wonderful bath in a jacuzzi while you listen to music.', 'MASSAGIST');
INSERT INTO salas(id, name, aforo, descripcion, room_type) VALUES(5, 'Hot water pool', 7, 'Hot water', 'MASSAGIST');

INSERT INTO circuitos(id, name, descripcion) VALUES(1, 'Circuito1 ', 'Water circuit');
INSERT INTO circuitos(id, name,descripcion) VALUES(2, 'Circuito2 ', 'Series of pools');

INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(1,1);
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(1,2);
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(1,3);
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(2,2);
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(2,3);

INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(1, 1, '2013-01-02', '2013-01-29', 50, 700);
INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(2, 1, '2013-02-01', '2013-01-24', 50, 1100);

INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(5, 1, '2020-11-04', '2020-11-15' , 34, 2374);
INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(6, 1, '2020-11-16', '2020-11-20' , 34, 1000);

INSERT INTO horario(id,fecha,employee_id) VALUES (1,'2020-12-05',1);
INSERT INTO horario(id,fecha,employee_id) VALUES (2,'2020-12-05',1);
INSERT INTO horario(id,fecha,employee_id) VALUES (3,'2020-12-06',1);
INSERT INTO horario(id,fecha,employee_id) VALUES (4,'2021-01-25',1);

INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (1,'10:00','12:00',1,1);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (2,'12:00','14:00',2,1);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (3,'10:00','12:00',4,1);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (4,'10:00','12:00',3,2);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (5,'10:00','12:00',1,4);

INSERT INTO citas(id,cliente_id, sesion_id) VALUES (1,1,5);
