-- Server schema

-- !Ups
INSERT INTO player (username, password_hash)
values ('aloy', 'chieftain');

INSERT INTO deck (idx_player, name)
VALUES ((SELECT id_player FROM player where username='aloy'), 'default');

INSERT INTO deck_card
VALUES (1,1,1),(1,2,1),(1,7,1),(1,8,2),(1,12,1);

-- !Downs
DELETE FROM deck_card;
DELETE FROM deck;
DELETE FROM player;