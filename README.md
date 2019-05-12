# Projet_JPO

La commanditaire de ce projet est la coordinatrice JPO de la Cité Descartes, Mandine Jarverzac. Le projet a pour but de fournir une application pour les visiteurs venant assister aux Journées Portes Ouvertes (JPO) du campus Descartes afin de les aider à profiter au maximum de ces journées. Cette application devra faciliter la visite de l’utilisateur en proposant différentes fonctionnalités d’organisation, de géolocalisation et d’affichage d’informations.

Les enjeux de ce projet sont de proposer une application fonctionnelle avec le jeu de données correspondant aux JPO 2019 qui soit facile d’utilisation pour les visiteurs de la JPO et facile à mettre à jour par les administrateurs qui réponde aux objectifs fixés ci-dessous. L’application doit être esthétiquement agréable.

# Guide d'installation

L'application Explor'Descartes ne possède pas en propre les informations du campus Descartes. Elle affiche les informations que les administrateurs auront saisi en ligne. Il est donc impératif de posséder un serveur accessible depuis l'extérieur. Dans le guide suivant, il sera décrit comment devra être configuré ce serveur et les fichiers de l'application. Ces étapes sont indispensables, car le serveur par défaut est un serveur de test privé, n'ayant pas vocation à héberger les données lorsque l'application sera diffuser publiquement.

Attention : L'application Explor'Descartes et l'environnement l'entourant a été développé dans le cadre d'un projet développement par des étudiants. L'application ne respecte donc pas forcément les normes de cybersécurité et peux donc présenter des risques.

I – Configuration du serveur :

L'application Explor'Descartes utilise plusieurs services que le serveur hébergeur se doit de posséder :

une base de données Postgres, avec l'extension PostGIS
un serveur SMTP

L'installation de ses services ne sera pas décrit dans ce guide, car les méthodes d'installation diffèrent selon les systèmes d'exploitation. De plus, des tutoriels plus exhaustif que ce qui pourraient être proposés ici sont disponibles gratuitement sur internet.


I1. Configuration de la base de données Postgres :

Une fois le Postgres installé sur votre serveur, il faut créer une base donnée qui contiendra toutes les données nécessaires à l'application. On peut faire cela en ligne de commande avec la commande createdb [nombase] ou plus simplement en utilisant un SGBD installé sur le serveur comme phpPgAdmin. Le nom de la base utilisé durant les test était test-JPO. Vous pouvez en changer, il sera décrit plus loin comment configurer l'accès à la base de données.
Il faut ensuite instancié les différentes tables et les remplir avec les données de base. Pour cela, il faut lancer les scripts SQL suivant :

 - ./BDD/BDD_Ecoles.sql
 - ./BDD/BDD_évènements.sql
 - ./BDD/BDD_FAQ.sql
 - ./BDD/BDD_Questionnaire.sql
 - ./BDD/BDD_Visiteurs.sql
 
Cette opération peut se faire en ligne de commande avec la commande \i nomfichier ou grâce à un SGBD comme phpPgAdmin.

I2. Configuration du serveur SMTP :

La configuration du serveur diffère selon le système d'exploitation utilisé.

 - Sur Windows :

Il faut entrer dans le fichier php.ini, chercher la partie [mail function] et remplir les champs avec les informations correspondantes :
+ Pour le champ SMTP : Adresse du serveur SMTP. Cela peut-être votre serveur SMTP personnel ou comme ici celui de votre fournisseur d'accès internet.
+ Pour le champ sendmail_from : Adresse mail officielle de l'application, celle-ci a été créée lors du développement. Il est donc possible d'en changer.

 - Sur Unix :

Si le package sendmail n'est pas installé, il faut lancer la commande suivante : 
sudo apt-get install sendmail
Il faut ensuite configurer sendmail. Pour cela, on ouvre le fichier avec la commande sudo nano /etc/hosts. Il faut ensuite s'assurer que la première ligne soit bien 127.0.0.1 localhost yourhostname avec yourhostname le résultat que renvoie la commande hostname. Il faut ensuite lancer la commande sudo sendmailconfig et répondre oui à chaque fois, puis relancer le serveur avec la commande sudo service apache2 restart.

Il faut ensuite entre dans le fichier php.ini, chercher la partie [mail function] et remplir le champ sendmail_path avec le chemin de sendmail.


