INSERT INTO users (users_name, users_id, users_password, users_email, users_phone, users_role)
VALUES ('김강남', 'cherrybro', '{bcrypt}$2a$10$9a32jFO/jTEh0e93J9aGP.vd/CL98mo2tpQ9aeQroVa0yd4abYNP6', 'ggw@naver.com', '010-1234-5678', 1);

INSERT INTO farm (farm_name, users_no)
VALUES ('강남농장', 1);

INSERT INTO farm (farm_name, users_no)
VALUES ('강북농장', 2);

INSERT INTO farm_section (farm_no, farm_section_name)
VALUES (1, '1동');

INSERT INTO farm_section (farm_no, farm_section_name)
VALUES (1, '2동');

INSERT INTO farm_section (farm_no, farm_section_name)
VALUES (1, '3동');

INSERT INTO chick_entry (chick_entry_date, chick_entry_number, farm_section)
VALUES ('2025-04-28', 1000, 1);

INSERT INTO chick_entry (chick_entry_date, chick_entry_number, farm_section)
VALUES ('2025-04-28', 2000, 2);

INSERT INTO chick_death (chick_death_date, chick_death_number, farm_section)
VALUES ('2025-04-28', 50, 1);

INSERT INTO chick_death (chick_death_date, chick_death_number, farm_section)
VALUES ('2025-04-28', 50, 2);




