--command to run it: cat db-init.sql | docker exec -i <container name> psql -U tb_server -d tb_server_db

--------------------------------
-- Creations
--------------------------------
DROP SCHEMA IF EXISTS public CASCADE;
CREATE SCHEMA public;

CREATE TABLE IF NOT EXISTS player
(
    id_player     SERIAL PRIMARY KEY,
    username      varchar(255) UNIQUE NOT NULL,
    password_hash varchar(255)        NOT NULL
);
CREATE TABLE IF NOT EXISTS session
(
    id_session SERIAL PRIMARY KEY,
    idx_player BIGINT UNIQUE NOT NULL,
    CONSTRAINT fk_session_player FOREIGN KEY (idx_player) REFERENCES player (id_player) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS card
(
    id_card         SERIAL primary key,
    name            varchar(255) unique not null,
    life_points     int                 not null,
    attack_points   int                 not null,
    max_number_deck int                 not null
);
CREATE TABLE IF NOT EXISTS unit_card
(
    idx_card BIGINT PRIMARY KEY,
    CONSTRAINT fk_card_unit_card FOREIGN KEY (idx_card) REFERENCES card (id_card) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS spy_card
(
    idx_card BIGINT PRIMARY KEY,
    CONSTRAINT fk_card_spy_card FOREIGN KEY (idx_card) REFERENCES unit_card (idx_card) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS vehicle_card
(
    idx_card BIGINT PRIMARY KEY,
    CONSTRAINT fk_card_vehicle_card FOREIGN KEY (idx_card) REFERENCES unit_card (idx_card) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS power
(
    id_power SERIAL PRIMARY KEY,
    name     VARCHAR(255) UNIQUE NOT NULL
);
CREATE TABLE IF NOT EXISTS hero_card
(
    idx_card  BIGINT PRIMARY KEY,
    idx_power BIGINT NOT NULL,
    CONSTRAINT fk_card_hero_card FOREIGN KEY (idx_card) REFERENCES unit_card (idx_card) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_hero_card_power FOREIGN KEY (idx_power) REFERENCES power (id_power) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS base_card
(
    idx_card BIGINT PRIMARY KEY,
    CONSTRAINT fk_card_base_card FOREIGN KEY (idx_card) REFERENCES card (id_card) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS game
(
    idx_player_winner BIGINT,
    idx_player_looser BIGINT,
    date              DATE,
    PRIMARY KEY (idx_player_winner, idx_player_looser, date),
    CONSTRAINT fk_game_player_winner FOREIGN KEY (idx_player_winner) REFERENCES player (id_player) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_game_player_looser FOREIGN KEY (idx_player_looser) REFERENCES player (id_player) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS deck
(
    id_deck    SERIAL PRIMARY KEY,
    idx_player BIGINT       NOT NULL,
    name       VARCHAR(255) NOT NULL,
    CONSTRAINT fk_player_deck FOREIGN KEY (idx_player) REFERENCES player (id_player) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS deck_card
(
    idx_deck BIGINT,
    idx_card BIGINT,
    quantity SMALLINT NOT NULL,
    PRIMARY KEY (idx_deck, idx_card),
    CONSTRAINT fk_deck_deck_card FOREIGN KEY (idx_deck) REFERENCES deck (id_deck) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_card_deck_card FOREIGN KEY (idx_card) REFERENCES card (id_card) ON UPDATE CASCADE ON DELETE CASCADE
);

--------------------------------
-- Insertions
--------------------------------
INSERT INTO power (name)
values ('PrecisionStrike'),
       ('DistanceStrike'),
       ('Healing'),
       ('DoubleStrike'),
       ('Incineration'),
	   ('Whipstrike');
INSERT INTO card (name, life_points, attack_points, max_number_deck)
values ('Ahsoka Tano', 8, 10, 1),
       ('Aloy', 8, 10, 1),
       ('V', 8, 8, 1),
       ('Jon Snow', 9, 8, 1),
       ('Geralt of Rivia', 10, 10, 1),
	   ('Indiana Jones', 7, 9, 1);
INSERT INTO unit_card(idx_card)
VALUES ((select id_card from card where card.name='Ahsoka Tano')),
       ((select id_card from card where card.name='Aloy')),
       ((select id_card from card where card.name='V')),
       ((select id_card from card where card.name='Jon Snow')),
       ((select id_card from card where card.name='Geralt of Rivia')),
	   ((select id_card from card where card.name='Indiana Jones'));
INSERT INTO hero_card (idx_card, idx_power)
values ((select id_card from card where card.name='Ahsoka Tano'),(select id_power from power where power.name='PrecisionStrike')),
       ((select id_card from card where card.name='Aloy'),(select id_power from power where power.name='DistanceStrike')),
       ((select id_card from card where card.name='V'),(select id_power from power where power.name='Healing')),
       ((select id_card from card where card.name='Jon Snow'),(select id_power from power where power.name='DoubleStrike')),
       ((select id_card from card where card.name='Geralt of Rivia'),(select id_power from power where power.name='Incineration')),
	   ((select id_card from card where card.name='Indiana Jones'),(select id_power from power where power.name='Whipstrike'));

INSERT INTO card (name, life_points, attack_points, max_number_deck)
values ('James Bond', 6, 5, 1),
       ('Vanasha', 5, 5, 1);
INSERT INTO unit_card(idx_card)
VALUES ((select id_card from card where card.name='James Bond')),
       ((select id_card from card where card.name='Vanasha'));
INSERT INTO spy_card(idx_card)
VALUES ((select id_card from card where card.name='James Bond')),
       ((select id_card from card where card.name='Vanasha'));

INSERT INTO card (name, life_points, attack_points, max_number_deck)
values ('Avatar gunship', 5, 6, 2),
       ('Black Pearl', 7, 4, 1),
       ('Jedi starfighter', 4, 8, 1),
       ('Glinthawk', 4, 3, 3);
INSERT INTO unit_card(idx_card)
VALUES ((select id_card from card where card.name='Avatar gunship')),
       ((select id_card from card where card.name='Black Pearl')),
       ((select id_card from card where card.name='Jedi starfighter')),
       ((select id_card from card where card.name='Glinthawk'));
INSERT INTO vehicle_card(idx_card)
VALUES ((select id_card from card where card.name='Avatar gunship')),
       ((select id_card from card where card.name='Black Pearl')),
       ((select id_card from card where card.name='Jedi starfighter')),
       ((select id_card from card where card.name='Glinthawk'));

INSERT INTO card (name, life_points, attack_points, max_number_deck)
values ('Chloe Frazer', 8, 6, 1),
       ('John Wick', 7, 7, 1),
       ('Scorcher', 7, 9, 2),
       ('Cheeseburger', 9, 4, 1),
       ('Judy Alvarez', 4, 4, 1),
	   ('Panam Palmer', 4, 4, 1),
       ('Mammoth', 10, 3, 2),
       ('Ellie', 5, 5, 1),
       ('Direwolf', 5, 3, 3),
       ('Kirov', 2, 10, 2),
       ('Sombra', 5, 5, 1),
       ('Asari warrior', 4, 8, 3),
       ('Valyrian dragon', 7, 7, 2),
       ('Clicker', 4, 6, 3),
	   ('Tremortusk', 6, 6, 2),
       ('Assassin''s Creed guard',2 ,2 ,6);
INSERT INTO unit_card(idx_card)
VALUES ((select id_card from card where card.name='Chloe Frazer')),
       ((select id_card from card where card.name='John Wick')),
       ((select id_card from card where card.name='Scorcher')),
       ((select id_card from card where card.name='Cheeseburger')),
       ((select id_card from card where card.name='Judy Alvarez')),
	   ((select id_card from card where card.name='Panam Palmer')),
       ((select id_card from card where card.name='Mammoth')),
       ((select id_card from card where card.name='Ellie')),
       ((select id_card from card where card.name='Direwolf')),
       ((select id_card from card where card.name='Kirov')),
       ((select id_card from card where card.name='Sombra')),
       ((select id_card from card where card.name='Asari warrior')),
       ((select id_card from card where card.name='Valyrian dragon')),
       ((select id_card from card where card.name='Clicker')),
       ((select id_card from card where card.name='Assassin''s Creed guard')),
       ((select id_card from card where card.name='Tremortusk'));
INSERT INTO card (name, life_points, attack_points, max_number_deck)
values ('Base 1', 38, 0, 1),
       ('Base 2', 18, 0, 2),
       ('Base 3', 11, 0, 3);

INSERT INTO base_card(idx_card)
VALUES ((select id_card from card where card.name='Base 1')),
       ((select id_card from card where card.name='Base 2')),
       ((select id_card from card where card.name='Base 3'));
	   
--------------------------------
-- Procedures and triggers
--------------------------------
CREATE FUNCTION default_deck() RETURNS trigger AS $default_deck$
    BEGIN
		INSERT INTO deck (idx_player, name)
			VALUES (NEW.id_player, 'default');
		
        RETURN NEW;
    END;
$default_deck$ LANGUAGE plpgsql;

CREATE TRIGGER default_deck AFTER INSERT ON player
    FOR EACH ROW EXECUTE FUNCTION default_deck();

CREATE FUNCTION default_deck_cards() RETURNS trigger AS $default_deck_cards$
    BEGIN
        FOR x in 1 .. (select count(*) from unit_card) LOOP
			INSERT INTO deck_card
				VALUES (NEW.id_deck, x, 1);
		END LOOP;
		
		INSERT INTO deck_card 
			VALUES (NEW.id_deck,
			(select id_card from card where card.name='Base 2'),
			(select max_number_deck from card where card.name='Base 2'));
		
        RETURN NEW;
    END;
$default_deck_cards$ LANGUAGE plpgsql;

CREATE TRIGGER default_deck_cards AFTER INSERT ON deck
    FOR EACH ROW EXECUTE FUNCTION default_deck_cards();
