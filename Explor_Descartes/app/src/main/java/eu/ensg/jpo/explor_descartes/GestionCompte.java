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

import java.lang.reflect.Modifier;
import java.text.ParseException;

import eu.ensg.jpo.explor_descartes.donneesAcces.VisiteurDAO;

public class GestionCompte extends template {

    private Button ModifierPerso;
    private Button ModifierMdp;
    private Button Deconnexion;
    private Button Suppression;

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
            String urlServeur = getString(R.string.url_serveur) + "serveur.php/";
            try {
                VisiteurDAO visiteurDAO = new VisiteurDAO(urlServeur);
                visiteurDAO.suppression(ListeObjets.visiteur);
            } catch (Exception e) {
                e.printStackTrace();
            }
            openDeconnexion();
        }
        else {
            Toast.makeText(this, "Vous n'est pas connecté !" , Toast.LENGTH_SHORT).show();
        }
    }

    private void openDeconnexion() {

        if (ListeObjets.visiteur != null){

            Toast.makeText(this, "Au revoir " + ListeObjets.visiteur.getPseudo() , Toast.LENGTH_SHORT).show();

            // Suppression des données stockées sur l'application
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("pseudo");
            editor.remove("mdp");
            editor.commit();

            //Remise à zéro de la valeur visiteur
            ListeObjets.visiteur = null;

            // Create intent
            Intent intent = new Intent(this, MainActivity.class);

            // Start activity
            startActivity(intent);

        }
        else {
            Toast.makeText(this, "Vous n'est pas connecté !" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentTemp();

        // I - Instanciation des objets Java représentant les composants graphiques
        Button ModifierPerso = (Button) findViewById(R.id.ModifierPersoB);
        Button ModifierMdp   = (Button) findViewById(R.id.ModifierMdpB);
        Button Deconnexion = (Button) findViewById(R.id.Deconnexion);
        Button Suppression = (Button) findViewById(R.id.Suppression);

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

    @Override
    public void getData() throws ParseException {
        super.getData();
    }

    @Override
    protected void contentTemp() {
        super.contentTemp();
    }

    @Override
    protected void llayout() {
        setLayout(R.layout.activity_gestion_compte);
    }

    @Override
    public void setLayout(int newLayout) {
        super.setLayout(newLayout);
    }
}
