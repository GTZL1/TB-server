-- Server schema

-- !Ups
INSERT INTO power (name)
values ('PrecisionStrike'),
       ('DistanceStrike'),
       ('Healing'),
       ('DoubleStrike'),
       ('Incineration');
INSERT INTO card (name, life_points, attack_points, max_number_deck, image)
values ('Ahsoka Tano', 8, 10, 1, 'https://nsm09.casimages.com/img/2021/05/16//21051612415425970017418852.jpg'),
       ('Aloy', 8, 10, 1, 'https://nsm09.casimages.com/img/2021/05/16//21051611184925970017418778.png'),
       ('V', 8, 8, 1, 'https://nsm09.casimages.com/img/2021/05/16//21051612055125970017418830.jpg'),
       ('Jon Snow', 9, 8, 1, 'https://nsm09.casimages.com/img/2021/05/16//21051611183625970017418776.jpg'),
       ('Geralt of Rivia', 10, 10, 1, 'default');
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

INSERT INTO card (name, life_points, attack_points, max_number_deck, image)
values ('James Bond', 6, 5, 1, 'default'),
       ('Vanasha', 5, 5, 1, 'https://nsm09.casimages.com/img/2021/05/16//21051612415525970017418853.jpg');
INSERT INTO unit_card(idx_card)
VALUES ((select id_card from card where card.name='James Bond')),
       ((select id_card from card where card.name='Vanasha'));
INSERT INTO spy_card(idx_card)
VALUES ((select id_card from card where card.name='James Bond')),
       ((select id_card from card where card.name='Vanasha'));

INSERT INTO card (name, life_points, attack_points, max_number_deck, image)
values ('Avatar gunship', 5, 6, 2, 'https://nsm09.casimages.com/img/2021/05/16//21051611183525970017418775.jpg'),
       ('Black Pearl', 7, 4, 1, 'default'),
       ('Jedi starfighter', 4, 8, 1, 'https://nsm09.casimages.com/img/2021/05/16//21051612055225970017418833.png'),
       ('Glinthawk', 4, 3, 2, 'default');
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

INSERT INTO card (name, life_points, attack_points, max_number_deck, image)
values ('Chloe Frazer', 8, 6, 1, 'https://nsm09.casimages.com/img/2021/05/16//21051611183525970017418774.png'),
       ('John Wick', 7, 7, 1, 'default'),
       ('Scorcher', 7, 9, 2, 'default'),
       ('Cheeseburger', 9, 4, 1, 'default'),
       ('Judy Alvarez', 4, 4, 1, 'https://nsm09.casimages.com/img/2021/05/16//21051612055125970017418829.jpg'),
       ('Mammoth', 10, 3, 2, 'default'),
       ('Ellie', 5, 5, 1, 'https://nsm09.casimages.com/img/2021/05/16//21051612055225970017418832.jpg'),
       ('Direwolf', 5, 3, 3, 'default'),
       ('Kirov', 2, 10, 1, 'default'),
       ('Sombra', 5, 5, 1, 'default'),
       ('Indiana Jones', 4, 8, 1, 'default'),
       ('Dragon', 7, 7, 2, 'default'),
       ('Clicker', 4, 6, 3, 'default'),
       ('Assassin''s Creed guard',2 ,2 ,3, 'default'),
       ('Tremortusk', 6, 6, 2, 'https://nsm09.casimages.com/img/2021/05/16//21051612055125970017418831.jpg');
INSERT INTO unit_card(idx_card)
VALUES ((select id_card from card where card.name='Chloe Frazer')),
       ((select id_card from card where card.name='John Wick')),
       ((select id_card from card where card.name='Scorcher')),
       ((select id_card from card where card.name='Cheeseburger')),
       ((select id_card from card where card.name='Judy Alvarez')),
       ((select id_card from card where card.name='Mammoth')),
       ((select id_card from card where card.name='Ellie')),
       ((select id_card from card where card.name='Direwolf')),
       ((select id_card from card where card.name='Kirov')),
       ((select id_card from card where card.name='Sombra')),
       ((select id_card from card where card.name='Indiana Jones')),
       ((select id_card from card where card.name='Dragon')),
       ((select id_card from card where card.name='Clicker')),
       ((select id_card from card where card.name='Assassin''s Creed guard')),
       ((select id_card from card where card.name='Tremortusk'));
INSERT INTO card (name, life_points, attack_points, max_number_deck, image)
values ('Base 1', 38, 0, 1, 'default'),
       ('Base 2', 18, 0, 2, 'default'),
       ('Base 3', 11, 0, 3, 'default');

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