--ACTORES
--Usuario Admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
--Ususario Cliente
INSERT INTO users(username,password,enabled) VALUES ('juanma','hola',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'juanma','client');
--Usuario Empleado
INSERT INTO users(username,password,enabled) VALUES ('miguel','hola',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'miguel','employee');
INSERT INTO employees(id, first_name, last_name, DOB, address, category, IBAN, profession, email, username) VALUES(1, 'Miguel', 'Guapo', '2000-01-01', 'Sevilla C/Tangana 1º A',1, 'ES3912341234250123456789', 'LIFE_GUARD', 'miguel_molina2@hotmail.es', 'miguel');
INSERT INTO clientes(id, first_name, last_name, DOB, address, category, IBAN, suscripcion, email, username) VALUES(1, 'Juan Manuel', 'Garcia Criado', '2000-01-01', 'C/ Pedro Sanchez', 0, 'ES3912341234250123456789', 'MORNING', 'juanma101099@gmail.com','juanma');

--PAGOS DE SUSCRIPCION
INSERT INTO pagos(id, cliente_id, f_emision, cantidad) VALUES(1, 1, '2020-11-15', 32);
INSERT INTO pagos(id, cliente_id, f_emision, cantidad) VALUES(2, 1, '2020-11-27', 40);

--BALANCE
INSERT INTO balances(id, month, year, subs, bonos, salaries) VALUES(1, 'JANUARY', '2020', 200,200,202);

--SALAS
INSERT INTO salas(id, name, aforo, descripcion, room_type) VALUES(1, 'Jacuzzi', 7, 'A large circular bath which is fitted with a device that makes the water move around.', 'LIFE_GUARD');
INSERT INTO salas(id, name, aforo, descripcion, room_type) VALUES(2, 'Relax pool', 15, 'Indoor heated pool where you can relax','LIFE_GUARD');
INSERT INTO salas(id, name, aforo, descripcion, room_type) VALUES(3, 'Sauna', 5, 'A Finnish steam bath in which the steam is provided by water thrown on hot stones' , 'MASSAGIST');
INSERT INTO salas(id, name, aforo, descripcion, room_type) VALUES(4, 'Musical jacuzzi', 3, 'Have a wonderful bath in a jacuzzi while you listen to music.', 'MASSAGIST');
INSERT INTO salas(id, name, aforo, descripcion, room_type) VALUES(5, 'Hot water pool', 7, 'Hot water', 'LIFE_GUARD');
INSERT INTO salas(id, name, aforo, descripcion, room_type) VALUES(6, 'Stone Massage', 7, 'Massage with hot volcanic rocks', 'MASSAGIST');

--CIRCUITOS
INSERT INTO circuitos(id, name, descripcion) VALUES(1, 'Circuito1 ', 'Water circuit');
INSERT INTO circuitos(id, name,descripcion) VALUES(2, 'Circuito2 ', 'Series of pools');

INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(1,1);
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(1,2);
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(1,3);
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(2,2);
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(2,3);

--SALARIOS EMPLEADOS
INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(1, 1, '2013-01-02', '2013-01-29', 50, 700);
INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(2, 1, '2013-02-01', '2013-01-24', 50, 1100);

INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(5, 1, '2020-11-04', '2020-11-15' , 34, 2374);
INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(6, 1, '2020-11-16', '2020-11-20' , 34, 1000);

--HORARIOS -> SESIONES -> CITAS
INSERT INTO horario(id,fecha,employee_id) VALUES (1,'2020-12-05',1);
INSERT INTO horario(id,fecha,employee_id) VALUES (2,'2020-12-06',1);
INSERT INTO horario(id,fecha,employee_id) VALUES (3,'2021-01-25',1);
INSERT INTO horario(id,fecha,employee_id) VALUES (4,'2021-02-23',1);
INSERT INTO horario(id,fecha,employee_id) VALUES (5,'2021-02-25',1);
INSERT INTO horario(id,fecha,employee_id) VALUES (6,'2021-02-27',1);

INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (1,'10:00','12:00',1,1);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (2,'12:00','14:00',2,1);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (3,'10:00','12:00',4,1);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (5,'10:00','12:00',1,3);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (6,'10:00','12:00',2,4);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (7,'12:00','14:00',2,4);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (8,'15:00','17:00',5,5);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (9,'17:00','19:00',2,5);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (10,'09:00','11:00',2,6);
INSERT INTO sesion(id,hora_inicio,hora_fin,sala_id,horario_id) VALUES (11,'11:00','13:00',2,6);

INSERT INTO citas(id,cliente_id, sesion_id) VALUES (1,1,5);
INSERT INTO citas(id,cliente_id, sesion_id) VALUES (2,1,7);

--Datos para balance Enero 2021
--(Modifica la etiqueta @Schedue en Balance Service para poder ver en tiempo real que se crea automaticamente, si no tendrás que esperar al dia 1 del mes)
INSERT INTO pagos(id, cliente_id, f_emision, cantidad) VALUES(3, 1, '2021-01-15', 32);
INSERT INTO pagos(id, cliente_id, f_emision, cantidad) VALUES(4, 1, '2021-01-27', 40);

INSERT INTO bonos(id, codigo, precio, date_start, date_end, descripcion, usado) VALUES (1,  'QWERT1', 5,  '2021-01-03', '2021-01-04', 'Token for a Spa session.', 1);

INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(7, 1, '2021-01-02', '2021-01-29', 50, 700);