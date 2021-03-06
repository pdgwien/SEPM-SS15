CREATE TABLE IF NOT EXISTS Horse (id IDENTITY PRIMARY KEY, name VARCHAR(25), imgPath TEXT, minSpeed DOUBLE, maxSpeed DOUBLE, isDeleted BOOLEAN);
CREATE TABLE IF NOT EXISTS Jockey (id IDENTITY PRIMARY KEY, name VARCHAR(25), talent DOUBLE, age INTEGER, isDeleted BOOLEAN);
CREATE TABLE IF NOT EXISTS Race (id UUID, horse BIGINT REFERENCES Horse, jockey BIGINT REFERENCES Jockey, talent DOUBLE, speed DOUBLE, luckyNumber DOUBLE, rank INTEGER, PRIMARY KEY(id, horse, jockey));
