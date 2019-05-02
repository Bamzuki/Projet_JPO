package eu.ensg.jpo.explor_descartes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;

public class SatisfactionActivity extends template {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentTemp();

    }

    @Override
    protected void contentTemp() {

    }

    @Override
    protected void llayout() { setLayout(R.layout.activity_satisfaction);}
}
