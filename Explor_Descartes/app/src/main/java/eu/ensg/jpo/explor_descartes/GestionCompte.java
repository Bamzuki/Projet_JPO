package eu.ensg.jpo.explor_descartes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Modifier;

import eu.ensg.jpo.explor_descartes.donneesAcces.VisiteurDAO;

public class GestionCompte extends AppCompatActivity {

    Button ModifierPerso;
    Button ModifierMdp;
    Button Deconnexion;
    Button Suppression;

    private void openModifierPerso() {
        // Create intent
        Intent intent = new Intent(this, ModifierPerso.class);

        if (ListeObjets.visiteur != null){
            // Start activity
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Vous n'est pas connecté !" , Toast.LENGTH_SHORT).show();
        }
    }

    private void openModifierMdp() {
        // Create intent
        Intent intent = new Intent(this, ModifierMdp.class);

        if (ListeObjets.visiteur != null){
            // Start activity
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Vous n'est pas connecté !" , Toast.LENGTH_SHORT).show();
        }
    }

    private void openSuppression() {
        if (ListeObjets.visiteur != null){
            // Suppression dans la base de données :
            String urlServeur = getString(R.string.url_serveur_ecoles);
            try {
                VisiteurDAO visiteurDAO = new VisiteurDAO(urlServeur);
                visiteurDAO.suppression( ListeObjets.visiteur);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(this, "Vous n'est pas connecté !" , Toast.LENGTH_SHORT).show();
        }
    }

    private void openDeconnexion() {
        // Create intent
        Intent intent = new Intent(this, MainActivity.class);

        if (ListeObjets.visiteur != null){
            //Remise à zéro de la valeur
            ListeObjets.visiteur = null;

            // Start activity
            startActivity(intent);

            Toast.makeText(this, "Au revoir " + ListeObjets.visiteur.getPseudo() , Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "Vous n'est pas connecté !" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_compte);

    // I - Instanciation des objets Java représentant les composants graphiques
    ModifierPerso = (Button) findViewById(R.id.ModifierPersoB);
    ModifierMdp   = (Button) findViewById(R.id.ModifierMdpB);
    Deconnexion = (Button) findViewById(R.id.Deconnexion);
    Suppression = (Button) findViewById(R.id.Suppression);

    // II - Ajout des écouteurs d'événements aux composants graphiques représentés par des objets Java

        ModifierPerso.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GestionCompte.this.openModifierPerso();
        }
    });
        ModifierMdp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GestionCompte.this.openModifierMdp();
        }
    });

        Deconnexion.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GestionCompte.this.openDeconnexion();
        }
    });
        Suppression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GestionCompte.this.openSuppression();
            }
        });

    }
}
