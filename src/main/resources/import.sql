INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO users (name, last_name, email, username, password, address, phone, enabled) VALUES ('John', 'Doe', 'jdoe@gmail.com', 'jdoe', '$2y$12$Vq5XlwHBcuNWqyrRIoO3Eu.AYlpX6zLXUawynnju13z49U0He5NYy', 'San Salvador', '7845 0956', true);
INSERT INTO users (name, last_name, email, username, password, address, phone, enabled) VALUES ('Juan', 'Perez', 'jperez@gmail.com', 'jperez', '$2y$12$Vq5XlwHBcuNWqyrRIoO3Eu.AYlpX6zLXUawynnju13z49U0He5NYy', 'Santa Ana', '7234 5434', true);

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);

INSERT INTO operations (operation) VALUES('RENT');
INSERT INTO operations (operation) VALUES('SALE');
INSERT INTO operations (operation) VALUES('NEW');

INSERT INTO movies (title, description, rental_price, sale_price, availability) VALUES ('Gladiator', 'A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.', 2.99, 3.00, true);
INSERT INTO movies (title, description, rental_price, sale_price, availability) VALUES ('Back to the Future', 'Marty McFly, a 17-year-old high school student, is accidentally sent thirty years into the past in a time-traveling DeLorean invented by his close friend, the eccentric scientist Doc Brown.', 3.99, 4.00, true);

INSERT INTO likes(movie_id, user_id) VALUES(1, 1);