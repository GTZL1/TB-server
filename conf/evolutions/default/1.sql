-- Server schema

-- !Ups
CREATE TABLE IF NOT EXISTS player (
                      id_player SERIAL PRIMARY KEY,
                      username varchar(255) UNIQUE NOT NULL,
                      password_hash varchar(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS session(
                     id_session SERIAL PRIMARY KEY,
                     idx_player BIGINT,
                     CONSTRAINT fkSessionPlayer FOREIGN KEY (idx_player) REFERENCES player (id_player) ON UPDATE CASCADE ON DELETE CASCADE
);

-- !Downs
DROP TABLE session;
DROP TABLE player;