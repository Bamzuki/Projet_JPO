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

public class ModifierMdp extends AppCompatActivity {


    Button validationB;
    EditText mdpET;
    EditText mdp_confirmET;



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



        ListeObjets.visiteur.setMdp(mdp);

        // Enregistrement dans la base de données :
        String urlServeur = getString(R.string.url_serveur_ecoles);
        try {
            VisiteurDAO visiteurDAO = new VisiteurDAO(urlServeur);
            visiteurDAO.updateMdp( this,ListeObjets.visiteur);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


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
