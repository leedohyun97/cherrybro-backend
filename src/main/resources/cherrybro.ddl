DROP TABLE IF EXISTS chick_death;
DROP TABLE IF EXISTS chick_entry;
DROP TABLE IF EXISTS farm_section;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS farmer;
DROP TABLE IF EXISTS users;

CREATE TABLE users(
		user_no                       		INT(10)		 NOT NULL AUTO_INCREMENT,
		user_name                     		VARCHAR(100)		 NULL ,
		user_role                     		VARCHAR(10)		 NULL ,
		user_email                    		VARCHAR(100)		 NULL ,
		user_phone                    		INT(100)		 NULL 
);

CREATE TABLE farmer(
		farmer_no                     		INT(10)		 NOT NULL AUTO_INCREMENT,
		user_no                       		INT(10)		 NULL 
);

CREATE TABLE admin(
		admin_no                      		INT(10)		 NOT NULL AUTO_INCREMENT,
		user_no                       		INT(10)		 NULL 
);

CREATE TABLE farm_section(
		farm_section_no               		INT(10)		 NOT NULL AUTO_INCREMENT,
		farm_section_name             		VARCHAR(50)		 NULL ,
		farmer_no                     		INT(10)		 NULL 
);

CREATE TABLE chick_entry(
		chick_entry_no                		INT(10)		 NOT NULL AUTO_INCREMENT,
		chick_entry_number            		INT(100)		 NULL ,
		chick_entry_date              		DATE		 NULL ,
		farm_section_no               		INT(10)		 NULL 
);

CREATE TABLE chick_death(
		chick_death_no                		INT(10)		 NOT NULL AUTO_INCREMENT,
		chick_death_number            		INT(100)		 NULL ,
		chick_death_date              		DATE		 NULL ,
		farm_section_no               		INT(10)		 NULL 
);


ALTER TABLE users ADD CONSTRAINT IDX_users_PK PRIMARY KEY (user_no);

ALTER TABLE farmer ADD CONSTRAINT IDX_farmer_PK PRIMARY KEY (farmer_no);
ALTER TABLE farmer ADD CONSTRAINT IDX_farmer_FK0 FOREIGN KEY (user_no) REFERENCES users (user_no);

ALTER TABLE admin ADD CONSTRAINT IDX_admin_PK PRIMARY KEY (admin_no);
ALTER TABLE admin ADD CONSTRAINT IDX_admin_FK0 FOREIGN KEY (user_no) REFERENCES users (user_no);

ALTER TABLE farm_section ADD CONSTRAINT IDX_farm_section_PK PRIMARY KEY (farm_section_no);
ALTER TABLE farm_section ADD CONSTRAINT IDX_farm_section_FK0 FOREIGN KEY (farmer_no) REFERENCES farmer (farmer_no);

ALTER TABLE chick_entry ADD CONSTRAINT IDX_chick_entry_PK PRIMARY KEY (chick_entry_no);
ALTER TABLE chick_entry ADD CONSTRAINT IDX_chick_entry_FK0 FOREIGN KEY (farm_section_no) REFERENCES farm_section (farm_section_no);

ALTER TABLE chick_death ADD CONSTRAINT IDX_chick_death_PK PRIMARY KEY (chick_death_no);
ALTER TABLE chick_death ADD CONSTRAINT IDX_chick_death_FK0 FOREIGN KEY (farm_section_no) REFERENCES farm_section (farm_section_no);

