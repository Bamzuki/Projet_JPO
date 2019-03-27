<?php

// I - Fonctions :

function getListeNomObjets($nomTable){
  //Cette fonction renvoie une liste des noms des objets de la table rentrée en argument
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  if ($nomTable == "niveaux"){
    $requete = "SELECT DISTINCT niveau FROM formations";
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT id, nom, site, description FROM ecoles";
  $result = pg_query($link, $requete);

  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $ecole = '{"id":' . $row[0] . ', "nom":"' . $row[1] . '", "site":"' . $row[2] . '", "description":"' . $row[3] . '"}';
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT id, nom FROM filieres";
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT id, nom, fonction, lat, lng FROM batiments WHERE fonction LIKE '" . $filtreFonction . "'";
  $result = pg_query($link, $requete);

  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $batiment = '{"id":' . $row[0] . ', "nom":"' . $row[1] . '", "fonction":"' . $row[2] . '", "lat":' . $row[3] . ', "lng":' . $row[4] . '}';
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT f.id, f.nom, f.niveau, ecoles.nom, batiments.nom, filieres.nom
  FROM formations AS f LEFT JOIN ecoles ON f.id_ecole = ecoles.id
  LEFT JOIN batiments ON f.id_batiment = batiments.id
  LEFT JOIN filieres ON f.id_filiere = filieres.id
  WHERE f.niveau LIKE '" . $filtreNiveau . "' AND ecoles.nom LIKE '" . $filtreEcole . "' AND batiments.nom LIKE '" . $filtreBatiment . "' AND filieres.nom LIKE '" . $filtreFiliere . "'";
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

function saveEcole ($nom, $site, $description){
  //Cette fonction enregistre une nouvelle école dans la base de données
  $nom = str_replace("'", "''", $nom);
  $description = str_replace("'", "''", $description);
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "INSERT INTO ecoles (nom, site, description) VALUES ('" . $nom . "', '" . $site . "', '" . $description . "')";
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "INSERT INTO filieres (nom) VALUES ('" . $nom . "')";
  $result = pg_query($link, $requete);

  if ($result){
    return "Sauvegarde réussie !";
  }else{
    return "La sauvegarde a échouée";
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

// II.2 Requêtes SAVE :
if (isset($_GET['request']) && $_GET['request'] == "saveEcole"){
  if (isset($_GET['nom'])) {
    $nom = $_GET['nom'];
  }
  else{
    $nom = "";
  }
  if (isset($_GET['site'])) {
    $site = $_GET['site'];
  }
  else{
    $site = "";
  }
  if (isset($_GET['description'])) {
    $description = $_GET['description'];
  }
  else{
    $description = "";
  }
  echo saveEcole ($nom, $site, $description);
}

if (isset($_GET['request']) && $_GET['request'] == "saveBatiment"){
  if (isset($_GET['nom'])) {
    $nom = $_GET['nom'];
  }
  else{
    $nom = "";
  }
  if (isset($_GET['fonction'])) {
    $fonction = $_GET['fonction'];
  }
  else{
    $fonction = "";
  }
  if (isset($_GET['lat'])) {
    $lat = $_GET['lat'];
  }
  else{
    $lat = 0;
  }
  if (isset($_GET['lng'])) {
    $lng = $_GET['lng'];
  }
  else{
    $lng = 0;
  }
  echo saveBatiment ($nom, $fonction, $lat, $lng);
}

if (isset($_GET['request']) && $_GET['request'] == "saveFiliere"){
  if (isset($_GET['nom'])) {
    $nom = $_GET['nom'];
  }
  else{
    $nom = "";
  }
  echo saveFiliere ($nom);
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
  echo '- ' . getListeNomObjets("niveaux") . '<br />' . '<br />' . '<br />';

  echo '<b>Test de "getListeEcoles" :</b>' . '<br />' . '<br />';
  echo '- ' . getListeEcoles() . '<br />' . '<br />'. '<br />';

  echo '<b>Test de "getListeBatiments" :</b>' . '<br />' . '<br />';
  echo '- ' . getListeBatiments("%") . '<br />' . '<br />'. '<br />';

  echo '<b>Test de "getListeFilieres" :</b>' . '<br />' . '<br />';
  echo '- ' . getListeFilieres() . '<br />' . '<br />'. '<br />';

  echo '<b>Test de "getListeFormations" :</b>' . '<br />' . '<br />';
  echo '- ' . getListeFormations("%", "%", "%", "%") . '<br />' . '<br />'. '<br />';

  echo '<b>Test de "saveEcole" :</b>' . '<br />' . '<br />';
  echo '- ' . saveEcole("ecoleTest", "www.test-site.com", "descriptionTest") . '<br />' . '<br />'. '<br />';

  echo '<b>Test de "saveBatiment" :</b>' . '<br />' . '<br />';
  echo '- ' . saveBatiment("batimentTest", "fonctionTest", 0, 0) . '<br />' . '<br />'. '<br />';

  echo '<b>Test de "saveFiliere" :</b>' . '<br />' . '<br />';
  echo '- ' . saveFiliere("filiereTest") . '<br />' . '<br />'. '<br />';
}

 ?>
