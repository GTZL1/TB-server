-- Server schema

-- !Ups
CREATE SCHEMA General;
CREATE TABLE IF NOT EXISTS General.Player (
                      idPlayer SERIAL PRIMARY KEY,
                      username varchar(255) NOT NULL,
                      passwordHash varchar(255) NOT NULL
);

-- !Downs

DROP TABLE General.Player;
DROP SCHEMA General;