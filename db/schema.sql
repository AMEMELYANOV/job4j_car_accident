DROP TABLE IF EXISTS accident_types CASCADE;
CREATE TABLE accident_types (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255));
	
DROP TABLE IF EXISTS accidents CASCADE;
CREATE TABLE accidents (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255),
	text VARCHAR(255),
	address VARCHAR(255),
	accident_type_id INT,
	FOREIGN KEY (accident_type_id) REFERENCES accident_types(id)
);

DROP TABLE IF EXISTS rules CASCADE;
CREATE TABLE rules (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255)
);

DROP TABLE IF EXISTS accidents_rules CASCADE;
CREATE TABLE accidents_rules (
	accident_id INT,
	rules_id int,
	PRIMARY KEY (rules_id, accident_id),
	FOREIGN KEY (accident_id) REFERENCES accidents(id),
	FOREIGN KEY (rules_id) REFERENCES rules(id)
);

insert into accident_types (name) VALUES ('Две машины'), ('Машина и человек'), ('Машина и велосипед');

insert into accidents (name, text, address, accident_type_id) VALUES
	('name1', 'text1', 'address1', 1),
	('name2', 'text2', 'address2',2);

insert into rules (name) VALUES ('Статья. 1'), ('Статья. 2'), ('Статья. 3');

insert into accidents_rules (accident_id, rules_id) VALUES 
	(1, 1),
	(2, 2),
	(2, 3);