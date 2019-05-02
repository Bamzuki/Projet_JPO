package eu.ensg.jpo.explor_descartes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.GridView.GridViewAdapter;
import eu.ensg.jpo.explor_descartes.GridView.ImageEcole;
import eu.ensg.jpo.explor_descartes.donneesAcces.PictureDAO;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;


public class PlanningActivity extends template implements Serializable {
    private String text;
    private TextView tes;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        contentTemp();

        GridView gridView = (GridView) findViewById(R.id.main_grid);
        GridViewAdapter gridAdapter = new GridViewAdapter(this, this, R.layout.grid_item_layout, getListImageEcoles());
        gridView.setAdapter(gridAdapter);
        //Ajout de l'event listener :
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageEcole item = (ImageEcole) parent.getItemAtPosition(position);
                //Create intent
                Intent intent = new Intent(PlanningActivity.this, EcoleActivity.class);
                ListeObjets.ecoleSelectionnee = item.getEcole();

                //Start details activity
                startActivity(intent);
            }
        });

    }

    private ArrayList<ImageEcole> getListImageEcoles() {

        PictureDAO pictureDAO = new PictureDAO(getString(R.string.url_serveur));

        final ArrayList<ImageEcole> imageEcoles = new ArrayList<>();
        for (Ecole ecole : ListeObjets.listeEcole) {
            ImageEcole imageEcole = new ImageEcole(ecole.getNom(), ecole);
            pictureDAO.addPicture(this, imageEcole, ecole.getImage());
            imageEcoles.add(imageEcole);
        }
        return imageEcoles;
    }

    @Override
    protected void contentTemp(){
        text = "Planning";
        //tes = (TextView) findViewById(R.id.textPlanning);

    }

    @Override
    protected void llayout(){
        setLayout(R.layout.activity_planning);
    }
}