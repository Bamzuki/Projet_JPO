<?php



function connexion($nom,$pseudo){
	$link = pg_connect("host=localhost port=5432 dbname=tutilisateurs user=postgres password=postgres");
	$requete = "SELECT * FROM utilisateurs WHERE nom LIKE '" . $nom . "'";
	$result = pg_query($link, $requete);
	if($result.pseudo==$pseudo){
		echo("Connexion réussie");
		if($result.admin=="True"){
			return("True");
		};
		else(){
			return("False");
		};
	};
	else(){
		echo("Connexion refusée");
	};
	
	
}




if (isset($_GET['request']) && $_GET['request'] == "connexion"){
  $nom         = $_GET['nom'];
  $pseudo        = $_GET['pseudo'];
  echo connexion ($nom, $pseudo);
}









?>