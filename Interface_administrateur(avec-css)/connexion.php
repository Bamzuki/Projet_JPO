<?php


//Cette fonction va nous permettre de nous connecter au site à l'aide d'un pseudo et d'un mot de passe si on possède les droits d'administrateur


function connexion($nom,$mdp){
	$link = pg_connect("host=localhost port=5432 dbname=test-JPO user=postgres password=postgres");
	$requete = "SELECT * FROM utilisateurs WHERE nom LIKE '" . $nom . "'";
	$result = pg_query($link, $requete);

	while ($row = pg_fetch_row($result)) {
    $password = $row[2];
	$administrateur = $row[3];
    }
	if($password==$mdp){
		echo("Connexion réussie");
		if($administrateur=="t"){
			return("True");
		}else{
			return("False");
		};
	}else{
		echo("Connexion refusée");
	};


}


if (isset($_GET['request'])){
  $nom         = $_GET['nom'];
  $mdp       = $_GET['mdp'];
  echo connexion ($nom, $mdp);
}

?>
