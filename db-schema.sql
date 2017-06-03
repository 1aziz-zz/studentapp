CREATE TABLE courses
(
  id            INT(2) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name          VARCHAR(25)        NOT NULL,
  category      VARCHAR(25)        NOT NULL,
  location      VARCHAR(25)        NOT NULL,
  description   TEXT               NOT NULL
);

INSERT INTO courses (id, name, category, location, description)
VALUES (1,'Patterns and Frameworks', 'Java Programming', 'Nijenoord 1, Utrecht', 'Great course!');
