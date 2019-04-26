package eu.ensg.jpo.explor_descartes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import eu.ensg.jpo.explor_descartes.donneesAcces.BatimentDAO;
import eu.ensg.jpo.explor_descartes.donneesAcces.EcoleDAO;


public class MainActivity extends AppCompatActivity {

    ImageButton connexionB;
    Button inscriptionB;
    Button ignorerB;

    private void openSignInActivity() {

        // Create intent
        Intent intent = new Intent(this, SignInActivity.class);

        // Start activity
        startActivity(intent);
    }

    private void openRegisterActivity() {

        // Create intent
        Intent intent = new Intent(this, RegisterActivity.class);

        // Start activity
        startActivity(intent);
    }

    private void openAccueilActivity() {

        // Create intent
        Intent intent = new Intent(this, AccueilActivity.class);

        // Start activity
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 0 - Chargement des objets depuis la base de données
        chargerListeObjets();

        // I - Instanciation des objets Java représentant les composants graphiques

        ignorerB     = findViewById(R.id.ignorerB);
        connexionB   = findViewById(R.id.connexionB);
        inscriptionB = findViewById(R.id.inscriptionB);

        // II - Ajout des écouteurs d'événements aux composants graphiques représentés par des objets Java

        connexionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.openSignInActivity();
            }
        });

        inscriptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.openRegisterActivity();
            }
        });

        ignorerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.openAccueilActivity();
            }
        });

    }

    public void chargerListeObjets(){
        String urlServeur = getString(R.string.url_serveur_ecoles);
        // Chargement de la liste des batiments :
        BatimentDAO batimentDAO = new BatimentDAO(urlServeur);
        batimentDAO.chargerBatiment();
        // Chargement de la liste des écoles :
        EcoleDAO ecoleDAO = new EcoleDAO(urlServeur);
        ecoleDAO.chargerEcole();

    }
}