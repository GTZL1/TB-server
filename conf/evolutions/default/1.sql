-- Server schema

-- !Ups
CREATE TABLE IF NOT EXISTS player (
                      id_player SERIAL PRIMARY KEY,
                      username varchar(255) UNIQUE NOT NULL,
                      password_hash varchar(255) NOT NULL
);

-- !Downs
DROP TABLE player;