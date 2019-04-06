<?php
// I - Fonctions :
// I.1 Fonctions GET ID :
function getIdEcole($nom){
  //Cette fonction renvoie l'id d'une école depuis son nom
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT id FROM ecoles WHERE nom LIKE '" . $nom . "'";
  $result = pg_query($link, $requete);
  while ($row = pg_fetch_row($result)) {
    $id_ecole = $row[0];
    return $id_ecole;
  }
}
function getIdBatiment($nom){
  //Cette fonction renvoie l'id d'un bâtiment depuis son nom
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT id FROM batiments WHERE nom LIKE '" . $nom . "'";
  $result = pg_query($link, $requete);
  while ($row = pg_fetch_row($result)) {
    $id_batiment = $row[0];
    return $id_batiment;
  }
}
function getIdFiliere($nom){
  //Cette fonction renvoie l'id d'une filière depuis son nom
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT id FROM filieres WHERE nom LIKE '" . $nom . "'";
  $result = pg_query($link, $requete);
  while ($row = pg_fetch_row($result)) {
    $id_filiere = $row[0];
    return $id_filiere;
  }
}
// I.2 Fonctions GET :
function getListeNomObjets($nomTable){
  //Cette fonction renvoie une liste des noms des objets de la table rentrée en argument
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT id, nom, site, description FROM ecoles ORDER BY id";
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT id, nom, fonction, lat, lng FROM batiments WHERE fonction LIKE '" . $filtreFonction . "' ORDER BY id";
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
// I.3 Fonctions SAVE :
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "INSERT INTO formations (nom, niveau, id_ecole, id_batiment, id_filiere) VALUES ('" . $nom . "', '" . $niveau . "', " . $id_ecole . ", " . $id_batiment . ", " . $id_filiere . ")";
  $result = pg_query($link, $requete);
  if ($result){
    return "Sauvegarde réussie !";
  }else{
    return "La sauvegarde a échouée";
  }
}
// I.4 Fonctions CHANGE :
function changeEcole ($id, $nom, $site, $description){
  //Cette fonction modifie une école déjà existante dans la base de données
  $nom = str_replace("'", "''", $nom);
  $description = str_replace("'", "''", $description);
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "UPDATE ecoles
              SET nom = '" . $nom . "', site = '" . $site . "', description = '" . $description . "'
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
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
// I.5 Fonctions DELETE :
function deleteEcole ($id){
  //Cette fonction supprime une école dans la base de données
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "DELETE FROM batiments
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Suppression réussie !";
  }else{
    return "La suppression a échouée";
  }
}
function deleteFiliere ($id){
  //Cette fonction supprime une filiere dans la base de données
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
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
  $link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
  $requete = "DELETE FROM formations
              WHERE id=" . $id;
  $result = pg_query($link, $requete);
  if ($result){
    return "Suppression réussie !";
  }else{
    return "La suppression a échouée";
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
  $nom         = $_GET['nom'];
  $site        = $_GET['site'];
  $description = $_GET['description'];
  echo saveEcole ($nom, $site, $description);
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
// II.3 Requêtes CHANGE :
if (isset($_GET['request']) && $_GET['request'] == "changeEcole"){
  $id          = $_GET['id'];
  $nom         = $_GET['nom'];
  $site        = $_GET['site'];
  $description = $_GET['description'];
  echo changeEcole ($id, $nom, $site, $description);
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
  $ecole    = $_GET['ecole'];
  $batiment = $_GET['batiment'];
  $filiere  = $_GET['filiere'];
  echo changeFormation ($id, $nom, $niveau, $ecole, $batiment, $filiere);
}
// II.4 Requêtes DELETE :
if (isset($_GET['request']) && $_GET['request'] == "deleteEcole"){
  $id  = $_GET['id'];
  echo changeEcole ($id);
}
if (isset($_GET['request']) && $_GET['request'] == "deleteBatiment"){
  $id  = $_GET['id'];
  echo changeBatiment ($id);
}
if (isset($_GET['request']) && $_GET['request'] == "deleteFiliere"){
  $id  = $_GET['id'];
  echo changeFiliere ($id);
}
if (isset($_GET['request']) && $_GET['request'] == "deleteFormation"){
  $id  = $_GET['id'];
  echo changeFormation ($id);
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
  echo '- ' . saveEcole("ecoleTest", "www.test-site.com", "descriptionTest") . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "saveBatiment" :</b>' . '<br />' . '<br />';
  echo '- ' . saveBatiment("batimentTest", "fonctionTest", 0, 0) . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "saveFiliere" :</b>' . '<br />' . '<br />';
  echo '- ' . saveFiliere("filiereTest") . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "saveFormation" :</b>' . '<br />' . '<br />';
  echo '- ' . saveFormation("testFormation", "DUT", "ENSG-Géomatique", "ENSG", "Sport") . '<br />' . '<br />'. '<br />';
  echo '<b>Test de "changeEcole" :</b>' . '<br />' . '<br />';
  echo '- ' . changeEcole(10, "ecoleTestModif", "www.test-site-modif.com", "descriptionTestModif") . '<br />' . '<br />'. '<br />';
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