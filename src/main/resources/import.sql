
INSERT INTO departments (id, department_name, manager) VALUES (1,'COMUNICACION', 'Yadira Mesa');
INSERT INTO departments (id, department_name, manager) VALUES (2,'REDES', 'Ernesto Menchaca');
INSERT INTO departments (id, department_name, manager) VALUES (3,'REPORTEROS', 'Yoanny Duardo');
INSERT INTO departments (id, department_name, manager) VALUES (4,'REDACCION', 'Tanía Cruz');
INSERT INTO departments (id, department_name, manager) VALUES (5,'MONITOREO', 'Osmar Esteñez');

INSERT INTO items (stock_number, electric, type, description, price, id_department, broken, withdrawn) VALUES (2012, true, 'MESA', 'Una mesa de madera', 100.00, 1, false, false);
INSERT INTO items (stock_number, electric, type, description, price, id_department, broken, withdrawn) VALUES (1234, false, 'SILLA', 'Una silla de plástico', 50.00, 1, false, false);
INSERT INTO items (stock_number, electric, type, description, price, id_department, broken, withdrawn) VALUES (6520, true, 'SPLIT', 'Un aire acondicionado', 1500.00, 3, false, false);
INSERT INTO items (stock_number, electric, type, description, price, id_department, broken, withdrawn) VALUES (5418, false, 'BACKUP', 'Un disco duro externo', 100.00, 4, false, false);
INSERT INTO items (stock_number, electric, type, description, price, id_department, broken, withdrawn) VALUES (4039, true, 'PC', 'Una computadora portátil', 1000.00, 5, false, false);
INSERT INTO items (stock_number, electric, type, description, price, id_department, broken, withdrawn) VALUES (6739, true, 'PC', 'Una computadora de escritorio', 10000.00, 1, false, false);
INSERT INTO items (stock_number, electric, type, description, price, id_department, broken, withdrawn) VALUES (1520, true, 'PC', 'Una computadora de escritorio moderna', 15000.00, 1, false, false);


INSERT INTO usuarios (name, last_name, username, email, password, role, enabled) VALUES ('Juan', 'Pérez', 'juan', 'juanperez@example.com', '$2y$10$5lMHWk/jPttQn8Jgzr.j9Ol1y3iy430QTHbK2ArVahMIxt7BVNBUO', 'USER', true);