/* ===== 사용자 ===== */
INSERT INTO users (users_name, users_id, users_password, users_email, users_phone, users_role)
VALUES ('관리자', 'admin', '{bcrypt}$2a$10$Rro88dIX9bY9a6cZ0ReUw.CfDtfyIhgoDwI3438OWbDSOsp/L.wD2', 'zszz54@naver.com', '010-1234-5678', 'ROLE_ADMIN');

INSERT INTO users (users_name, users_id, users_password, users_email, users_phone, users_role)
VALUES ('김강남', 'cherrybro1', '{bcrypt}$2a$10$Rro88dIX9bY9a6cZ0ReUw.CfDtfyIhgoDwI3438OWbDSOsp/L.wD2', 'ggw@naver.com', '010-1234-5678', 'ROLE_FARMER');

INSERT INTO users (users_name, users_id, users_password, users_email, users_phone, users_role)
VALUES ('김강북', 'cherrybro2', '{bcrypt}$2a$10$9a32jFO/jTEh0e93J9aGP.vd/CL98mo2tpQ9aeQroVa0yd4abYNP6', 'ggw@naver.com', '010-1234-5678', 'ROLE_FARMER');

/* ===== 농장 ===== */
INSERT INTO farm (farm_name, users_no)
VALUES ('강남농장', 2);

INSERT INTO farm (farm_name, users_no)
VALUES ('강북농장', 3);

/* ===== 농장동 ===== */
INSERT INTO farm_section (farm_no, farm_section_name)
VALUES (1, '1동'), (1, '2동'), (1, '3동'),
       (2, '1동'), (2, '2동'), (2, '3동'), (2, '4동');

/* ===== 입추수수 ===== */
INSERT INTO chick_entry (chick_entry_date, chick_entry_number, farm_section_no, chick_entry_create_at)
VALUES ('2025-04-28', 1000, 1, '2025-05-13 13:51:52.665541'),
       ('2025-04-28', 2000, 2, '2025-05-13 13:51:52.665541'),
       ('2025-05-03', 10000, 4, '2025-05-13 13:51:52.665541'),
       ('2025-05-04', 20000, 5, '2025-05-13 13:51:52.665541');

/* ===== 폐사 ===== */
INSERT INTO chick_death (chick_death_date, chick_death_number, farm_section_no, chick_death_create_at)
VALUES ('2025-04-28', 50, 1, '2025-05-13 13:51:52.665541'),
       ('2025-04-28', 50, 2, '2025-05-13 13:51:52.665541'),
       ('2025-05-06', 500, 4, '2025-05-13 13:51:52.665541'),
       ('2025-05-06', 500, 5, '2025-05-13 13:51:52.665541');

/* ===== 도사 (Disposal) ===== */
INSERT INTO chick_disposal (chick_disposal_date, chick_disposal_number, farm_section_no, chick_disposal_create_at)
VALUES ('2025-04-28', 30, 1, '2025-05-13 13:51:52.665541'),
       ('2025-04-28', 25, 2, '2025-05-13 13:51:52.665541'),
       ('2025-05-06', 400, 4, '2025-05-13 13:51:52.665541'),
       ('2025-05-06', 350, 5, '2025-05-13 13:51:52.665541');
