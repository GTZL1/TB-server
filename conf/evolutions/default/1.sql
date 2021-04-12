-- Server schema

-- !Ups
CREATE TABLE IF NOT EXISTS player (
                      id_player SERIAL PRIMARY KEY,
                      username varchar(255) UNIQUE NOT NULL,
                      password_hash varchar(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS session(
                     id_session SERIAL PRIMARY KEY,
                     idx_player BIGINT NOT NULL,
                     CONSTRAINT fk_session_player FOREIGN KEY (idx_player) REFERENCES player (id_player) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS card(
                    id_card SERIAL primary key,
                    name varchar(255) not null,
                    attack_points int not null,
                    life_points int not null,
                    max_number_deck int not null
);
CREATE TABLE IF NOT EXISTS unit_card(
                    idx_card BIGINT NOT NULL,
                    CONSTRAINT fk_card_unit_card FOREIGN KEY (idx_card) REFERENCES card (id_card) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS spy_card(
                                        idx_card BIGINT NOT NULL,
                                        CONSTRAINT fk_card_spy_card FOREIGN KEY (idx_card) REFERENCES card (id_card) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS vehicle_card(
                                        idx_card BIGINT NOT NULL,
                                        CONSTRAINT fk_card_vehicle_card FOREIGN KEY (idx_card) REFERENCES card (id_card) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS power(
                    id_power SERIAL PRIMARY KEY,
                    name VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS hero_card(
                                        idx_card BIGINT NOT NULL,
                                        idx_power BIGINT NOT NULL,
                                        CONSTRAINT fk_card_hero_card FOREIGN KEY (idx_card) REFERENCES card (id_card) ON UPDATE CASCADE ON DELETE CASCADE,
                                        CONSTRAINT fk_hero_card_power FOREIGN KEY (idx_card) REFERENCES power (id_power) ON UPDATE CASCADE ON DELETE CASCADE
);

-- !Downs
DROP TABLE power;
DROP TABLE unit_card;
DROP TABLE hero_card;
DROP TABLE vehicle_card;
DROP TABLE spy_card;
DROP TABLE card;
DROP TABLE session;
DROP TABLE player;