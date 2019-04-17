CREATE TABLE evenements (id SERIAL UNIQUE, nom character varying(100), debut date, fin date, id_ecole int,id_batiment int);

INSERT INTO evenements (nom, debut, fin, id_ecole, id_batiment) VALUES (' Les points forts du DUT TC en alternance','2018-02-02 11:00:00' ,'2018-02-02 11:45:00' , 5, 6);
INSERT INTO evenements (nom, debut, fin, id_ecole, id_batiment) VALUES (' Les points forts du DUT TC en alternance','2018-02-02 14:30:00','2018-02-02 15:15:00' , 5, 6);
INSERT INTO evenements (nom, debut, fin, id_ecole, id_batiment) VALUES (' Présentation du cycle ingénieur en géomatique','2018-02-02 10:30:00' ,'2018-02-02 11:00:00' , 1, 1);
INSERT INTO evenements (nom, debut, fin, id_ecole, id_batiment) VALUES (' Présentation de la Licence professionnelle géomatique et environnement','2018-02-02 11:15:00' ,'2018-02-02 11:45:00' , 1, 1);
INSERT INTO evenements (nom, debut, fin, id_ecole, id_batiment) VALUES (' Présentation du cycle de technicien supérieur géomètre géomaticien','2018-02-02 14:00:00' ,'2018-02-02 14:30:00' , 1, 1);
INSERT INTO evenements (nom, debut, fin, id_ecole, id_batiment) VALUES ('Conférences et questions/réponses sur la formation et le métier d’ostéopathe','2018-02-02 11:00:00' ,'2018-02-02 11:45:00', 9, 9);
INSERT INTO evenements (nom, debut, fin, id_ecole, id_batiment) VALUES ('Conférences et questions/réponses sur la formation et le métier d’ostéopathe','2018-02-02 14:00:00' ,'2018-02-02 14:45:00' , 9, 9);
INSERT INTO evenements (nom, debut, fin, id_ecole, id_batiment) VALUES ('Réunion information VAE','2018-02-02 09:30:00','2018-02-02 12:30:00' , 2, 2);
INSERT INTO evenements (nom, debut, fin, id_ecole, id_batiment) VALUES ('Conférence-débat animée par Mme Bénédicte BLANC-directrice des études de 1re année','2018-02-02 10:30:00' ,'2018-02-02 11:15:00' , 2, 7);
INSERT INTO evenements (nom, debut, fin, id_ecole, id_batiment) VALUES ('Conférence-débat animée par Mme Bénédicte BLANC-directrice des études de 1re année','2018-02-02 14:30:00' ,'2018-02-02 15:15:00' , 2, 7);
INSERT INTO evenements (nom, debut, fin, id_ecole, id_batiment) VALUES (' Licence pro Performance Énergétique et Environnement des Bâtiments','2018-02-02 11:00:00' ,'2018-02-02 11:45:00', 2, 7);


