package eu.ensg.jpo.explor_descartes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import eu.ensg.jpo.explor_descartes.donneesAcces.VisiteurDAO;

public class SignInActivity extends AppCompatActivity {

    /**
     * Classe gérant la connexion à lapplication
     */

    Button inscriptionB;
    Button connexionB;
    EditText mailET;
    EditText mdpET;
    Button forgotPassword;

    private void connexion(){

        /**
         * Fonction permettant de se connecter en récupérant le pseudo et le mot de passe
         */

        // Récupération des valeurs entrées :
        String mail = mailET.getText().toString();
        String mdp  = mdpET.getText().toString();

        try {
            mdp = SHA.encode(mdp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Enregistrement dans la base de données :
        String urlServeur = getString(R.string.url_serveur_ecoles);
        VisiteurDAO visiteurDAO = new VisiteurDAO(urlServeur);
        visiteurDAO.connexionBdd(this, mail, mdp);

    }

    public void openRegisterActivity() {

        /**
         * Fonction s'occupant d'ouvrir la page de connexion
         */

        // Create intent
        Intent intent = new Intent(this, RegisterActivity.class);

        // Start activity
        startActivity(intent);
    }

    public void openAccueilActivity() {

        /**
         * Fonction s'occupant d'ouvrir la page d'accueil
         */

        // Create intent
        Intent intent = new Intent(this, AccueilActivity.class);

        // Start activity
        startActivity(intent);
    }

    public void openForgotPassword(){

        /**
         * Fonction redirigeant l'utilisateur sur la page web qui lui envoie
         * un nouveau mot de passe s'il clique sur le lien "mot de passe oublié"
         */

        String url = getString(R.string.url_serveur) + "forgotPassword.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // I - Instanciation des objets Java représentant les composants graphiques

        inscriptionB = (Button) findViewById(R.id.inscriptionB);
        connexionB   = (Button) findViewById(R.id.connexionB);
        forgotPassword = (Button) findViewById(R.id.forgotPassword);

        mailET = (EditText) findViewById(R.id.mailET);
        mdpET  = (EditText) findViewById(R.id.mdpET);

        // II - Ajout des écouteurs d'événements aux composants graphiques représentés par des objets Java

        inscriptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInActivity.this.openRegisterActivity();
            }
        });
        connexionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInActivity.this.connexion();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInActivity.this.openForgotPassword();
            }
        });

    }

    public EditText getMdpET() {
        return mdpET;
    }

}

