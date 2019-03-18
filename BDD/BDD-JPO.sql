﻿CREATE TABLE filieres (id SERIAL UNIQUE, nom character varying(100));
INSERT INTO filieres (nom) VALUES ('Agriculture, Bois');
INSERT INTO filieres (nom) VALUES ('Architecture Paysage, Urbanisme');
INSERT INTO filieres (nom) VALUES ('Défense');
INSERT INTO filieres (nom) VALUES ('Sécurité');
INSERT INTO filieres (nom) VALUES ('Art-Design');
INSERT INTO filieres (nom) VALUES ('Arts du spectacle');
INSERT INTO filieres (nom) VALUES ('Culture et patrimoine');
INSERT INTO filieres (nom) VALUES ('Mode');
INSERT INTO filieres (nom) VALUES ('Assurance-Banque-Finance');
INSERT INTO filieres (nom) VALUES ('Audiovisuel');
INSERT INTO filieres (nom) VALUES ('Communication');
INSERT INTO filieres (nom) VALUES ('Edition-Librairie-Bibliothèque');
INSERT INTO filieres (nom) VALUES ('Traduction-Interprétation');
INSERT INTO filieres (nom) VALUES ('Bâtiments et travaux publics');
INSERT INTO filieres (nom) VALUES ('Compatibilité-Gestion-Ressources');
INSERT INTO filieres (nom) VALUES ('Droit-Justice');
INSERT INTO filieres (nom) VALUES ('Enseignement');
INSERT INTO filieres (nom) VALUES ('Recherche');
INSERT INTO filieres (nom) VALUES ('Energie');
INSERT INTO filieres (nom) VALUES ('Environnement-Qualité');
INSERT INTO filieres (nom) VALUES ('Logistique et transport');
INSERT INTO filieres (nom) VALUES ('Hôtellerie, restauration');
INSERT INTO filieres (nom) VALUES ('Tourisme');
INSERT INTO filieres (nom) VALUES ('Automobile');
INSERT INTO filieres (nom) VALUES ('Electronique');
INSERT INTO filieres (nom) VALUES ('Maintenance');
INSERT INTO filieres (nom) VALUES ('Mécanique');
INSERT INTO filieres (nom) VALUES ('Verre, béton, céramique-Matériaux');
INSERT INTO filieres (nom) VALUES ('Génie Civil');
INSERT INTO filieres (nom) VALUES ('Génie industriel-Génie urbain');
INSERT INTO filieres (nom) VALUES ('Physique-Chimie');
INSERT INTO filieres (nom) VALUES ('Informatique & Réseaux');
INSERT INTO filieres (nom) VALUES ('Jeu vidéo');
INSERT INTO filieres (nom) VALUES ('Informatique et numérique');
INSERT INTO filieres (nom) VALUES ('Commerce, distribution');
INSERT INTO filieres (nom) VALUES ('Marketing, publicité');
INSERT INTO filieres (nom) VALUES ('Médical');
INSERT INTO filieres (nom) VALUES ('Paramédical');
INSERT INTO filieres (nom) VALUES ('Social');
INSERT INTO filieres (nom) VALUES ('Sport');
INSERT INTO filieres (nom) VALUES ('Management');
INSERT INTO filieres (nom) VALUES ('Mathématiques -Statistiques');
INSERT INTO filieres (nom) VALUES ('Siences et humaines et sociales');

CREATE TABLE ecoles (id SERIAL UNIQUE, nom character varying(50), site character varying(60), description character varying(2000));
INSERT INTO ecoles (nom, site, description) VALUES ('ENSG-Géomatique', 'http://www.ensg.eu/', 'Grande école d''ingénieurs qui forme des professionnels dans le filiere des technologies de l''information géographique et forestière : la géomatique\n - filieres d''enseignement : Informatique, géolocalisation, imagerie 3D, web cartographique… Débouchés dans de nombreux secteurs : technologies de l''information, aménagement des territoires, transports, urbanisme, énergie, environnement, défense, géodécisionnel...\n - Formations proposées : cycle de technicien supérieur, cycle d''ingénieur, licence professionnelle, masters et mastères spécialisés');
INSERT INTO ecoles (nom, site, description) VALUES ('UPEM', 'http://www.u-pem.fr/', 'Avec + de 20% de ses 12.000 étudiants en apprentissage, l''UPEM est la première université française en matière d''apprentissage.\n - 7 grands filieres d''enseignement : arts, lettres modernes, langues, économie et gestion, STAPS, sciences humaines et sociales, sciences et technologies\n - Une École d''Urbanisme de Paris (EUP) : première école de ce type en France et l''une des plus importantes d’Europe, est un centre de formation proposant un master unique « Urbanisme et Aménagement » qui accueille chaque année plus de 350 étudiants en M1 et M2 et plusieurs Diplômes d’Université de niveau Master\n - Une École Supérieure d''Ingénieurs (ESIPE-MLV)\n - Un Institut d’Administration des Entreprises (IAE Gustave Eiffel)\n - Un Institut Universitaire de Technologie (IUT) : 2 sites : Champssur-Marne et Meaux (une navette est prévue entre les 2 le jour de la JPO)');
INSERT INTO ecoles (nom, site, description) VALUES ('EAVT', 'http://www.marnelavallee.archi.fr/', 'École nationale supérieure d''architecture, sous la tutelle du ministère de la Culture, qui forme des architectes et des urbanistes :\n - Licence générale en architecture\n - Filières de master : Architecture & Expérience /Matières à penser / Métropoles / Transformation\n - Formation Structure & Architecture (passerelle pour le double cursus architecte-ingénieur)\n - Formations post-master : DSA d''architecte-urbaniste, DPEA Architecture post-carbone, Habilitation à la maîtrise d’œuvre\n - Admission en 1re année ouverte aux titulaires du baccalauréat (général, technologique et professionnel)');
INSERT INTO ecoles (nom, site, description) VALUES ('ESIEE Paris', 'https://www.esiee.fr/', 'L''école de l''innovation technologique\nESIEE Paris est l''école d''ingénieurs de la CCI Paris Île-de-France.\nElle forme des étudiants dans tous les filieres du numérique et des nouvelles technologies, de l''électronique jusqu''à l''informatique, des systèmes embarqués jusqu''à la cybersécurité en passant par la datascience et l''intelligence artificielle.\nElle forme également aux nouvelles applications du numérique à la santé, l''énergie, et l’environnement, ainsi qu''au management des technologies.');
INSERT INTO ecoles (nom, site, description) VALUES ('CFA Descartes', 'https://www.cfadescartes.fr/', 'Boostez votre formation supérieure et votre insertion professionnelle avec l''alternance !\n - 85 formations ouvertes en apprentissage avec l''UPEM, en DUT, BTS, Licence Pro, Licence 3, Master et Master 2 dans 14 secteurs : Automobile, Banque, Comptabilité, Commerce Vente, Communication, Web et Numérique, Marketing, Informatique Electronique, Industrie Energie, Qualité Environnement, Urbanisme et Construction, Immobilier, Ressources humaines, Management des entreprises, Santé-sport-ESS\n - Le CFA Descartes c''est : 25 ans d''expérience1800 apprentis/an ; +1000 entreprises partenaires ; 93% de diplômés et 91% d''insertion pro.');
INSERT INTO ecoles (nom, site, description) VALUES ('IUT Marne-la-Vallée', 'http://iut.u-pem.fr/', 'Pas de description');
INSERT INTO ecoles (nom, site, description) VALUES ('Communauté Université Paris-Est', 'http://www.univ-paris-est.fr/fr', 'Faire un doctorat : Université Paris-Est forme à et par la recherche.\n - Formation doctorale et délivrance du diplôme de doctorat (Bac+8) et de l''habilitation à diriger des recherches\n - 8 écoles doctorales pluridisciplinaires : Agriculture alimentation biologie environnement santé ; Cultures et Sociétés ; Organisations marchés institutions ; Mathématiques et STIC ; Santé Publique ; Sciences Ingénierie Environnement ; Sciences de la vie et de la santé ; Ville transports et territoires.');
INSERT INTO ecoles (nom, site, description) VALUES ('Compagnons du devoir et du tour de France', 'https://www.compagnons-du-devoir.com/', '« Savoir-faire et savoir-être ». Se former et s''épanouir dans et par son métier :\n - En alternance : formation par le travail en entreprise, complété par des stages\n - En voyageant grâce au Tour de France, dont une étape à l''international\n - En partageant des expériences et des moments de vie en communauté\n - 30 métiers, 6 filières : Aménagement et finition ; Bâtiment ; Métiers du goût ; Matériaux souples ; Métallurgie et industrie ; Métiers du vivant. De la formation initiale (CAP, Bac pro) à la formation supérieure (DEUST, Licence pro).');
INSERT INTO ecoles (nom, site, description) VALUES ('ESO Paris SUPOSTEO', 'https://www.eso-suposteo.fr/', 'L''école d''ostéopathie référente en France et en Europe :\nDes conditions optimales pour former des ostéopathes passionnés et rigoureux. Un cursus complet en 4865 heures (5 ans) et la plus importante clinique ostéopathique de France.\n - Diplôme d''ostéopathe, titre national inscrit au RNCP niveau 1\n - Formation Bac+5');

CREATE TABLE batiments (id SERIAL UNIQUE, nom character varying(50), fonction character varying(30), lat float, lng float);
INSERT INTO batiments (nom, fonction, lat, lng) VALUES ('ENSG', 'enseignement', 48.8410959, 2.5873874);
INSERT INTO batiments (nom, fonction, lat, lng) VALUES ('Copernic', 'enseignement', 48.839159, 2.5866814);
INSERT INTO batiments (nom, fonction, lat, lng) VALUES ('EUP', 'enseignement', 48.842614, 2.5861283);
INSERT INTO batiments (nom, fonction, lat, lng) VALUES ('EAVT', 'enseignement', 48.8411598, 2.5918843);
INSERT INTO batiments (nom, fonction, lat, lng) VALUES ('ESIEE', 'enseignement', 48.8402383, 2.5816832);
INSERT INTO batiments (nom, fonction, lat, lng) VALUES ('CFA Descartes', 'enseignement', 48.8360343, 2.5923958);
INSERT INTO batiments (nom, fonction, lat, lng) VALUES ('IUT Champs', 'enseignement', 48.837046, 2.5842788);
INSERT INTO batiments (nom, fonction, lat, lng) VALUES ('ESO', 'enseignement', 48.836663, 2.5913698);

CREATE TABLE formations (id SERIAL UNIQUE, nom character varying(200), niveau character varying(30), id_ecole INTEGER, id_batiment INTEGER, id_filiere INTEGER);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Forêt et bois', 'Master', 1, 1, 1);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('L1&2 Géographie et aménagement', 'Licence Générale', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('L3 Géographie et aménagement - Géographie sociale et culturelle', 'Licence Générale', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('L3 Géographie et aménagement - Etudes urbaines', 'Licence Générale', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Métiers de l''aménagement du territoire et de l''urbanisme - Assistant chef de projet en aménagement de l''espace', 'Licence Professionnelle', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1 Génie urbain', 'Master', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Génie urbain  - Développement Urbain Durable (DUD)', 'Master', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Génie urbain  - Ingénierie de la Maîtrise d’Œuvre Architecturale et Urbaine (IMOAU)', 'Master', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Génie urbain - Management et Ingénierie des Services à l’Environnement (MISE)', 'Master', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1 Urbanisme et aménagement', 'Master', 2, 3, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Urbanisme et aménagement - Programmation, projet et management urbain', 'Master', 2, 3, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Urbanisme et aménagement - Développement et Territoires: ressources, politiques et stratégies (DETER)', 'Master', 2, 3, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Urbanisme et aménagement - Développement urbain intégré : stratégies et projets', 'Master', 2, 3, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Urbanisme et aménagement - Alternatives urbaines, démarches expérimentales et Espaces publics (AUDE)', 'Master', 2, 3, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Urbanisme et aménagement - Habitat et renouvellement urbain (HRU)', 'Master', 2, 3, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Urbanisme et aménagement - Environnements urbains : stratégies, projets, services', 'Master', 2, 3, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Urbanisme et aménagement - Transport, mobilité', 'Master', 2, 3, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Urbanisme et aménagement - Urbanisme et expertise internationale', 'Master', 2, 3, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Urbanisme et aménagement - Maîtrise d''ouvrage des projets urbains  (MOPU)', 'Master', 2, 3, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1 Génie Civil', 'Master', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Génie Civil - Mécanique, Matériaux et Structures pour la Construction et les Transports', 'Master', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Génie Civil - Ingénierie de projet en Génie Civil', 'Master', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Génie Civil - Mécanique des sols, des roches et des ouvrages dans leur environnement', 'Master', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Géographie - Espaces, Sociétés, Territoires', 'Master', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Histoire - Diagnostic historique et aménagement urbain', 'Master', 2, 2, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Diplôme d''études en architecture (DEEA)', 'Diplôme d''architecture', 3, 4, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Diplôme d''État d’architecte (ADE)', 'Diplôme d''architecture', 3, 4, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Diplôme de spécialisation et d''approfondissement en architecture « architecte-urbaniste »', 'Diplôme d''architecture', 3, 4, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Mastère spécialisé Photogrammétrie, positionnement et mesure de déformations', 'Mastère spécialisé', 1, 1, 2);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Filière Systèmes électroniques intelligents', 'Diplôme d''ingénieur', 4, 5, 3);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Diplôme d’ingénieur en géomatique', 'Diplôme d''ingénieur', 1, 1, 3);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Filière Systèmes électroniques intelligents', 'Diplôme d''ingénieur', 4, 5, 4);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('L1 à L3 Arts - Musique et Métiers du Son', 'Licence Générale', 2, 2, 5);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('L1 à L3 Arts - Études Visuelles, Multimédia et Arts Numériques', 'Licence Générale', 2, 2, 5);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Métiers du design - Design packaging et objet graphique', 'Licence Professionnelle', 2, 2, 5);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Métiers du design - Management de Projets en Communication et Industries Graphiques', 'Licence Professionnelle', 2, 2, 5);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Arts, Lettres et Civilisations - Arts numériques et cultures visuelles', 'Master', 2, 2, 5);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Arts, Lettres et Civilisations - Musique et Informatique Musicale (MIM)', 'Master', 2, 2, 5);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Arts, Lettres et Civilisations - Création et arts sonores', 'Master', 2, 2, 5);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Arts, Lettres et Civilisations - Musique et Production musicale', 'Master', 2, 2, 5);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('L1 à L3 Arts - Cinéma et Audiovisuel', 'Licence Générale', 2, 2, 6);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Arts, Lettres et Civilisations - Cinéma et audiovisuel (CAV)', 'Master', 2, 2, 6);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Guide conférencier', 'Licence Professionnelle', 2, 2, 7);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Arts, Lettres et Civilisations - Littérature, Savoirs et Culture Numérique (LSCN)', 'Master', 2, 2, 7);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Métiers de la mode', 'Licence Professionnelle', 2, 2, 8);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1 Finance Banque et assurance', 'Master', 2, 2, 9);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M2 Mathématiques et applications - Finance', 'Master', 2, 2, 9);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Actuariat', 'Master', 2, 2, 9);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Systèmes Audiovisuels Numériques', 'Licence Professionnelle', 6, 7, 10);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Administration de Réseaux Scéniques', 'Licence Professionnelle', 6, 7, 10);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Techniques du son et de l''image - Gestion de la production audiovisuelle', 'Licence Professionnelle', 2, 2, 10);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Métiers du multimédia et de l''internet (MMI)', 'DUT', 6, 7, 11);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Métiers de la Communication : chargé de communication des  collectivités territoriales et des associations', 'Licence Professionnelle', 2, 2, 11);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Métiers du Tourisme : commercialisation des produits touristiques - Nouvelles technologies de l''Information et de la Communication (NTIC)', 'Licence Professionnelle',2, 2, 11);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Sciences sociales - Communication des entreprises et médias sociaux', 'Master', 2, 2, 11);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('L3 Lettres Modernes Appliquées', 'Licence Générale', 2, 2, 12);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Métiers du livre : édition et commerce du livre - Fabrication du livre imprimé et numérique', 'Licence Professionnelle', 2, 2, 12);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('Métiers du livre : édition et commerce du livre - Fabrication du livre imprimé et numérique', 'Licence Professionnelle', 2, 2, 12);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Métiers du livre et de l''édition - Edition Livre papier et numérique ', 'Master', 2, 2, 12);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('L1 à L3 Langues, littératures et civilisations étrangères et régionales - Anglais ou Espagnol', 'Licence Générale', 2, 2, 13);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('L1&L2 Langues Etrangères appliquées - Anglais-Allemand ou Anglais-Espagnol', 'Licence Générale', 2, 2, 13);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('L3 Langues Etrangères appliquées - Anglais-Allemand ou Anglais-Espagnol Traduction spécialisée', 'Licence Générale', 2, 2, 13);
INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('M1&2 Traduction et interprétation - Traduction spécialisée', 'Master', 2, 2, 13);
