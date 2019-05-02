CREATE TABLE 	satisfaction (id SERIAL UNIQUE, question_1 character varying(100),question_2 character varying(100),question_3 character varying(100),question_4 character varying(100),question_5 character varying(100),question_6 character varying(100),question_7 character varying(100),question_8 character varying(100),question_9 character varying(100),question_10 character varying(100),question_11 character varying(100),question_12 character varying(100),question_13 character varying(100),question_14 character varying(100),question_15 character varying(100),question_16 character varying(100),question_17 character varying(100),question_18 character varying(100),question_19 character varying(100),question_20 character varying(100));

INSERT INTO satisfaction (question_1, question_2,question_3, question_4,question_5, question_6,question_7, question_8,question_9, question_10,question_11, question_12,question_13, question_14,question_15, question_16,question_17, question_18,question_19, question_20) VALUES ('exemple1','exemple2','exemple3','exemple4','exemple5','exemple6','exemple7','exemple8','exemple9','exemple10','exemple11','exemple12','exemple13','exemple14','exemple15','exemple16','exemple17','exemple18','exemple19','exemple20');




CREATE TABLE 	questionnaire (id SERIAL UNIQUE, question_1 character varying(100),question_2 character varying(100),question_3 character varying(100));

INSERT INTO questionnaire(question_1, question_2,question_3) VALUES ('exemple1','exemple2','exemple3');

