package eu.ensg.jpo.explor_descartes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import eu.ensg.jpo.explor_descartes.donneesAcces.VisiteurDAO;
import eu.ensg.jpo.explor_descartes.donnesObjet.Visiteur;

public class ModifierPerso extends AppCompatActivity {

    Button validationB;
    EditText prenomET;
    EditText nomET;
    EditText pseudoET;
    EditText mailET;


    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public void openSignInActivity() {

        // Create intent
        Intent intent = new Intent(this, SignInActivity.class);

        // Start activity
        startActivity(intent);
    }

    public void openAccueilActivity() {

        // Create intent
        Intent intent = new Intent(this, AccueilActivity.class);
        // Start activity
        startActivity(intent);
    }

    private void modification() {

        /**
         * Fonction modifiant les données personnelle d'un compte
         */

        // Récupération des valeurs entrées :
        String prenom = prenomET.getText().toString();
        String nom = nomET.getText().toString();
        String pseudo = pseudoET.getText().toString();
        String mail = mailET.getText().toString().toLowerCase();

        //A completer avec la vérification du pseudo (il ne doit pas y avoir deux fois le même)
        ListeObjets.visiteur.setPrenom(prenom);
        ListeObjets.visiteur.setNom(nom);
        ListeObjets.visiteur.setPseudo(pseudo);
        ListeObjets.visiteur.setEmail(mail);

        // Enregistrement dans la base de données :
        String urlServeur = getString(R.string.url_serveur_ecoles);

        try {
            // Modification dans les données internes de l'application :
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("pseudo");
            editor.putString("pseudo", pseudo);
            editor.apply();

            // Modification dans la BDD :
            VisiteurDAO visiteurDAO = new VisiteurDAO(urlServeur);
            visiteurDAO.updatePerso(this ,ListeObjets.visiteur);

            // Renvoie sur la page Profil :
            Toast.makeText(this, "Vos informations personnelles ont bien été modifiées.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, GestionCompte.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_perso);


        // I - Instanciation des objets Java représentant les composants graphiques

        validationB = (Button)findViewById(R.id.validationB);

        prenomET      = (EditText) findViewById(R.id.prenomET);
        nomET         = (EditText) findViewById(R.id.nomET);
        pseudoET      = (EditText) findViewById(R.id.pseudoET);
        mailET        = (EditText) findViewById(R.id.mailET);


        // II - Préremplissage des champs :

        prenomET.setText(ListeObjets.visiteur.getPrenom());
        nomET.setText(ListeObjets.visiteur.getNom());
        pseudoET.setText(ListeObjets.visiteur.getPseudo());
        mailET.setText(ListeObjets.visiteur.getEmail());

        // III - Ajout des écouteurs d'événements aux composants graphiques représentés par des objets Java

        validationB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModifierPerso.this.modification();
            }
        });
    }

    public EditText getPseudoET() {
        return pseudoET;
    }

    public EditText getMailET() {
        return mailET;
    }
}
