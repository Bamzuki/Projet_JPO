package eu.ensg.jpo.explor_descartes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    Button connexionB;
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
        Intent intent = new Intent(this, NavigationActivity.class);

        // Start activity
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // I - Instanciation des objets Java représentant les composants graphiques

        connexionB   = (Button)findViewById(R.id.connexionB);
        inscriptionB = (Button)findViewById(R.id.inscriptionB);
        ignorerB     = (Button)findViewById(R.id.ignorerB);

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
}
