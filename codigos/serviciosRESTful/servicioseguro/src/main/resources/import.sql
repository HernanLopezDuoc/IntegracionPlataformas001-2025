
-- Insertar roles
INSERT INTO rol (id, nombre) VALUES (1, 'ROLE_LECTOR');
INSERT INTO rol (id, nombre) VALUES (2, 'ROLE_ESCRITOR');
INSERT INTO rol (id, nombre) VALUES (3, 'ROLE_H2');

-- Insertar usuarios
INSERT INTO usuario (id, username, password) VALUES (1, 'lector', '$2a$10$iDVcP0ZjLXjY.GjQdau1beMIRU4EJSYMj7dDzagD5b6dROp3ojuG6'); -- lector123
INSERT INTO usuario (id, username, password) VALUES (2, 'escritor', '$2a$10$A7HnRoWYY8T1HBua/1YXge6R8uKejQlIq9LHSSL.4Vj3eqVnepgCu'); -- escritor123
INSERT INTO usuario (id, username, password) VALUES (3, 'h2admin', '$2a$10$rX8WswTisoQgsPrsotpRpOFCexxNPAu5tMaWrPktlqQWVIErPhg3i'); -- password: h2console

-- Asociar usuarios con roles
INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (2, 2);
INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (2, 1);
INSERT INTO usuario_roles (usuario_id, rol_id) VALUES (3, 3);

