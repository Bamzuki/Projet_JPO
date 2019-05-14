package eu.ensg.jpo.explor_descartes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
;
import java.text.ParseException;

import eu.ensg.jpo.explor_descartes.donneesAcces.VisiteurDAO;

public class GestionCompte extends template {

    /**
     * page de gestion d'un compte
     */

    private Button ModifierPerso;
    private Button ModifierMdp;
    private Button Deconnexion;
    private Button Suppression;

    private void openModifierPerso() {

        /**
         * redirige vers la page de modification des données personnelles
         */

        if (ListeObjets.visiteur != null){
            // Create intent
            Intent intent = new Intent(this, ModifierPerso.class);
            // Start activity
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Vous n'est pas connecté !" , Toast.LENGTH_SHORT).show();
        }
    }

    private void openModifierMdp() {

        /**
         * redirige vers la page de modification du mot de passe
         */

        if (ListeObjets.visiteur != null){
            // Create intent
            Intent intent = new Intent(this, ModifierMdp.class);
            // Start activity
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Vous n'est pas connecté !" , Toast.LENGTH_SHORT).show();
        }
    }

    private void openSuppression() {

        /**
         * redirige vers la page de modification de suppression d'un compte
         */


        if (ListeObjets.visiteur != null){

            try {
                // Suppression dans la base de données :
                VisiteurDAO visiteurDAO = new VisiteurDAO(getString(R.string.url_serveur) + "serveur.php/");
                visiteurDAO.delete(ListeObjets.visiteur);
                // Déconnexon :
                openDeconnexion();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else {
            Toast.makeText(this, "Vous n'est pas connecté !" , Toast.LENGTH_SHORT).show();
        }
    }

    private void openDeconnexion() {

        /**
         * fonction permettant de se déconnecter
         */


        if (ListeObjets.visiteur != null){

            Toast.makeText(this, "Au revoir " + ListeObjets.visiteur.getPseudo() , Toast.LENGTH_SHORT).show();

            // Suppression des données stockées sur l'application
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("pseudo");
            editor.remove("mdp");
            editor.apply();

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
