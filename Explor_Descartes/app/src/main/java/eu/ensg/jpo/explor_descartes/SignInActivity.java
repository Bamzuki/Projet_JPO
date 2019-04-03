package eu.ensg.jpo.explor_descartes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInActivity extends AppCompatActivity {

    Button inscriptionB;

    private void openRegisterActivity() {

        // Create intent
        Intent intent = new Intent(this, RegisterActivity.class);

        // Start activity
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // I - Instanciation des objets Java représentant les composants graphiques

        inscriptionB = (Button)findViewById(R.id.inscriptionB);

        // II - Ajout des écouteurs d'événements aux composants graphiques représentés par des objets Java

        inscriptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInActivity.this.openRegisterActivity();
            }
        });
    }
}
