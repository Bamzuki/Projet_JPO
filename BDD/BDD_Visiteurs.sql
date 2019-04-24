CREATE TABLE utilisateurs (id SERIAL UNIQUE, prenom character varying(100), nom character varying(100), pseudo character varying(100), email character varying(100), mdp character varying(100), admin boolean);

INSERT INTO utilisateurs (prenom, nom, pseudo, email, mdp, admin) VALUES ('Lucas','Tacito', 'Lulu', 'lucas.tacito@ensg.eu', 'mdp', TRUE);
INSERT INTO utilisateurs (prenom, nom, pseudo, email, mdp, admin) VALUES ('Hugo','De Paulis', 'The boss', 'hugo.de-paulis@ensg.eu', 'mdp', TRUE);

CREATE TABLE favoris (id_utilisateur INTEGER, id_favoris INTEGER); 

INSERT INTO favoris (id_utilisateur, id_favoris) VALUES (1, 1);
INSERT INTO favoris (id_utilisateur, id_favoris) VALUES (1, 2);
INSERT INTO favoris (id_utilisateur, id_favoris) VALUES (2, 3);
INSERT INTO favoris (id_utilisateur, id_favoris) VALUES (2, 4);
 