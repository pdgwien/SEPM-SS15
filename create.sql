CREATE TABLE Horse (name VARCHAR(25) PRIMARY KEY, imgPath TEXT, minSpeed DECIMAL(5, 2), maxSpeed DECIMAL(5, 2), isDeleted TINYINT(1));
CREATE TABLE Jockey (name VARCHAR(25) PRIMARY KEY, talent DOUBLE, age INTEGER);
CREATE TABLE Race (id BINARY(16) PRIMARY KEY, horse VARCHAR(25) REFERENCES Horse, jockey VARCHAR(25) REFERENCES Jockey, talent DOUBLE, speed DECIMAL(5, 2), luckyNumber DECIMAL(3, 2));

INSERT INTO Horse (name, minSpeed, maxSpeed) VALUES ('Anton', 74.38, 79.58);
INSERT INTO Horse (name, minSpeed, maxSpeed) VALUES ('Rudi', 60.54, 95.37);

INSERT INTO Jockey (name, talent, age) VALUES ('Rüdiger', 4858, 28);
INSERT INTO Jockey (name, talent, age) VALUES ('Rudolf', -374, 68);