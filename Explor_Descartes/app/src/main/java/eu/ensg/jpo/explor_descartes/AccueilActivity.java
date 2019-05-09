package eu.ensg.jpo.explor_descartes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.GridView.GridViewAdapter;
import eu.ensg.jpo.explor_descartes.GridView.ImageEcole;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;

public class AccueilActivity extends template {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        contentTemp();

        gridView = (GridView) findViewById(R.id.main_grid);
        GridViewAdapter gridAdapter = new GridViewAdapter(this, this, R.layout.grid_item_layout, getListImageEcoles());
        gridView.setAdapter(gridAdapter);
        //Ajout de l'event listener :
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageEcole item = (ImageEcole) parent.getItemAtPosition(position);
                //Create intent
                Intent intent = new Intent(AccueilActivity.this, EcoleActivity.class);
                ListeObjets.ecoleSelectionnee = item.getEcole();

                //Start details activity
                startActivity(intent);
            }
        });

    }

    private ArrayList<ImageEcole> getListImageEcoles() {


        final ArrayList<ImageEcole> imageEcoles = new ArrayList<>();
        for (Ecole ecole : ListeObjets.listeEcole) {
            ImageEcole imageEcole = new ImageEcole(ecole.getNom(), ecole);
            imageEcoles.add(imageEcole);
        }
        return imageEcoles;
    }


    @Override
    protected void llayout(){
        setLayout(R.layout.activity_accueil);
    }

    @Override
    public void onStart() {
        super.onStart();
        GridViewAdapter adapter = (GridViewAdapter) this.gridView.getAdapter();
        for (ImageEcole imageEcole : adapter.getData()){
            imageEcole.addPicture(this, adapter, getString(R.string.url_serveur));
        }


    }

}