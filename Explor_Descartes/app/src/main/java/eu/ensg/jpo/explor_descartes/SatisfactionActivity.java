package eu.ensg.jpo.explor_descartes;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import eu.ensg.jpo.explor_descartes.donneesAcces.SatisfactionDAO;
import eu.ensg.jpo.explor_descartes.donneesAcces.VisiteurDAO;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;

public class SatisfactionActivity extends AppCompatActivity {

    Button validationB;

    CheckBox app1;
    CheckBox app2;
    CheckBox app3;
    CheckBox app4;
    CheckBox app5;
    CheckBox app6;
    CheckBox app7;
    CheckBox app8;
    CheckBox app9;
    CheckBox app10;
    CheckBox app11;
    CheckBox app12;
    CheckBox app13;

    CheckBox jpo1;
    CheckBox jpo2;
    CheckBox jpo3;
    CheckBox jpo4;
    CheckBox jpo5;
    CheckBox jpo6;
    CheckBox jpo7;
    CheckBox jpo8;
    CheckBox jpo9;
    CheckBox jpo10;


    private void validation() {

        // Récupération des valeurs entrées :


        String App1=app1.getText().toString();
        String App2=app2.getText().toString();
        String App3=app3.getText().toString();
        String App4=app4.getText().toString();
        String App5=app5.getText().toString();
        String App6=app6.getText().toString();
        String App7=app7.getText().toString();
        String App8=app8.getText().toString();
        String App9=app9.getText().toString();
        String App10=app10.getText().toString();
        String App11=app11.getText().toString();
        String App12=app12.getText().toString();
        String App13=app13.getText().toString();

        String Jpo1=jpo1.getText().toString();
        String Jpo2=jpo2.getText().toString();
        String Jpo3=jpo3.getText().toString();
        String Jpo4=jpo4.getText().toString();
        String Jpo5=jpo5.getText().toString();
        String Jpo6=jpo6.getText().toString();
        String Jpo7=jpo7.getText().toString();
        String Jpo8=jpo8.getText().toString();
        String Jpo9=jpo9.getText().toString();
        String Jpo10=jpo10.getText().toString();




        ListeObjets.satisfaction.setQuestion_1(App1);
        ListeObjets.satisfaction.setQuestion_2(App2);
        ListeObjets.satisfaction.setQuestion_3(App3);
        ListeObjets.satisfaction.setQuestion_4(App4);
        ListeObjets.satisfaction.setQuestion_5(App5);
        ListeObjets.satisfaction.setQuestion_6(App6);
        ListeObjets.satisfaction.setQuestion_7(App7);
        ListeObjets.satisfaction.setQuestion_8(App8);
        ListeObjets.satisfaction.setQuestion_9(App9);
        ListeObjets.satisfaction.setQuestion_10(App10);
        ListeObjets.satisfaction.setQuestion_11(App11);
        ListeObjets.satisfaction.setQuestion_12(App12);
        ListeObjets.satisfaction.setQuestion_13(App13);

        ListeObjets.satisfaction.setQuestion_14(Jpo1);
        ListeObjets.satisfaction.setQuestion_15(Jpo2);
        ListeObjets.satisfaction.setQuestion_16(Jpo3);
        ListeObjets.satisfaction.setQuestion_17(Jpo4);
        ListeObjets.satisfaction.setQuestion_18(Jpo5);
        ListeObjets.satisfaction.setQuestion_19(Jpo6);
        ListeObjets.satisfaction.setQuestion_20(Jpo7);
        ListeObjets.satisfaction.setQuestion_21(Jpo8);
        ListeObjets.satisfaction.setQuestion_22(Jpo9);
        ListeObjets.satisfaction.setQuestion_23(Jpo10);

        // Enregistrement dans la base de données :
        String urlServeur = getString(R.string.url_serveur_ecoles);
        try {
            SatisfactionDAO satisfactionDAO = new SatisfactionDAO(urlServeur);
            SatisfactionDAO.saveSatisfaction( this, ListeObjets.satisfaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // I - Instanciation des objets Java représentant les composants graphiques

        validationB = (Button)findViewById(R.id.validationB);

        app1=(CheckBox)findViewById(R.id.app1);
        jpo2=(CheckBox)findViewById(R.id.app2);
        jpo3=(CheckBox)findViewById(R.id.app3);
        jpo4=(CheckBox)findViewById(R.id.app4);
        jpo5=(CheckBox)findViewById(R.id.app5);
        jpo6=(CheckBox)findViewById(R.id.app6);
        jpo7=(CheckBox)findViewById(R.id.app7);
        jpo8=(CheckBox)findViewById(R.id.app8);
        jpo9=(CheckBox)findViewById(R.id.app9);
        jpo10=(CheckBox)findViewById(R.id.app10);
        app11=(CheckBox)findViewById(R.id.app11);
        app12=(CheckBox)findViewById(R.id.app12);
        app13=(CheckBox)findViewById(R.id.app13);

        jpo1=(CheckBox)findViewById(R.id.jpo1);
        jpo2=(CheckBox)findViewById(R.id.jpo2);
        jpo3=(CheckBox)findViewById(R.id.jpo3);
        jpo4=(CheckBox)findViewById(R.id.jpo4);
        jpo5=(CheckBox)findViewById(R.id.jpo5);
        jpo6=(CheckBox)findViewById(R.id.jpo6);
        jpo7=(CheckBox)findViewById(R.id.jpo7);
        jpo8=(CheckBox)findViewById(R.id.jpo8);
        jpo9=(CheckBox)findViewById(R.id.jpo9);
        jpo10=(CheckBox)findViewById(R.id.jpo10);

        // II - Ajout des écouteurs d'événements aux composants graphiques représentés par des objets Java



        validationB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SatisfactionActivity.this.validation();
            }
        });





    }






















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
