package eu.ensg.jpo.explor_descartes;

import android.content.Intent;
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
            VisiteurDAO visiteurDAO = new VisiteurDAO(urlServeur);
            visiteurDAO.updatePerso(this ,ListeObjets.visiteur);
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


        // II - Ajout des écouteurs d'événements aux composants graphiques représentés par des objets Java



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
