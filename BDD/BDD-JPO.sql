CREATE TABLE domaines (id SERIAL UNIQUE, nom character varying(100));
INSERT INTO domaines (nom) VALUES ('Agriculture, Bois');
INSERT INTO domaines (nom) VALUES ('Architecture Paysage, Urbanisme');
INSERT INTO domaines (nom) VALUES ('Défense');
INSERT INTO domaines (nom) VALUES ('Sécurité');
INSERT INTO domaines (nom) VALUES ('Art-Design');
INSERT INTO domaines (nom) VALUES ('Arts du spectacle');
INSERT INTO domaines (nom) VALUES ('Culture et patrimoine');
INSERT INTO domaines (nom) VALUES ('Mode');
INSERT INTO domaines (nom) VALUES ('Assurance-Banque-Finance');
INSERT INTO domaines (nom) VALUES ('Audiovisuel');
INSERT INTO domaines (nom) VALUES ('Communication');
INSERT INTO domaines (nom) VALUES ('Edition-Librairie-Bibliothèque');
INSERT INTO domaines (nom) VALUES ('Traduction-Interprétation');
INSERT INTO domaines (nom) VALUES ('Bâtiments et travaux publics');
INSERT INTO domaines (nom) VALUES ('Compatibilité-Gestion-Ressources');
INSERT INTO domaines (nom) VALUES ('Droit-Justice');
INSERT INTO domaines (nom) VALUES ('Enseignement');
INSERT INTO domaines (nom) VALUES ('Recherche');
INSERT INTO domaines (nom) VALUES ('Energie');
INSERT INTO domaines (nom) VALUES ('Environnement-Qualité');
INSERT INTO domaines (nom) VALUES ('Logistique et transport');
INSERT INTO domaines (nom) VALUES ('Hôtellerie, restauration');
INSERT INTO domaines (nom) VALUES ('Tourisme');
INSERT INTO domaines (nom) VALUES ('Automobile');
INSERT INTO domaines (nom) VALUES ('Electronique');
INSERT INTO domaines (nom) VALUES ('Maintenance');
INSERT INTO domaines (nom) VALUES ('Mécanique');
INSERT INTO domaines (nom) VALUES ('Verre, béton, céramique-Matériaux');
INSERT INTO domaines (nom) VALUES ('Génie Civil');
INSERT INTO domaines (nom) VALUES ('Génie industriel-Génie urbain');
INSERT INTO domaines (nom) VALUES ('Physique-Chimie');
INSERT INTO domaines (nom) VALUES ('Informatique & Réseaux');
INSERT INTO domaines (nom) VALUES ('Jeu vidéo');
INSERT INTO domaines (nom) VALUES ('Informatique et numérique');
INSERT INTO domaines (nom) VALUES ('Commerce, distribution');
INSERT INTO domaines (nom) VALUES ('Marketing, publicité');
INSERT INTO domaines (nom) VALUES ('Médical');
INSERT INTO domaines (nom) VALUES ('Paramédical');
INSERT INTO domaines (nom) VALUES ('Social');
INSERT INTO domaines (nom) VALUES ('Sport');
INSERT INTO domaines (nom) VALUES ('Management');
INSERT INTO domaines (nom) VALUES ('Mathématiques -Statistiques');
INSERT INTO domaines (nom) VALUES ('Siences et humaines et sociales');

CREATE TABLE ecoles (id SERIAL UNIQUE, nom character varying(30), site character varying(60), description character varying(2000));
INSERT INTO ecoles (nom, site, description) VALUES ('ENSG-Géomatique', 'http://www.ensg.eu/', 'Grande école d''ingénieurs qui forme des professionnels dans le domaine des technologies de l''information géographique et forestière : la géomatique\n- Domaines d''enseignement : Informatique, géolocalisation, imagerie 3D, web cartographique… Débouchés dans de nombreux secteurs : technologies de l''information, aménagement des territoires, transports, urbanisme, énergie, environnement, défense, géodécisionnel...\n- Formations proposées : cycle de technicien supérieur, cycle d''ingénieur, licence professionnelle, masters et mastères spécialisés');
INSERT INTO ecoles (nom, site, description) VALUES ('UPEM', 'http://www.u-pem.fr/', 'Avec + de 20% de ses 12.000 étudiants en apprentissage, l''UPEM est la première université française en matière d''apprentissage.\n- 7 grands domaines d''enseignement : arts, lettres modernes, langues, économie et gestion, STAPS, sciences humaines et sociales, sciences et technologies\n- Une École d''Urbanisme de Paris (EUP) : première école de ce type en France et l''une des plus importantes d’Europe, est un centre de formation proposant un master unique « Urbanisme et Aménagement » qui accueille chaque année plus de 350 étudiants en M1 et M2 et plusieurs Diplômes d’Université de niveau Master\n- Une École Supérieure d''Ingénieurs (ESIPE-MLV)\n- Un Institut d’Administration des Entreprises (IAE Gustave Eiffel)\n- Un Institut Universitaire de Technologie (IUT) : 2 sites : Champssur-Marne et Meaux (une navette est prévue entre les 2 le jour de la JPO)');
INSERT INTO ecoles (nom, site, description) VALUES ('EVAT', 'http://www.marnelavallee.archi.fr/', 'École nationale supérieure d''architecture, sous la tutelle du ministère de la Culture, qui forme des architectes et des urbanistes :\n- Licence générale en architecture\n- Filières de master : Architecture & Expérience /Matières à penser / Métropoles / Transformation\n- Formation Structure & Architecture (passerelle pour le double cursus architecte-ingénieur)\n- Formations post-master : DSA d''architecte-urbaniste, DPEA Architecture post-carbone, Habilitation à la maîtrise d’œuvre\n- Admission en 1re année ouverte aux titulaires du baccalauréat (général, technologique et professionnel)');
INSERT INTO ecoles (nom, site, description) VALUES ('ESIEE Paris', 'https://www.esiee.fr/', 'L''école de l''innovation technologique\nESIEE Paris est l''école d''ingénieurs de la CCI Paris Île-de-France.\nElle forme des étudiants dans tous les domaines du numérique et des nouvelles technologies, de l''électronique jusqu''à l''informatique, des systèmes embarqués jusqu''à la cybersécurité en passant par la datascience et l''intelligence artificielle.\nElle forme également aux nouvelles applications du numérique à la santé, l''énergie, et l’environnement, ainsi qu''au management des technologies.');
INSERT INTO ecoles (nom, site, description) VALUES ('CFA Descartes', 'https://www.cfadescartes.fr/', 'Boostez votre formation supérieure et votre insertion professionnelle avec l''alternance !\n- 85 formations ouvertes en apprentissage avec l''UPEM, en DUT, BTS, Licence Pro, Licence 3, Master et Master 2 dans 14 secteurs : Automobile, Banque, Comptabilité, Commerce Vente, Communication, Web et Numérique, Marketing, Informatique Electronique, Industrie Energie, Qualité Environnement, Urbanisme et Construction, Immobilier, Ressources humaines, Management des entreprises, Santé-sport-ESS\n- Le CFA Descartes c''est : 25 ans d''expérience1800 apprentis/an ; +1000 entreprises partenaires ; 93% de diplômés et 91% d''insertion pro.');
INSERT INTO ecoles (nom, site, description) VALUES ('IUT Marne-la-Vallée', 'http://iut.u-pem.fr/', 'Pas de description');
INSERT INTO ecoles (nom, site, description) VALUES ('Communauté Université Paris-Est', 'http://www.univ-paris-est.fr/fr', 'Faire un doctorat : Université Paris-Est forme à et par la recherche.\n- Formation doctorale et délivrance du diplôme de doctorat (Bac+8) et de l''habilitation à diriger des recherches\n- 8 écoles doctorales pluridisciplinaires : Agriculture alimentation biologie environnement santé ; Cultures et Sociétés ; Organisations marchés institutions ; Mathématiques et STIC ; Santé Publique ; Sciences Ingénierie Environnement ; Sciences de la vie et de la santé ; Ville transports et territoires.');




