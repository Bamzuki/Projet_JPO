package eu.ensg.jpo.explor_descartes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    Button connexionB;

    private void openSignInActivity() {

        // Create intent
        Intent intent = new Intent(this, SignInActivity.class);

        // Start activity
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // I - Instanciation des objets Java représentant les composants graphiques

        connexionB   = (Button)findViewById(R.id.connexionB);

        // II - Ajout des écouteurs d'événements aux composants graphiques représentés par des objets Java

        connexionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.this.openSignInActivity();
            }
        });
    }
}
