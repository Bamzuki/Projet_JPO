<?php

// Functions :

function getListeNomObjets($nomTable){
  //Cette fonction renvoie une liste des noms des objets de la table rentrée en argument
  $link = pg_connect("host=localhost port=5433 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT nom FROM " . $nomTable;
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
  $link = pg_connect("host=localhost port=5433 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT id, nom, site, description FROM ecoles";
  $result = pg_query($link, $requete);

  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $ecole= '{"id":' . $row[0] . ', "nom":"' . $row[1] . '", "site":"' . $row[2] . '", "description":"' . $row[3] . '"}';
      $response = $response . $ecole . ', ';
    }
  }
  $response = substr($response, 0, -2) . ']';
  return $response;
}

function getListeBatiments(){
  //Cette fonction renvoie la liste des bâtiments
  $link = pg_connect("host=localhost port=5433 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT id, nom, fonction, lat, lng FROM batiments";
  $result = pg_query($link, $requete);

  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $batiment = '{"id":' . $row[0] . ', "nom":"' . $row[1] . '", "fonction":"' . $row[2] . '", "lat":' . $row[3] . ', "lng":' . $row[4] . '}';
      $response = $response . $batiment . ', ';
    }
  }
  $response = substr($response, 0, -2) . ']';
  return $response;
}

function getListeFormations(){
  //Cette fonction renvoie la liste des fomrmations
  $link = pg_connect("host=localhost port=5433 dbname=test-JPO user=postgres password=postgres");
  $requete = "SELECT f.id, f.nom, f.niveau, ecoles.nom, batiments.nom, filieres.nom FROM formations AS f LEFT JOIN ecoles ON f.id_ecole = ecoles.id LEFT JOIN batiments ON f.id_batiment = batiments.id LEFT JOIN filieres ON f.id_filiere = filieres.id";
  $result = pg_query($link, $requete);

  if ($result) {
    $response = '[';
    while ($row = pg_fetch_row($result)) {
      $formation = '{"id":' . $row[0] . ', "nom":"' . $row[1] . '", "niveau":"' . $row[2] . '", "ecole":"' . $row[3] . '", "batiment":"' . $row[4] . '", "filiere":"' . $row[3] . '"}';
      $response = $response . $formation . ', ';
    }
  }
  $response = substr($response, 0, -2) . ']';
  return $response;
}



// Requests :

if (isset($_GET['request']) && $_GET['request'] == "listenomobjets" && isset($_GET['nomtable'])){
  $nomTable = $_GET['nomtable'];
  echo getListeNomObjets($nomTable);
}

if (isset($_GET['request']) && $_GET['request'] == "testunitaire"){

  echo '<b>Test de "getListeNomObjets" :</b>' . '<br />' . '<br />';
  echo '- Ecoles :</b>'."<br />";
  echo '- ' . getListeNomObjets("ecoles") . '<br />' . '<br />' ;
  echo '- Filières :</b>'."<br />";
  echo '- ' . getListeNomObjets("filieres") . '<br />' . '<br />';
  echo '- Bâtiments :</b>'."<br />";
  echo '- ' . getListeNomObjets("batiments") . '<br />' . '<br />' . '<br />';

  echo '<b>Test de "getListeEcoles" :</b>' . '<br />' . '<br />';
  echo '- ' . getListeEcoles() . '<br />' . '<br />'. '<br />';

  echo '<b>Test de "getListeBatiments" :</b>' . '<br />' . '<br />';
  echo '- ' . getListeBatiments() . '<br />' . '<br />'. '<br />';

  echo '<b>Test de "getListeFormations" :</b>' . '<br />' . '<br />';
  echo '- ' . getListeFormations() . '<br />' . '<br />'. '<br />';


}


 ?>
