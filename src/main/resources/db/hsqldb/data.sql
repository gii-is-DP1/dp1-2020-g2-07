-- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');
-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO users(username,password,enabled) VALUES ('juanma','hola',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (4,'juanma','owner');


INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (11, 'Juan Manuel', 'Garcia Criado', '24 Mordor', 'Tierra Media', '0123456789', 'owner1');

INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Federico', '2020-10-19', 1, 11);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

INSERT INTO bonos(id, codigo, precio, date_start, date_end, descripcion, usado) VALUES (1,  'QWERT1', 5,  '2020-11-03','2020-11-11', 'Token for a free Spa session.', 1);
INSERT INTO bonos(id, codigo, precio, date_start, date_end, descripcion, usado) VALUES (2, 'SADYT3', 3, '2020-11-03','2020-11-11', 'Token for a free Cardio session.',1);

INSERT INTO clientes(id, nick, nombre, apellidos, direccion, categoria, IBAN, suscripcion) VALUES(1, 'juanma', 'Juan Manuel', 'García Criado', 'C/ Pedro Sanchez', 0, 'ES72 2923 9043 2575 5091', 'MATINAL');
INSERT INTO clientes(id, nick, nombre, apellidos, direccion, categoria, IBAN, suscripcion) VALUES(2, 'faker', 'Pepe', 'Díaz Oslo', 'Sevilla C/Inventada 2',0, 'ES72 2223 2334 3422', 'VESPERTINO');
INSERT INTO clientes(id, nick, nombre, apellidos, direccion, categoria, IBAN, suscripcion) VALUES(3, 'mgc99', 'Manuel', 'Osuna Moreno', 'Sevilla C/Tangana 1º A',0, 'ES72 6789 4839 3928 2143', 'PREMIUM');

INSERT INTO pagos(id, cliente_id, f_emision, cantidad) VALUES(1, 1, '2020-11-15', 32);
INSERT INTO pagos(id, cliente_id, f_emision, cantidad) VALUES(2, 1, '2020-11-27', 100);

INSERT INTO salas(id, name, aforo, descripcion) VALUES(1, 'Jacuzzi', 7, 'A large circular bath which is fitted with a device that makes the water move around.');
INSERT INTO salas(id, name, aforo, descripcion) VALUES(2, 'Relax pool', 15, 'Indoor heated pool where you can relax');
INSERT INTO salas(id, name, aforo, descripcion) VALUES(3, 'Sauna', 5, 'A Finnish steam bath in which the steam is provided by water thrown on hot stones');
INSERT INTO salas(id, name, aforo, descripcion) VALUES(4, 'Musical jacuzzi', 3, 'Have a wonderful bath in a jacuzzi while you listen to music.');
INSERT INTO salas(id, name, aforo, descripcion) VALUES(5, 'Hot water pool', 7, 'Hot water');
INSERT INTO circuitos(id, name, descripcion) VALUES(1, 'Circuito1 ', 'Water circuit');
INSERT INTO circuitos(id, name,descripcion) VALUES(2, 'Circuito2 ', 'Series of pools');
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(1,1);
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(1,2);
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(1,3);
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(2,2);
INSERT INTO REL_CIRCUITO_SALAS(FK_Circuito,FK_Sala) VALUES(2,3);

INSERT INTO REL_BONOS_SALAS(FK_Bono,FK_Sala) VALUES(1,1);
INSERT INTO REL_BONOS_SALAS(FK_Bono,FK_Sala) VALUES(1,2);
INSERT INTO REL_BONOS_SALAS(FK_Bono,FK_Sala) VALUES(2,1);
INSERT INTO REL_BONOS_SALAS(FK_Bono,FK_Sala) VALUES(2,2);

INSERT INTO employees(id, nick, nombre, apellidos, direccion, categoria, IBAN, profession) VALUES(1, 'em1', 'Jhon', 'Smith', 'Sevilla C/Tangana 1º A',1, 'ES72 6789 4839 3928 2143', 'LIFE_GUARD');
INSERT INTO employees(id, nick, nombre, apellidos, direccion, categoria, IBAN, profession) VALUES(2, 'em2', 'Izan', 'Brent', 'Sevilla C/Ej 23',1, 'ES72 8009 1129 1089 27401', 'LIFE_GUARD');
INSERT INTO employees(id, nick, nombre, apellidos, direccion, categoria, IBAN, profession) VALUES(3, 'em3', 'Jessica', 'Wilde', 'Sevilla C/Torneo 19 3º I',1, 'ES72 0329 4543 3990 6543', 'LIFE_GUARD');



INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(1, 1, '2013-01-02', '2013-01-29', 60, 700);
INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(2, 1, '2013-02-01', '2013-01-24', 100, 1100);
INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(3, 1, '2013-01-01', '2013-01-28', 40, 530);
INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(4, 1, '2013-01-05', '2013-01-27', 78, 900);

INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(5, 1, '2020-11-04', '2020-11-15' , 34, 2374);
INSERT INTO revenue(id, employee_id, date_start, date_end, hours_worked, quantity) VALUES(6, 1, '2020-11-16', '2020-11-20' , 34, 1000);


INSERT INTO toallas(cantidad) VALUES (1);
