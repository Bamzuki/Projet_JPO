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

public class ModifierMdp extends AppCompatActivity {


    Button validationB;
    EditText mdpET;
    EditText mdp_confirmET;


    private void validation() {
        // Récupération des valeurs entrées :

        String mdp = mdpET.getText().toString();
        String mdp_confirm = mdp_confirmET.getText().toString();

        // Vérification des valeurs entrées :
        if (mdp.equals("")){
            Toast.makeText(this, "Le champ 'Mot de passe' est obligatoire.", Toast.LENGTH_LONG).show();
            return;
        }
        if (!mdp.equals(mdp_confirm)){
            Toast.makeText(this, "Les mots de passe ne correspondent pas.", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            // Encodage du mot de passe :
            String encodedMdp = SHA.encode(mdp);

            // Modification de l'objet Visiteur :
            ListeObjets.visiteur.setMdp(encodedMdp);

            // Modification dans les données internes de l'application :
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("mdp");
            editor.putString("mdp", encodedMdp);
            editor.apply();

            // Modification dans la BDD :
            VisiteurDAO visiteurDAO = new VisiteurDAO(getString(R.string.url_serveur) + "serveur.php/");
            visiteurDAO.update(ListeObjets.visiteur);

            // Renvoie sur la page Profil :
            Toast.makeText(this, "Votre mot de passe a bien été modifié.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, GestionCompte.class);
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_mdp);

        // I - Instanciation des objets Java représentant les composants graphiques

        validationB = (Button)findViewById(R.id.validationB);

        mdpET         = (EditText) findViewById(R.id.mdpET);
        mdp_confirmET = (EditText) findViewById(R.id.mdp_confirmET);

        // II - Ajout des écouteurs d'événements aux composants graphiques représentés par des objets Java

        validationB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModifierMdp.this.validation();
            }
        });
    }


}
