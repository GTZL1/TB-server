-- Server schema

-- !Ups
INSERT INTO power (name)
values ('PrecisionStrike'),
       ('DistanceStrike'),
       ('Healing'),
       ('DoubleStrike'),
       ('Incineration');
INSERT INTO card (name, life_points, attack_points, max_number_deck)
values ('Ahsoka Tano', 8, 10, 1),
       ('Aloy', 8, 10, 1),
       ('V', 8, 8, 1),
       ('Jon Snow', 9, 8, 1),
       ('Geralt of Rivia', 10, 10, 1);
INSERT INTO unit_card(idx_card)
VALUES ((select id_card from card where card.name='Ahsoka Tano')),
       ((select id_card from card where card.name='Aloy')),
       ((select id_card from card where card.name='V')),
       ((select id_card from card where card.name='Jon Snow')),
       ((select id_card from card where card.name='Geralt of Rivia'));
INSERT INTO hero_card (idx_card, idx_power)
values ((select id_card from card where card.name='Ahsoka Tano'),(select id_power from power where power.name='PrecisionStrike')),
       ((select id_card from card where card.name='Aloy'),(select id_power from power where power.name='DistanceStrike')),
       ((select id_card from card where card.name='V'),(select id_power from power where power.name='Healing')),
       ((select id_card from card where card.name='Jon Snow'),(select id_power from power where power.name='DoubleStrike')),
       ((select id_card from card where card.name='Geralt of Rivia'),(select id_power from power where power.name='Incineration'));

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
       ('Glinthawk', 4, 3, 2);
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
       ('Mammoth', 10, 3, 2),
       ('Griffin', 7, 5, 2),
       ('Direwolf', 5, 3, 3),
       ('Kirov', 2, 10, 1),
       ('Sombra', 5, 5, 1),
       ('Death Trooper', 4, 8, 2),
       ('Dragon', 7, 7, 1),
       ('Clicker', 4, 6, 3),
       ('Assassin''s Creed guard',2 ,2 ,3);
INSERT INTO unit_card(idx_card)
VALUES ((select id_card from card where card.name='Chloe Frazer')),
       ((select id_card from card where card.name='John Wick')),
       ((select id_card from card where card.name='Scorcher')),
       ((select id_card from card where card.name='Cheeseburger')),
       ((select id_card from card where card.name='Judy Alvarez')),
       ((select id_card from card where card.name='Mammoth')),
       ((select id_card from card where card.name='Griffin')),
       ((select id_card from card where card.name='Direwolf')),
       ((select id_card from card where card.name='Kirov')),
       ((select id_card from card where card.name='Sombra')),
       ((select id_card from card where card.name='Death Trooper')),
       ((select id_card from card where card.name='Dragon')),
       ((select id_card from card where card.name='Clicker')),
       ((select id_card from card where card.name='Assassin''s Creed guard'));
INSERT INTO card (name, life_points, attack_points, max_number_deck)
values ('Base 1', 38, 0, 1),
       ('Base 2', 18, 0, 2),
       ('Base 3', 11, 0, 3);

INSERT INTO base_card(idx_card)
VALUES ((select id_card from card where card.name='Base 1')),
       ((select id_card from card where card.name='Base 2')),
       ((select id_card from card where card.name='Base 3'));


-- !Downs
DELETE FROM power;
DELETE FROM base_card;
DELETE FROM hero_card;
DELETE FROM spy_card;
DELETE FROM vehicle_card;
DELETE FROM unit_card;
DELETE FROM card;