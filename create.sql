CREATE TABLE Horse (id IDENTITY PRIMARY KEY, name VARCHAR(25), imgPath TEXT, minSpeed DOUBLE, maxSpeed DOUBLE, isDeleted BOOLEAN);
CREATE TABLE Jockey (id IDENTITY PRIMARY KEY, name VARCHAR(25), talent DOUBLE, age INTEGER, isDeleted BOOLEAN);
CREATE TABLE Race (id IDENTITY PRIMARY KEY, horse BIGINT REFERENCES Horse, jockey BIGINT REFERENCES Jockey, talent DOUBLE, speed DOUBLE, luckyNumber DOUBLE);

INSERT INTO Horse (name, minSpeed, maxSpeed, isDeleted) VALUES ('Anton', 74.38, 79.58, false);
INSERT INTO Horse (name, minSpeed, maxSpeed, isDeleted) VALUES ('Rudi', 60.54, 95.37, false);

INSERT INTO Jockey (name, talent, age, isDeleted) VALUES ('Rüdiger', 4858.0, 28, false);
INSERT INTO Jockey (name, talent, age, isDeleted) VALUES ('Rudolf', -374.5, 68, false);