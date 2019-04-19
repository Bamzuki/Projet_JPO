package eu.ensg.jpo.explor_descartes;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;

public class EcoleActivity extends AppCompatActivity {

    public Ecole ecole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecole);
        this.ecole = ListeObjets.ecole_selectionnee;

        // 0 - Création de la charte graphique de la page


        // Création d'un ConstraintLayout
        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        //Attributs de la ConstraintLayout
        constraintLayout.setBackgroundColor(Color.WHITE);

        // Création d'une ImageView pour la photo de l'ecole
        ImageView photo = new ImageView(this);
        photo.setImageResource(R.drawable.facebook);

        // Création d'un TextView pour le nom de l'école
        TextView nom_ecole = new TextView(this);
        nom_ecole.setText(this.ecole.getNom());
        System.out.print("Id de l'école :" + nom_ecole);

        // Paramètres spécifique au placement
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, //Width
                ConstraintLayout.LayoutParams.WRAP_CONTENT //Height
        );
        nom_ecole.setLayoutParams(params);

        // Attributs
        nom_ecole.setTextColor(Color.BLACK);                                    // Couleur du texte
        nom_ecole.setTextSize(14);                                              // Taille de la police
        nom_ecole.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);                 // Alignement
        nom_ecole.setPadding(15, 15,15,15);              // Marges intérieures
        nom_ecole.setGravity(Gravity.CENTER);                                   // Gravité

        // Insertion dans la page
        constraintLayout.addView(nom_ecole);

        // Création d'un LinearLayout vertical pour inserer les informations relative de l'école
        LinearLayout info = new LinearLayout(this);
        info.setOrientation(LinearLayout.VERTICAL);


        // Création d'un LinearLayout horizontal pour inserer l'adresse de l'école
        LinearLayout adresse_layout = new LinearLayout(this);
        adresse_layout.setOrientation(LinearLayout.HORIZONTAL);

        ImageView pointeur = new ImageView(this);
        pointeur.setImageResource(R.drawable.pointeur);

        adresse_layout.addView(pointeur);

        TextView adresse = new TextView(this);
        nom_ecole.setText("Adresse de l'école");

        adresse_layout.addView(adresse);

        info.addView(adresse_layout);

        // Création d'un LinearLayout pour inserer le site web de l'école
        LinearLayout site_layout = new LinearLayout(this);
        site_layout.setOrientation(LinearLayout.HORIZONTAL);

        ImageView web = new ImageView(this);
        web.setImageResource(R.drawable.site);

        site_layout.addView(web);

        TextView site = new TextView(this);
        site.setText(this.ecole.getSite().toString());
        Linkify.addLinks(site, Linkify.WEB_URLS);

        info.addView(site_layout);

        setContentView(constraintLayout);
    }



}
