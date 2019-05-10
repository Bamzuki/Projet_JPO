package eu.ensg.jpo.explor_descartes;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import eu.ensg.jpo.explor_descartes.donneesAcces.SatisfactionDAO;
import eu.ensg.jpo.explor_descartes.donnesObjet.Satisfaction;

public class SatisfactionActivity extends template {

    Button validationB;

    RadioGroup app1;
    RadioGroup app2;
    RadioGroup app3;
    RadioGroup app4;
    RadioGroup app5;
    RadioGroup app6;
    RadioGroup app7;
    RadioGroup app8;
    RadioGroup app9;
    RadioGroup app10;
    RadioGroup app11;
    RadioGroup app12;
    RadioGroup app13;

    RadioButton rep_app1;
    RadioButton rep_app2;
    RadioButton rep_app3;
    RadioButton rep_app4;
    RadioButton rep_app5;
    RadioButton rep_app6;
    RadioButton rep_app7;
    RadioButton rep_app8;
    RadioButton rep_app9;
    RadioButton rep_app10;
    RadioButton rep_app11;
    RadioButton rep_app12;
    RadioButton rep_app13;

    RadioGroup jpo1;
    RadioGroup jpo2;
    RadioGroup jpo3;
    RadioGroup jpo4;
    LinearLayout jpo5;
    LinearLayout jpo6;
    RadioGroup jpo7;
    RadioGroup jpo8;
    RadioGroup jpo9;
    RadioGroup jpo10;

    RadioButton rep_jpo1;
    RadioButton rep_jpo2;
    RadioButton rep_jpo3;
    RadioButton rep_jpo4;
    LinearLayout rep_jpo5;
    LinearLayout rep_jpo6;
    RadioButton rep_jpo7;
    RadioButton rep_jpo8;
    RadioButton rep_jpo9;
    RadioButton rep_jpo10;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentTemp();

        setContentView(R.layout.activity_satisfaction);

        // I - Instanciation des objets Java représentant les composants graphiques
        validationB = (Button) findViewById(R.id.validationB);

        app1 = (RadioGroup) findViewById(R.id.app1);
        app2 = (RadioGroup) findViewById(R.id.app2);
        app3 = (RadioGroup) findViewById(R.id.app3);
        app4 = (RadioGroup) findViewById(R.id.app4);
        app5 = (RadioGroup) findViewById(R.id.app5);
        app6 = (RadioGroup) findViewById(R.id.app6);
        app7 = (RadioGroup) findViewById(R.id.app7);
        app8 = (RadioGroup) findViewById(R.id.app8);
        app9 = (RadioGroup) findViewById(R.id.app9);
        app10 = (RadioGroup) findViewById(R.id.app10);
        app11 = (RadioGroup) findViewById(R.id.app11);
        app12 = (RadioGroup) findViewById(R.id.app12);
        app13 = (RadioGroup) findViewById(R.id.app13);

        jpo1 = (RadioGroup) findViewById(R.id.jpo1);
        jpo2 = (RadioGroup) findViewById(R.id.jpo2);
        jpo3 = (RadioGroup) findViewById(R.id.jpo3);
        jpo4 = (RadioGroup) findViewById(R.id.jpo4);
        jpo5 = (LinearLayout) findViewById(R.id.jpo5);
        jpo6 = (LinearLayout) findViewById(R.id.jpo6);
        jpo7 = (RadioGroup) findViewById(R.id.jpo7);
        jpo8 = (RadioGroup) findViewById(R.id.jpo8);
        jpo9 = (RadioGroup) findViewById(R.id.jpo9);
        jpo10 = (RadioGroup) findViewById(R.id.jpo10);

        // II - Ajout des écouteurs d'événements aux composants graphiques représentés par des objets Java
        validationB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SatisfactionActivity.this.validation();
                SatisfactionActivity.this.fermer();
            }
        });
    }

    public void validation() {

        // Creation d'un formulaire de réponse
        ArrayList<String> reponses = new ArrayList<String>();

        //Recupération des valeurs entrées
        int id_rep_app1 = app1.getCheckedRadioButtonId();
            if (id_rep_app1 > -1){
            rep_app1 = (RadioButton) findViewById(id_rep_app1);
            reponses.add(rep_app1.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_app2 = app2.getCheckedRadioButtonId();
        if (id_rep_app2 > -1) {
            rep_app2 = (RadioButton) findViewById(id_rep_app2);
            RadioButton autre = (RadioButton) findViewById(R.id.appQ2Autre);
            if (rep_app2.getText().toString() == autre.getText().toString()){
                EditText precisez = (EditText) findViewById(R.id.appQ2Precisez);
                reponses.add(precisez.getText().toString());
            }
            else{
                reponses.add(rep_app2.getText().toString());
            }
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_app3 = app3.getCheckedRadioButtonId();
        if (id_rep_app3 > -1) {
            rep_app3 = (RadioButton) findViewById(id_rep_app3);
            reponses.add(rep_app3.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_app4 = app4.getCheckedRadioButtonId();
        if (id_rep_app4 > -1) {
            rep_app4 = (RadioButton) findViewById(id_rep_app4);
            reponses.add(rep_app4.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_app5 = app5.getCheckedRadioButtonId();
        if (id_rep_app5 > -1) {
            rep_app5 = (RadioButton) findViewById(id_rep_app5);
            reponses.add(rep_app5.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_app6 = app6.getCheckedRadioButtonId();
        if (id_rep_app6 > -1) {
            rep_app6 = (RadioButton) findViewById(id_rep_app6);
            reponses.add(rep_app6.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_app7 = app7.getCheckedRadioButtonId();
        if (id_rep_app7 > -1) {
            rep_app7 = (RadioButton) findViewById(id_rep_app7);
            reponses.add(rep_app7.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_app8 = app8.getCheckedRadioButtonId();
        if (id_rep_app8 > -1) {
            rep_app8 = (RadioButton) findViewById(id_rep_app8);
            RadioButton autre = (RadioButton) findViewById(R.id.appQ8Choix1);
            if (rep_app8.getText().toString() == autre.getText().toString()){
                EditText precisez = (EditText) findViewById(R.id.suggestions);
                reponses.add(precisez.getText().toString());
            }
            else{
                reponses.add(rep_app2.getText().toString());
            }
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_app9 = app9.getCheckedRadioButtonId();
        if (id_rep_app9 > -1) {
            rep_app9 = (RadioButton) findViewById(id_rep_app9);
            reponses.add(rep_app9.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_app10 = app10.getCheckedRadioButtonId();
        if (id_rep_app10 > -1) {
            rep_app10 = (RadioButton) findViewById(id_rep_app10);
            reponses.add(rep_app10.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_app11 = app11.getCheckedRadioButtonId();
        if (id_rep_app11 > -1) {
            rep_app11 = (RadioButton) findViewById(id_rep_app11);
            reponses.add(rep_app11.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_app12 = app12.getCheckedRadioButtonId();
        if (id_rep_app12 > -1) {
            rep_app12 = (RadioButton) findViewById(id_rep_app12);
            reponses.add(rep_app12.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_app13 = app13.getCheckedRadioButtonId();
        if (id_rep_app13 > -1) {
            rep_app13 = (RadioButton) findViewById(id_rep_app13);
            reponses.add(rep_app13.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_jpo1 = jpo1.getCheckedRadioButtonId();
        if (id_rep_jpo1 > -1){
            rep_jpo1 = (RadioButton) findViewById(id_rep_jpo1);
            reponses.add(rep_jpo1.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_jpo2 = jpo2.getCheckedRadioButtonId();
        if (id_rep_jpo2 > -1){
            rep_jpo2 = (RadioButton) findViewById(id_rep_jpo2);
            reponses.add(rep_jpo2.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_jpo3 = jpo3.getCheckedRadioButtonId();
        if (id_rep_jpo3 > -1){
            rep_jpo3 = (RadioButton) findViewById(id_rep_jpo3);
            reponses.add(rep_jpo3.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_jpo4 = jpo4.getCheckedRadioButtonId();
        if (id_rep_jpo4 > -1){
            rep_jpo4 = (RadioButton) findViewById(id_rep_jpo4);
            reponses.add(rep_jpo4.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        //GESTION LISTVIEW
        ArrayList<CheckBox> jpo5_checkboxes = new ArrayList<CheckBox>();
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ5Choix1));
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ5Choix2));
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ5Choix3));
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ5Choix4));
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ5Choix5));
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ5Choix6));
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ5Choix7));
        String choixQ5 = checked(jpo5_checkboxes);
        if (choixQ5.isEmpty()){
            reponses.add("pas de réponse");
        }
        else {
            reponses.add(choixQ5);
        }

        ArrayList<CheckBox> jpo6_checkboxes = new ArrayList<CheckBox>();
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ6Choix1));
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ6Choix2));
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ6Choix3));
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ6Choix4));
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ6Choix5));
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ6Choix6));
        jpo5_checkboxes.add((CheckBox) findViewById(R.id.jpoQ6Choix7));
        String choixQ6 = checked(jpo6_checkboxes);
        if (choixQ6.isEmpty()){
            reponses.add("pas de réponse");
        }
        else {
            reponses.add(choixQ6);
        }

        int id_rep_jpo7 = jpo7.getCheckedRadioButtonId();
        if (id_rep_jpo7 > -1){
            rep_jpo7 = (RadioButton) findViewById(id_rep_jpo7);
            reponses.add(rep_jpo7.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_jpo8 = jpo8.getCheckedRadioButtonId();
        if (id_rep_jpo8 > -1){
            rep_jpo8 = (RadioButton) findViewById(id_rep_jpo8);
            reponses.add(rep_jpo8.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_jpo9 = jpo9.getCheckedRadioButtonId();
        if (id_rep_jpo9 > -1){
            rep_jpo9 = (RadioButton) findViewById(id_rep_jpo9);
            reponses.add(rep_jpo9.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        int id_rep_jpo10 = jpo10.getCheckedRadioButtonId();
        if (id_rep_jpo10 > -1){
            rep_jpo10 = (RadioButton) findViewById(id_rep_jpo10);
            reponses.add(rep_jpo10.getText().toString());
        }
        else {
            reponses.add("pas de réponse");
        }

        Satisfaction satisfaction = new Satisfaction(0, reponses.get(0), reponses.get(1), reponses.get(2), reponses.get(3), reponses.get(4), reponses.get(5), reponses.get(6), reponses.get(7),
                reponses.get(8), reponses.get(9), reponses.get(10), reponses.get(11), reponses.get(12), reponses.get(13), reponses.get(14), reponses.get(15), reponses.get(16), reponses.get(17),
                reponses.get(18), reponses.get(19), reponses.get(20), reponses.get(21), reponses.get(22));

        System.out.println(satisfaction);

        ListeObjets.satisfaction = satisfaction;


        // Enregistrement dans la base de données :
        try {
            SatisfactionDAO satisfactionDAO = new SatisfactionDAO(getString(R.string.url_serveur_ecoles) + "serveur.php/");
            satisfactionDAO.saveSatisfaction( this, ListeObjets.satisfaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fermer(){

        // Suppresion du questionnaire
        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollview);
        LinearLayout questionnaire = (LinearLayout) findViewById(R.id.questionnaire);
        questionnaire.removeAllViews();

        // Creation d'un nouvel LinearLayout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        //Ajout du texte
        TextView textView = new TextView(this);
        textView.setText(R.string.remerciment);
        linearLayout.addView(textView);
        scrollView.addView(linearLayout);

    }

    public String checked(ArrayList<CheckBox> checkBoxes){
        String choix = new String();
        for( CheckBox checkBox : checkBoxes){
            if (checkBox.isChecked()){
                choix += checkBox.getText().toString() + ",";
            }
        }
        if (choix.length() > 0) {
            choix = choix.substring(0, choix.length()-1 );
        }
        return choix;
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
        setLayout(R.layout.activity_satisfaction);
    }

    @Override
    public void setLayout(int newLayout) {
        super.setLayout(newLayout);
    }
}