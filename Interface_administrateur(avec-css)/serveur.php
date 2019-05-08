<?php

// O - Variables globales

$link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");

// I - Fonctions :

// I.1 Fonctions intermédiaires :

function getIdEcole($nom){
  //Cette fonction renvoie l'id d'une école depuis son nom
  global $link;
  $requete = "SELECT id FROM ecoles WHERE nom LIKE '" . $nom . "'";
  $result = pg_query($link, $requete);
  while ($row = pg_fetch_row($result)) {
    $id_ecole = $row[0];
    return $id_ecole;
  }
}

function getIdBatiment($nom){
  //Cette fonction renvoie l'id d'un bâtiment depuis son nom
  global $link;
  $requete = "SELECT id FROM batiments WHERE nom LIKE '" . $nom . "'";
  $result = pg_query($link, $requete);
  while ($row = pg_fetch_row($result)) {
    $id_batiment = $row[0];
    return $id_batiment;
  }
}

function getIdFiliere($nom){
  //Cette fonction renvoie l'id d'une filière depuis son nom
  global $link;
  $requete = "SELECT id FROM filieres WHERE nom LIKE '" . $nom . "'";
  $result = pg_query($link, $requete);
  while ($row = pg_fetch_row($result)) {
    $id_filiere = $row[0];
    return $id_filiere;
  }
}

function getListeIdFavoris($idUtilisateur){
  //Cette fonction renvoie la liste des id des favoris d'un utilisateur
  global $link;
  $requete = "SELECT id_favoris FROM favoris WHERE id_utilisateur=" . $idUtilisateur;
  $result = pg_query($link, $requete);
  $listeIdFavoris = array();
  while ($row = pg_fetch_row($result)) {
    $listeIdFavoris[] = $row[0];
  }
  return $listeIdFavoris;
}

function getEvenementById($idFavoris){
  //Cette fonction renvoie l'évènement correspondant à l'identifiant
  global $link;
  $requete = "SELECT id, nom, debut, fin, id_ecole, id_batiment FROM evenements ORDER BY id";
  $result = pg_query($link, $requete);
  if ($result) {
    while ($row = pg_fetch_row($result)) {
      $evenement = '{"id":' . $row[0] . ', "nom":"' . $row[1] . '", "debut":"' . $row[2] . '", "fin":"' . $row[3] . '", "id_ecole":' . $row[4] . ', "id_batiment":' . $row[5] . '"}';
    }
    return $evenement;
  }
}

function newPseudo($pseudo){
  //Cette fonction renvoie true si le pseudo n'est pas déjà présent dans la base de données et false dans le cas contraire
  global $link;
  $requete = "SELECT id FROM utilisateurs WHERE pseudo LIKE '" . $pseudo . "'";
  $result = pg_query($link, $requete);
  if ($result) {
    while ($row = pg_fetch_row($result)) {
      return false;
    }
    return true;
  }
}

function newMail($mail){
  //Cette fonction renvoie true si le mail n'est pas déjà présent dans la base de données et false dans le cas contraire
  global $link;
  $requete = "SELECT id FROM utilisateurs WHERE email LIKE '" . $mail . "'";
  $result = pg_query($link, $requete);
  if ($result) {
    while ($row = pg_fetch_row($result)) {
      return false;
    }
    return true;
  }
}


// I.2 Fonctions GET :
function getListeNomObjets($nomTable){
  //Cette fonction renvoie une liste des noms des objets de la table rentrée en argument
  global $link;
  if ($nomTable == "niveaux"){
    $requete = "SELECT DISTINCT niveau FROM formations";
  }
  else if ($nomTable == "fonctions"){
    $requete = "SELECT DISTINCT fonction FROM batiments";
  }
  else {
    $requete = "SELECT nom FROM " . $nomTable;
  }
  $result = pg_query($link, $requete);
  if ($result) {
    $response = '["';
    while ($row = pg_fetch_row($result)) {
      $response = $response . $row[0] . '", "';
    }
  }
  $response = substr($response, 0, -3) . ']';
  return $response;
}
function getListeEcoles(){
  //Cette fonction renvoie la liste des ecoles
  global $link;
  $requete = "SELECT id, nom, image, adresse, site, description FROM ecoles ORDER BY id";
  $result = pg_query($link, $requete);
  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $ecole = '{"id":' . $row[0] . ', "nom":"' . $row[1] . '", "image":"' . $row[2] .'", "adresse":"' . $row[3] . '", "site":"' . $row[4] . '", "description":"' . $row[5] . '"}';
      $response = $response . $ecole . ', ';
    }
  }
  $response = substr($response, 0, -2) . ']';
  if (strlen($response) < 2){
    $response ="[]";
  }
  return $response;
}
function getListeFilieres(){
  //Cette fonction renvoie la liste des filieres
  global $link;
  $requete = "SELECT id, nom FROM filieres ORDER BY id";
  $result = pg_query($link, $requete);
  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $filiere = '{"id":' . $row[0] . ', "nom":"' . $row[1] . '"}';
      $response = $response . $filiere . ', ';
    }
  }
  $response = substr($response, 0, -2) . ']';
  if (strlen($response) < 2){
    $response ="[]";
  }
  return $response;
}
function getListeBatiments($filtreFonction){
  //Cette fonction renvoie la liste des bâtiments
  global $link;
  $requete = "SELECT id, nom, fonction, lat, lng, id_ecole, ST_AsGeoJSON(geometrie) FROM batiments WHERE fonction LIKE '" . $filtreFonction . "' ORDER BY id";
  $result = pg_query($link, $requete);
  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      if ($row[5] == null){
        // Géométrie non renseignée
        $row[5] = "{}";
      }
      $batiment = '{"id":' . $row[0] . ', "nom":"' . $row[1] . '", "fonction":"' . $row[2] . '", "lat":' . $row[3] . ', "lng":' . $row[4] . ', "id_ecole":' . $row[5] .', "geometrie":' . $row[6] . '}';
      $response = $response . $batiment . ', ';
    }
  }
  $response = substr($response, 0, -2) . ']';
  if (strlen($response) < 2){
    $response ="[]";
  }
  return $response;
}
function getListeFormations($filtreNiveau, $filtreEcole, $filtreBatiment, $filtreFiliere){
  //Cette fonction renvoie la liste des formations
  global $link;
  $requete = "SELECT f.id, f.nom, f.niveau, ecoles.nom, batiments.nom, filieres.nom
  FROM formations AS f LEFT JOIN ecoles ON f.id_ecole = ecoles.id
  LEFT JOIN batiments ON f.id_batiment = batiments.id
  LEFT JOIN filieres ON f.id_filiere = filieres.id
  WHERE f.niveau LIKE '" . $filtreNiveau . "' AND ecoles.nom LIKE '" . $filtreEcole . "' AND batiments.nom LIKE '" . $filtreBatiment . "' AND filieres.nom LIKE '" . $filtreFiliere . "' ORDER BY f.id";
  $result = pg_query($link, $requete);
  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $formation = '{"id":' . $row[0] . ', "nom":"' . $row[1] . '", "niveau":"' . $row[2] . '", "ecole":"' . $row[3] . '", "batiment":"' . $row[4] . '", "filiere":"' . $row[5] . '"}';
      $response = $response . $formation . ', ';
    }
  }
  $response = substr($response, 0, -2) . ']';
  if (strlen($response) < 2){
    $response ="[]";
  }
  return $response;
}

function getListeEvenements($filtreEcole, $filtreBatiment){
  //Cette fonction renvoie la liste des evenements
  global $link;
  $requete = "SELECT e.id, e.nom, e.debut, e.fin, ecoles.nom, batiments.nom
  FROM evenements AS e LEFT JOIN ecoles ON e.id_ecole = ecoles.id
  LEFT JOIN batiments ON e.id_batiment = batiments.id
  WHERE ecoles.nom LIKE '" . $filtreEcole . "' AND batiments.nom LIKE '" . $filtreBatiment . "' ORDER BY e.id";
  $result = pg_query($link, $requete);
  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $evenement = '{"id":' . $row[0] . ', "nom":"' . $row[1] . '", "debut":"' . $row[2] . '", "fin":"' . $row[3]. '", "ecole":"' . $row[4] . '", "batiment":"' . $row[5] . '"}';
      $response = $response . $evenement . ', ';
    }
  }
  $response = substr($response, 0, -2) . ']';
  if (strlen($response) < 2){
    $response ="[]";
  }
  return $response;
}

function getListeUtilisateurs($filtreAdmin){
  //Cette fonction renvoie la liste des utilisateurs
  global $link;
  if ($filtreAdmin != null){
	  $requete = "SELECT id, prenom, nom, pseudo, email,admin FROM utilisateurs WHERE admin=" . $filtreAdmin ." ORDER BY id";
  }else{
	  $requete = "SELECT id, prenom, nom, pseudo, email,admin FROM utilisateurs ORDER BY id";
  }
  $result = pg_query($link, $requete);
  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $utilisateur = '{"id":' . $row[0] . ', "prenom":"' . $row[1] . '", "nom":"' . $row[2] . '", "pseudo":"' . $row[3] . '", "email":"' . $row[4] . '", "admin":"' . $row[5] .'"}';
      $response = $response . $utilisateur . ', ';
    }
  }
  $response = substr($response, 0, -2) . ']';
  if (strlen($response) < 2){
    $response ="[]";
  }

  return $response;
}

function getListeSatisfaction(){
  //Cette fonction renvoie la liste des utilisateurs
  global $link;
  $requete = "SELECT * FROM satisfaction  ORDER BY id";

  $result = pg_query($link, $requete);
  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $utilisateur = '{"id":' . $row[0] . ', "question_1":"' . $row[1] . '",  "question_2":"' . $row[2] . '",  "question_3":"' . $row[3] . '",  "question_4":"' . $row[4] . '", "question_5":"' . $row[5] . '", "question_6":"' . $row[6] . '", "question_7":"' . $row[7] . '", "question_8":"' . $row[8] . '", "question_9":"' . $row[9] . '", "question_10":"' . $row[10] . '", "question_11":"' . $row[11] . '", "question_12":"' . $row[12] . '", "question_13":"' . $row[13] . '", "question_14":"' . $row[14] . '", "question_15":"' . $row[15] . '", "question_16":"' . $row[16] . '", "question_17":"' . $row[17] . '", "question_18":"' . $row[18] . '", "question_19":"' . $row[19] . '", "question_20":"' . $row[20] . '"}';
      $response = $response . $utilisateur . ', ';
    }
  }
  $response = substr($response, 0, -2) . ']';
  if (strlen($response) < 2){
    $response ="[]";
  }

  return $response;
}


function getListeQuestionnaire(){
  //Cette fonction renvoie la liste des utilisateurs
  global $link;
  $requete = "SELECT id, question_1, question_2, question_3 FROM questionnaire  ORDER BY id";

  $result = pg_query($link, $requete);
  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $utilisateur = '{"id":' . $row[0] . ', "question_1":"' . $row[1] . '", "question_2":"' . $row[2] . '", "question_3":"' . $row[3] . '"}';
      $response = $response . $utilisateur . ', ';
    }
  }
  $response = substr($response, 0, -2) . ']';
  if (strlen($response) < 2){
    $response ="[]";
  }

  return $response;
}

function getListeFAQ(){
  //Cette fonction renvoie la liste des utilisateurs
  global $link;
  $requete = "SELECT id, question, reponse FROM faq";
  $result = pg_query($link, $requete);
  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $utilisateur = '{"id":' . $row[0] . ', "question":"' . $row[1] . '", "reponse":"' . $row[2] . '"}';
      $response = $response . $utilisateur . ', ';
    }
  }
  $response = substr($response, 0, -2) . ']';
  if (strlen($response) < 2){
    $response ="[]";
  }

  return $response;
}

function getLastUtilisateur(){
  //Cette fonction renvoie le dernier utilisateur créé
  global $link;
  $requete = "SELECT id, prenom, nom, pseudo, email, mdp, admin FROM utilisateurs ORDER BY id DESC";
  $result = pg_query($link, $requete);
  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
	    $id = $row[0];
      $utilisateur = '{"id":' . $row[0] . ', "prenom":"' . $row[1] . '", "nom":"' . $row[2] . '", "pseudo":"' . $row[3] . '", "email":"' . $row[4] . '", "mdp":"' . $row[5] . '", "admin":"' . $row[6] . '", "listeFavoris":';
      $listeIdFavoris = getListeIdFavoris($id);
      $liste = "[";
      if (count($listeIdFavoris) == 0){
        $liste = "[]";
      }else {
        foreach ($listeIdFavoris as $i => $idFavoris) {
          $liste = $liste . $idFavoris . ", ";
        }
        $liste = substr($liste, 0, -2) . ']';
      }
      $utilisateur = $utilisateur . $liste . "}";
      return $utilisateur;
    }
  }
}

function getUtilisateurByMailAndMdp($mail, $mdp){
  //Cette fonction renvoie l'utilisateur à partir de son email ou son pseudo et de son mot de passe
  global $link;
  $requete = "SELECT id, prenom, nom, pseudo, email, mdp, admin FROM utilisateurs WHERE (email LIKE '" . $mail . "' OR pseudo LIKE '" . $mail . "') AND mdp='" . $mdp . "'";
  $result = pg_query($link, $requete);
  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
	    $id = $row[0];
      $utilisateur = '{"id":' . $row[0] . ', "prenom":"' . $row[1] . '", "nom":"' . $row[2] . '", "pseudo":"' . $row[3] . '", "email":"' . $row[4] . '", "mdp":"' . $row[5] . '", "admin":"' . $row[6] . '", "listeFavoris":';
      $listeIdFavoris = getListeIdFavoris($id);
      $liste = "[";
      if (count($listeIdFavoris) == 0){
        $liste = "[]";
      }else {
        foreach ($listeIdFavoris as $i => $idFavoris) {
          $liste = $liste . $idFavoris . ", ";
        }
        $liste = substr($liste, 0, -2) . ']';
      }
      $utilisateur = $utilisateur . $liste . "}";
      return $utilisateur;
    }
  }
}

function getUtilisateurByPseudoAndMdp($pseudo, $mdp){
  //Cette fonction renvoie l'utilisateur à partir de son mail et de son mdp
  global $link;
  $requete = "SELECT id, prenom, nom, pseudo, email, mdp, admin FROM utilisateurs WHERE pseudo=" . $pseudo . ' AND mdp=' . $mdp;
  $result = pg_query($link, $requete);
  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
	    $id = $row[0];
      $utilisateur = '{"id":' . $row[0] . ', "prenom":"' . $row[1] . '", "nom":"' . $row[2] . '", "pseudo":"' . $row[3] . '", "email":"' . $row[4] . '", "mdp":"' . $row[5] . '", "admin":"' . $row[6] . '", "listeFavoris":';
      $listeIdFavoris = getListeIdFavoris($id);
      $liste = "[";
      if (count($listeIdFavoris) == 0){
        $liste = "[]";
      }else {
        foreach ($listeIdFavoris as $i => $idFavoris) {
          $liste = $liste . $idFavoris . ", ";
        }
        $liste = substr($liste, 0, -2) . ']';
      }
      $utilisateur = $utilisateur . $liste . "}";
      return $utilisateur;
    }
  }
}

function getBatimentById($id){
  //Cette fonction renvoie la liste des bâtiments
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT id, nom, fonction, lat, lng FROM batiments WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result) {
    while ($row = pg_fetch_row($result)) {
      $batiment = '{"id":' . $row[0] . ', "nom":"' . $row[1] . '", "fonction":"' . $row[2] . '", "lat":' . $row[3] . ', "lng":' . $row[4] . '}';
    }
  }
  return $batiment;
}


// I.3 Fonctions SAVE :
function saveEcole ($nom, $image, $adresse, $site, $description){
  //Cette fonction enregistre une nouvelle école dans la base de données
  $nom = str_replace("'", "''", $nom);
  $image = str_replace("'", "''", $image);
  $adresse = str_replace("'", "''", $adresse);
  $description = str_replace("'", "''", $description);
  global $link;
  $requete = "INSERT INTO ecoles (nom, image, adresse, site, description) VALUES ('" . $nom . "', '" . $image . "', '" . $adresse . "', '" . $site . "', '" . $description . "')";
  $result = pg_query($link, $requete);
  if ($result){
    return "Sauvegarde réussie !";
  }else{
    return "La sauvegarde a échouée";
  }
}
function saveQuestionnaire ($question_1,$question_2,$question_3){
  //Cette fonction enregistre une nouvelle école dans la base de données
  $question_1 = str_replace("'", "''", $question_1);
  $question_2 = str_replace("'", "''", $question_2);
  $question_3 = str_replace("'", "''", $question_3);
  global $link;
  $requete = "INSERT INTO questionnaire (question_1, question_2,question_3) VALUES ('" . $question_1 . "','" . $question_2 . "','" . $question_3 . "')";

  $result = pg_query($link, $requete);
  if ($result){
    return "Sauvegarde réussie !";
  }else{
    return "La sauvegarde a échouée";
  }
}
function saveBatiment ($nom, $fonction, $lat, $lng){
  //Cette fonction enregistre un nouveau batiment dans la base de données
  $nom = str_replace("'", "''", $nom);
  $fonction = str_replace("'", "''", $fonction);
  global $link;
  $requete = "INSERT INTO batiments (nom, fonction, lat, lng) VALUES ('" . $nom . "', '" . $fonction . "', " . $lat . ", " . $lng . ")";
  $result = pg_query($link, $requete);
  if ($result){
    return "Sauvegarde réussie !";
  }else{
    return "La sauvegarde a échouée";
  }
}
function saveFiliere ($nom){
  //Cette fonction enregistre un nouveau batiment dans la base de données
  $nom = str_replace("'", "''", $nom);
  global $link;
  $requete = "INSERT INTO filieres (nom) VALUES ('" . $nom . "')";
  $result = pg_query($link, $requete);
  if ($result){
    return "Sauvegarde réussie !";
  }else{
    return "La sauvegarde a échouée";
  }
}
function saveFormation ($nom, $niveau, $ecole, $batiment, $filiere){
  //Cette fonction enregistre une nouvelle formation dans la base de données
  $nom      = str_replace("'", "''", $nom);
  $niveau   = str_replace("'", "''", $niveau);
  $ecole    = str_replace("'", "''", $ecole);
  $batiment = str_replace("'", "''", $batiment);
  $filiere  = str_replace("'", "''", $filiere);
  // Détermination des id des objets
  $id_ecole    = getIdEcole($ecole);
  $id_batiment = getIdBatiment($batiment);
  $id_filiere  = getIdFiliere($filiere);
  global $link;
  $requete = "INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('" . $nom . "', '" . $niveau . "', " . $id_ecole . ", " . $id_batiment . ", " . $id_filiere . ")";
  $result = pg_query($link, $requete);
  if ($result){
    return "Sauvegarde réussie !";
  }else{
    return "La sauvegarde a échouée";
  }
}
function saveUtilisateur ($prenom, $nom, $pseudo, $email, $mdp, $admin){
  //Cette fonction enregistre un nouvel utilisateur dans la base de données
  $prenom   = str_replace("'", "''", $prenom);
  $nom      = str_replace("'", "''", $nom);
  $pseudo   = str_replace("'", "''", $pseudo);
  $email    = str_replace("'", "''", $email);
  $mdp      = str_replace("'", "''", $mdp);

  // Vérification des nouveaux identifiants
  if (!newPseudo($pseudo)){
    return "Pseudo déjà utilisé !";
  }
  if (!newMail($email)){
    return "Email déjà utilisé !";
  }

  global $link;
  $requete = "INSERT INTO utilisateurs (prenom, nom, pseudo, email, mdp, admin) VALUES ('" . $prenom . "', '" . $nom . "', '" . $pseudo . "', '" . $email . "', '" . $mdp . "', '" . $admin . "')";
  $result = pg_query($link, $requete);
  if ($result){
    return "Sauvegarde réussie !";
  }else{
    return "La sauvegarde a échouée";
  }
}

function saveEvenement ($nom, $debut, $fin, $ecole, $batiment){
  //Cette fonction enregistre un nouvel utilisateur dans la base de données
  $nom = str_replace("'", "''", $nom);
  // Détermination des id des objets
  $id_ecole    = getIdEcole($ecole);
  $id_batiment = getIdBatiment($batiment);
  global $link;
  $requete = "INSERT INTO evenements (nom, debut, fin, id_ecole, id_batiment) VALUES ('" . $nom . "', '" . $debut . "', '" . $fin . "', '" . $id_ecole . "', '" . $id_batiment . "')";
  $result = pg_query($link, $requete);
  if ($result){
    return "Sauvegarde réussie !";
  }else{
    return "La sauvegarde a échouée";
  }
}

function saveFAQ ($question,$reponse){
  //Cette fonction enregistre un nouveau batiment dans la base de données
  $question = str_replace("'", "''", $question);
  $reponse = str_replace("'", "''", $reponse);
  global $link;
  $requete = "INSERT INTO faq (question,reponse) VALUES ('" . $question . "','" . $reponse . "')";
  $result = pg_query($link, $requete);
  if ($result){
    return "Sauvegarde réussie !";
  }else{
    return "La sauvegarde a échouée";
  }
}

function saveSatisfaction($question_1,$question_2,$question_3,$question_4,$question_5,$question_6,$question_7,$question_8,$question_9,$question_10,$question_11,$question_12,$question_13,$question_14,$question_15,$question_16,$question_17,$question_18,$question_19,$question_20,$question_21,$question_22,$question_23){
  //Cette fonction enregistre un nouveau batiment dans la base de données
  $question_1 = str_replace("'", "''", $question_1);
  $question_2 = str_replace("'", "''", $question_2);
  $question_3 = str_replace("'", "''", $question_3);
  $question_4 = str_replace("'", "''", $question_4);
  $question_5 = str_replace("'", "''", $question_5);
  $question_6 = str_replace("'", "''", $question_6);
  $question_7 = str_replace("'", "''", $question_7);
  $question_8 = str_replace("'", "''", $question_8);
  $question_9 = str_replace("'", "''", $question_9);
  $question_10 = str_replace("'", "''", $question_10);
  $question_11 = str_replace("'", "''", $question_11);
  $question_12 = str_replace("'", "''", $question_12);
  $question_13 = str_replace("'", "''", $question_13);
  $question_14 = str_replace("'", "''", $question_14);
  $question_15 = str_replace("'", "''", $question_15);
  $question_16 = str_replace("'", "''", $question_16);
  $question_17 = str_replace("'", "''", $question_17);
  $question_18 = str_replace("'", "''", $question_18);
  $question_19 = str_replace("'", "''", $question_19);
  $question_20 = str_replace("'", "''", $question_20);
  $question_21 = str_replace("'", "''", $question_21);
  $question_22 = str_replace("'", "''", $question_22);
  $question_23 = str_replace("'", "''", $question_23);
  
  global $link;
  $requete = "INSERT INTO satisfaction (question_1,question_2,question_3,question_4,question_5,question_6,question_7,question_8,question_9,question_10,question_11,question_12,question_13,question_14,question_15,question_16,question_17,question_18,question_19,question_20,question_21,question_22,question_23) VALUES ('" . $question_1 . "','" . $question_2 . "','" . $question_3 . "','" . $question_4 . "','" . $question_5 . "','" . $question_6 . "','" . $question_7 . "','" . $question_8 . "','" . $question_9 . "','" . $question_10 . "','" . $question_11 . "','" . $question_12 . "','" . $question_13 . "','" . $question_14 . "','" . $question_15 . "','" . $question_16 . "','" . $question_17 . "','" . $question_18 . "','" . $question_19 . "','" . $question_20 . "','" . $question_21 . "','" . $question_22 . "','" . $question_23 . "')";
  $result = pg_query($link, $requete);
  if ($result){
    return "Sauvegarde réussie !";
  }else{
    return "La sauvegarde a échouée";
  }
}



// I.4 Fonctions CHANGE :
function changeEcole ($id, $nom, $image, $adresse, $site, $description){
  //Cette fonction modifie une école déjà existante dans la base de données
  $nom = str_replace("'", "''", $nom);
  $image = str_replace("'", "''", $image);
  $adresse = str_replace("'", "''", $adresse);
  $description = str_replace("'", "''", $description);
  global $link;
  $requete = "UPDATE ecoles
              SET nom = '" . $nom . "', image = '" . $image . "', adresse = '" . $adresse . "', site = '" . $site . "', description = '" . $description . "'
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Modification réussie !";
  }else{
    return "La modification a échouée";
  }
}
function changeBatiment ($id, $nom, $fonction, $lat, $lng){
  //Cette fonction modifie un batiment déjà existant dans la base de données
  $nom = str_replace("'", "''", $nom);
  $fonction = str_replace("'", "''", $fonction);
  global $link;
  $requete = "UPDATE batiments
              SET nom = '" . $nom . "', fonction = '" . $fonction . "', lat = '" . $lat . "', lng = '" . $lng . "'
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Modification réussie !";
  }else{
    return "La modification a échouée";
  }
}
function changeFiliere ($id, $nom){
  //Cette fonction modifie une filière déjà existante dans la base de données
  $nom = str_replace("'", "''", $nom);
  global $link;
  $requete = "UPDATE filieres
              SET nom = '" . $nom . "'
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Modification réussie !";
  }else{
    return "La modification a échouée";
  }
}
function changeFormation ($id, $nom, $niveau, $ecole, $batiment, $filiere){
  //Cette fonction modifie une formation déjà existante dans la base de données
  $nom      = str_replace("'", "''", $nom);
  $niveau   = str_replace("'", "''", $niveau);
  $ecole    = str_replace("'", "''", $ecole);
  $batiment = str_replace("'", "''", $batiment);
  $filiere  = str_replace("'", "''", $filiere);
  // Détermination des id des objets
  $id_ecole    = getIdEcole($ecole);
  $id_batiment = getIdBatiment($batiment);
  $id_filiere  = getIdFiliere($filiere);
  global $link;
  $requete = "UPDATE formations
              SET nom = '" . $nom . "', niveau = '" . $niveau . "', id_ecole = '" . $id_ecole . "', id_batiment = '" . $id_batiment ."', id_filiere = '" . $id_filiere . "'
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Modification réussie !";
  }else{
    return "La modification a échouée";
  }
}
function changeEvenement ($id, $nom, $debut, $fin, $id_ecole, $id_batiment){
  //Cette fonction modifie une formation déjà existante dans la base de données
  $nom      = str_replace("'", "''", $nom);
  $debut  = str_replace("'", "''", $debut);
  $fin    = str_replace("'", "''", $fin);
  $id_ecole = str_replace("'", "''", $id_ecole);
  $id_batiment  = str_replace("'", "''", $id_batiment);
  // Détermination des id des objets
  $ecole    = getIdEcole($id_ecole);
  $batiment = getIdBatiment($id_batiment);

  global $link;
  $requete = "UPDATE evenements
              SET nom = '" . $nom . "', debut = '" . $debut . "', fin = '" . $fin . "', id_ecole = '" . $ecole . "', id_batiment = '" . $batiment ."'
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Modification réussie !";
  }else{
    return "La modification a échouée";
  }
}
function changeUtilisateur ($id, $prenom, $nom, $pseudo, $email, $mdp, $admin){
  //Cette fonction modifie un utilisateur déjà existant dans la base de données
  $prenom   = str_replace("'", "''", $prenom);
  $nom      = str_replace("'", "''", $nom);
  $pseudo   = str_replace("'", "''", $pseudo);
  $email    = str_replace("'", "''", $email);
  $mdp      = str_replace("'", "''", $mdp);
  global $link;
  $requete = "UPDATE utilisateurs
              SET prenom = '" . $prenom . "', nom = '" . $nom . "', pseudo = '" . $pseudo . "', email = '" . $email . "', mdp = '" . $mdp . "', admin = '" . $admin ."'
             WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Modification réussie !";
  }else{
    return "La modification a échouée";
  }
}


function changeFAQ ($id, $question, $reponse){
  //Cette fonction modifie une formation déjà existante dans la base de données
  $question      = str_replace("'", "''", $question);
  $reponse   = str_replace("'", "''", $reponse);

  global $link;
  $requete = "UPDATE faq
              SET question = '" . $question . "', reponse = '" . $reponse . "'
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Modification réussie !";
  }else{
    return "La modification a échouée";
  }
}


// I.5 Fonctions DELETE :
function deleteEcole ($id){
  //Cette fonction supprime une école dans la base de données
  global $link;
  $requete = "DELETE FROM ecoles
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Suppression réussie !";
  }else{
    return "La suppression a échouée";
  }
}
function deleteBatiment ($id){
  //Cette fonction supprime un batiment dans la base de données
  global $link;
  $requete = "DELETE FROM batiments
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if (true){
    return "Suppression réussie !";
  }else{
    return "La suppression a échouée";
  }
}
function deleteFiliere ($id){
  //Cette fonction supprime une filiere dans la base de données
  global $link;
  $requete = "DELETE FROM filieres
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Suppression réussie !";
  }else{
    return "La suppression a échouée";
  }
}
function deleteFormation ($id){
  //Cette fonction supprime une formation dans la base de données
  global $link;
  $requete = "DELETE FROM formations
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Suppression réussie !";
  }else{
    return "La suppression a échouée";
  }
}
function deleteUtilisateur ($id){
  //Cette fonction supprime un utlisateru dans la base de données
  global $link;
  $requete = "DELETE FROM utilisateurs
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Suppression réussie !";
  }else{
    return "La suppression a échouée";
  }
}
function deleteEvenement ($id){
  //Cette fonction supprime un utlisateru dans la base de données
  global $link;
  $requete = "DELETE FROM evenements
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Suppression réussie !";
  }else{
    return "La suppression a échouée";
  }
}

function deleteFAQ($id){
  //Cette fonction supprime une école dans la base de données
  global $link;
  $requete = "DELETE FROM faq
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Suppression réussie !";
  }else{
    return "La suppression a échouée";
  }
}

// I.6 Fonctions application :

function ajouterFavori($idUtilisateur, $idFavoris){
  // Ajoute un favori dans la table favoris
  global $link;
  $requete = "INSERT INTO favoris (id_utilisateur, id_favoris) VALUES (" . $idUtilisateur . "," . $idFavoris .")";
  $result = pg_query($link, $requete);
  if ($result){
    return "Ajout du favori réussi !";
  }
}

function supprimerFavori($idUtilisateur, $idFavoris){
  // Supprime un favori dans la table favoris
  global $link;
  $requete = "DELETE FROM favoris WHERE id_utilisateur=" . $idUtilisateur . " AND id_favoris=" . $idFavoris;
  $result = pg_query($link, $requete);
  if ($result){
    return "Suppression du favori réussi !";
  }
}


// II - Requêtes :
// II.1 Requêtes GET :
if (isset($_GET['request']) && $_GET['request'] == "listeNomObjets" && isset($_GET['nomTable'])){
  $nomTable = $_GET['nomTable'];
  echo getListeNomObjets($nomTable);
}
if (isset($_GET['request']) && $_GET['request'] == "listeEcoles"){
  echo getListeEcoles();
}
if (isset($_GET['request']) && $_GET['request'] == "listeFilieres"){
  echo getListeFilieres();
}
if (isset($_GET['request']) && $_GET['request'] == "listeBatiments"){
  if (isset($_GET['filtreFonction'])) {
    $filtreFonction = $_GET['filtreFonction'];
  }
  else {
    $filtreFonction = "%";
  }
  echo getListeBatiments($filtreFonction);
}

if (isset($_GET['request']) && $_GET['request'] == "listeFormations"){
  if (isset($_GET['filtreNiveau'])) {
    $filtreNiveau = $_GET['filtreNiveau'];
  }
  else {
    $filtreNiveau = "%";
  }
  if (isset($_GET['filtreEcole'])) {
    $filtreEcole = $_GET['filtreEcole'];
  }
  else {
    $filtreEcole = "%";
  }
  if (isset($_GET['filtreBatiment'])) {
    $filtreBatiment = $_GET['filtreBatiment'];
  }
  else {
    $filtreBatiment = "%";
  }
  if (isset($_GET['filtreFiliere'])) {
    $filtreFiliere = $_GET['filtreFiliere'];
  }
  else {
    $filtreFiliere = "%";
  }
  echo getListeFormations($filtreNiveau, $filtreEcole, $filtreBatiment, $filtreFiliere);
}

if (isset($_GET['request']) && $_GET['request'] == "listeEvenements"){
  if (isset($_GET['filtreEcole'])) {
    $filtreEcole = $_GET['filtreEcole'];
  }
  else {
    $filtreEcole = "%";
  }
  if (isset($_GET['filtreBatiment'])) {
    $filtreBatiment = $_GET['filtreBatiment'];
  }
  else {
    $filtreBatiment = "%";
  }
  echo getListeEvenements($filtreEcole, $filtreBatiment);
}

if (isset($_GET['request']) && $_GET['request'] == "listeUtilisateurs"){
  if (isset($_GET['filtreAdmin'])) {
    $filtreAdmin = $_GET['filtreAdmin'];
  }
  else {
    $filtreAdmin = null;
  }
  echo getListeUtilisateurs($filtreAdmin);
}

if (isset($_GET['request']) && $_GET['request'] == "listeFAQ"){
  echo getListeFAQ();
}

if (isset($_GET['request']) && $_GET['request'] == "listeSatisfaction"){
  echo getListeSatisfaction();
}
if (isset($_GET['request']) && $_GET['request'] == "listeQuestionnaire"){
  echo getListeQuestionnaire();
}

if (isset($_GET['request']) && $_GET['request'] == "batiment"){
  $id = $_GET['id'];
  echo getBatimentById($id);
}

if (isset($_GET['request']) && $_GET['request'] == "connexion"){
  $mail = $_GET['mail'];
  $mdp  = $_GET['mdp'];
  echo getUtilisateurByMailAndMdp($mail, $mdp);
}




// II.2 Requêtes SAVE :
if (isset($_GET['request']) && $_GET['request'] == "saveEcole"){
  $nom         = $_GET['nom'];
  $image       = $_GET['image'];
  $adresse     = $_GET['adresse'];
  $site        = $_GET['site'];
  $description = $_GET['description'];
  echo saveEcole ($nom, $image, $adresse, $site, $description);
}

if (isset($_GET['request']) && $_GET['request'] == "saveBatiment"){
  $nom      = $_GET['nom'];
  $fonction = $_GET['fonction'];
  $lat      = $_GET['lat'];
  $lng      = $_GET['lng'];
  echo saveBatiment ($nom, $fonction, $lat, $lng);
}

if (isset($_GET['request']) && $_GET['request'] == "saveFiliere"){
  $nom = $_GET['nom'];
  echo saveFiliere ($nom);
}

if (isset($_GET['request']) && $_GET['request'] == "saveFormation"){
  $nom      = $_GET['nom'];
  $niveau   = $_GET['niveau'];
  $ecole    = $_GET['ecole'];
  $batiment = $_GET['batiment'];
  $filiere  = $_GET['filiere'];
  echo saveFormation ($nom, $niveau, $ecole, $batiment, $filiere);
}

if (isset($_GET['request']) && $_GET['request'] == "saveUtilisateur"){
  $prenom   = $_GET['prenom'];
  $nom      = $_GET['nom'];
  $pseudo   = $_GET['pseudo'];
  $email    = $_GET['email'];
  $mdp      = $_GET['mdp'];
  $admin    = $_GET['admin'];
  echo saveUtilisateur ($prenom, $nom, $pseudo, $email, $mdp, $admin);
}

if (isset($_GET['request']) && $_GET['request'] == "saveEvenement"){
  $nom   = $_GET['nom'];
  $debut      = $_GET['debut'];
  $fin  = $_GET['fin'];
  $id_ecole    = $_GET['id_ecole'];
  $id_batiment     = $_GET['id_batiment'];
  echo saveEvenement($nom, $debut, $fin, $id_ecole, $id_batiment);
}

if (isset($_GET['request']) && $_GET['request'] == "saveSatisfaction"){
  $question_1   = $_GET['question_1'];
  $question_2      = $_GET['question_2'];
  $question_3  = $_GET['question_3'];
  $question_1   = $_GET['question_4'];
  $question_1   = $_GET['question_5'];
  $question_1   = $_GET['question_6'];
  $question_1   = $_GET['question_7'];
  $question_1   = $_GET['question_8'];
  $question_1   = $_GET['question_9'];
  $question_1   = $_GET['question_10'];
  $question_1   = $_GET['question_11'];
  $question_1   = $_GET['question_12'];
  $question_1   = $_GET['question_13'];
  $question_1   = $_GET['question_14'];
  $question_1   = $_GET['question_15'];
  $question_1   = $_GET['question_16'];
  $question_1   = $_GET['question_17'];
  $question_1   = $_GET['question_18'];
  $question_1   = $_GET['question_19'];
  $question_1   = $_GET['question_20'];
  $question_1   = $_GET['question_21'];
  $question_1   = $_GET['question_22'];
  $question_1   = $_GET['question_23'];
  echo saveQuestionnaire($question_1, $question_2, $question_3, $question_4, $question_5, $question_6, $question_7, $question_8, $question_9, $question_10, $question_11, $question_12, $question_13, $question_14, $question_15, $question_16, $question_17, $question_18, $question_19, $question_20, $question_21, $question_22, $question_23);
}


if (isset($_GET['request']) && $_GET['request'] == "saveFAQ"){
  $question         = $_GET['question'];
  $reponse       = $_GET['reponse'];
  echo saveFAQ ($question, $reponse);
}
// II.3 Requêtes CHANGE :
if (isset($_GET['request']) && $_GET['request'] == "changeEcole"){
  $id          = $_GET['id'];
  $nom         = $_GET['nom'];
  $image       = $_GET['image'];
  $adresse     = $_GET['adresse'];
  $site        = $_GET['site'];
  $description = $_GET['description'];
  echo changeEcole ($id, $nom,$image, $adresse, $site, $description);
}
if (isset($_GET['request']) && $_GET['request'] == "changeBatiment"){
  $id       = $_GET['id'];
  $nom      = $_GET['nom'];
  $fonction = $_GET['fonction'];
  $lat      = $_GET['lat'];
  $lng      = $_GET['lng'];
  echo changeBatiment ($id, $nom, $fonction, $lat, $lng);
}
if (isset($_GET['request']) && $_GET['request'] == "changeFiliere"){
  $id  = $_GET['id'];
  $nom = $_GET['nom'];
  echo changeFiliere ($id, $nom);
}
if (isset($_GET['request']) && $_GET['request'] == "changeFormation"){
  $id       = $_GET['id'];
  $nom      = $_GET['nom'];
  $niveau   = $_GET['niveau'];
  $ecole    = $_GET['id_ecole'];
  $batiment = $_GET['id_batiment'];
  $filiere  = $_GET['id_filiere'];
  echo changeFormation ($id, $nom, $niveau, $ecole, $batiment, $filiere);
}
if (isset($_GET['request']) && $_GET['request'] == "changeUtilisateur"){
  $id       = $_GET['id'];
  $prenom   = $_GET['prenom'];
  $nom      = $_GET['nom'];
  $pseudo   = $_GET['pseudo'];
  $email    = $_GET['email'];
  $mdp      = $_GET['mdp'];
  $admin    = $_GET['admin'];
  echo changeUtilisateur ($id, $prenom, $nom, $pseudo, $email, $mdp, $admin);
}
if (isset($_GET['request']) && $_GET['request'] == "changeEvenement"){
  $id       = $_GET['id'];
  $nom   = $_GET['nom'];
  $debut     = $_GET['debut'];
  $fin   = $_GET['fin'];
  $id_ecole    = $_GET['id_ecole'];
  $id_batiment      = $_GET['id_batiment'];
  echo changeEvenement ($id, $nom, $debut, $fin, $id_ecole, $id_batiment);
}
if (isset($_GET['request']) && $_GET['request'] == "changeFAQ"){
  $id          = $_GET['id'];
  $question         = $_GET['question'];
  $reponse       = $_GET['reponse'];
  echo changeFAQ ($id, $question, $reponse);
}
// II.4 Requêtes DELETE :
if (isset($_GET['request']) && $_GET['request'] == "deleteEcole"){
  $id  = $_GET['id'];
  echo deleteEcole ($id);
}
if (isset($_GET['request']) && $_GET['request'] == "deleteBatiment"){
  $id  = $_GET['id'];
  echo deleteBatiment ($id);
}
if (isset($_GET['request']) && $_GET['request'] == "deleteFiliere"){
  $id  = $_GET['id'];
  echo deleteFiliere ($id);
}
if (isset($_GET['request']) && $_GET['request'] == "deleteFormation"){
  $id  = $_GET['id'];
  echo deleteFormation ($id);
}
if (isset($_GET['request']) && $_GET['request'] == "deleteUtilisateur"){
  $id  = $_GET['id'];
  echo deleteUtilisateur ($id);
}
if (isset($_GET['request']) && $_GET['request'] == "deleteEvenement"){
  $id  = $_GET['id'];
  echo deleteEvenement ($id);
}
if (isset($_GET['request']) && $_GET['request'] == "deleteFAQ"){
  $id  = $_GET['id'];
  echo deleteFAQ ($id);
}

// II.5 Requêtes application :
if (isset($_GET['request']) && $_GET['request'] == "ajouterFavori"){
  $idUtilisateur = $_GET['idUtilisateur'];
  $idFavoris     = $_GET['idFavoris'];
  echo ajouterFavori($idUtilisateur, $idFavoris);
}

if (isset($_GET['request']) && $_GET['request'] == "supprimerFavori"){
  $idUtilisateur = $_GET['idUtilisateur'];
  $idFavoris     = $_GET['idFavoris'];
  echo supprimerFavori($idUtilisateur, $idFavoris);
}

if (isset($_GET['request']) && $_GET['request'] == "newMail"){
  $mail = $_GET['mail'];
  echo newMail($mail);
}

// III - Tests unitaires :
if (isset($_GET['request']) && $_GET['request'] == "testUnitaire"){
  echo '<b>Test de "getListeNomObjets" :</b>' . '<br />' . '<br />';
  echo '- Ecoles :</b>'."<br />";
  echo '- ' . getListeNomObjets("ecoles") . '<br />' . '<br />' ;
  echo '- Filières :</b>'."<br />";
  echo '- ' . getListeNomObjets("filieres") . '<br />' . '<br />';
  echo '- Bâtiments :</b>'."<br />";
  echo '- ' . getListeNomObjets("batiments") . '<br />' . '<br />';
  echo '- Niveaux :</b>'."<br />";
  echo '- ' . getListeNomObjets("niveaux") . '<br />' . '<br />';
  echo '- Fonctions :</b>'."<br />";
  echo '- ' . getListeNomObjets("fonctions") . '<br />' . '<br />' . '<br />';
  echo '<b>Test de "getListeEcoles" :</b>' . '<br />' . '<br />';
  echo '- ' . getListeEcoles() . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "getListeBatiments" :</b>' . '<br />' . '<br />';
  echo '- ' . getListeBatiments("%") . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "getListeFilieres" :</b>' . '<br />' . '<br />';
  echo '- ' . getListeFilieres() . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "getListeFormations" :</b>' . '<br />' . '<br />';
  echo '- ' . getListeFormations("%", "%", "%", "%") . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "saveEcole" :</b>' . '<br />' . '<br />';
  echo '- ' . saveEcole("ecoleTest", "testImage.jpg", "test adresse", "www.test-site.com", "descriptionTest") . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "saveBatiment" :</b>' . '<br />' . '<br />';
  echo '- ' . saveBatiment("batimentTest", "fonctionTest", 0, 0) . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "saveFiliere" :</b>' . '<br />' . '<br />';
  echo '- ' . saveFiliere("filiereTest") . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "saveFormation" :</b>' . '<br />' . '<br />';
  echo '- ' . saveFormation("testFormation", "DUT", "ENSG-Géomatique", "ENSG", "Sport") . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "changeEcole" :</b>' . '<br />' . '<br />';
  echo '- ' . changeEcole(10, "ecoleTestModif", "testImageModif.jpg", "testadresse", "www.test-site-modif.com", "descriptionTestModif") . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "changeBatiment" :</b>' . '<br />' . '<br />';
  echo '- ' . changeBatiment(9, "batimentTestModif", "fonctionTestModif", 1, 1) . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "changeFiliere" :</b>' . '<br />' . '<br />';
  echo '- ' . changeFiliere(44, "filiereTestModif") . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "changeFormation" :</b>' . '<br />' . '<br />';
  echo '- ' . changeFormation(174, "formationTestModif", "niveauTestModif", "UPEM", "Copernic", "Agriculture, Bois") . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "deleteEcole" :</b>' . '<br />' . '<br />';
  echo '- ' . deleteEcole(10) . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "deleteBatiment" :</b>' . '<br />' . '<br />';
  echo '- ' . deleteBatiment(9) . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "deleteFiliere" :</b>' . '<br />' . '<br />';
  echo '- ' . deleteFiliere(44) . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "deleteFormation" :</b>' . '<br />' . '<br />';
  echo '- ' . deleteFormation(174) . '<br />' . '<br />'. '<br />';
}

 ?>
