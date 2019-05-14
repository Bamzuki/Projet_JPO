package eu.ensg.jpo.explor_descartes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import eu.ensg.jpo.explor_descartes.GridViewPlanning.GridViewAdapterPlanning;
import eu.ensg.jpo.explor_descartes.GridViewPlanning.ImageEvenement;
import eu.ensg.jpo.explor_descartes.donneesAcces.EvenementDAO;
import eu.ensg.jpo.explor_descartes.donneesAcces.VisiteurDAO;
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;


public class PlanningActivity extends template implements Serializable {

    /**
     * Fonction gèrant la page de planning
     */

    private GridView gridView;
    private GridViewAdapterPlanning gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        contentTemp();

        gridView = (GridView) findViewById(R.id.main_grid);
        gridAdapter = new GridViewAdapterPlanning(this, this, R.layout.grid_item_layout_planning, getListImageEvenements());
        ListeObjets.adapterPlanning = gridAdapter;
        gridView.setAdapter(gridAdapter);
        final PlanningActivity activity = this;
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageEvenement item = (ImageEvenement) parent.getItemAtPosition(position);
                VisiteurDAO visiteurDAO = new VisiteurDAO(getString(R.string.url_serveur) + "serveur.php/");
                Evenement evenement = item.getEvenement();
                if (evenement.estUnFavori(ListeObjets.visiteur)){
                    visiteurDAO.supprimerFavori(activity, evenement);
                }else{
                    visiteurDAO.ajouterFavori(activity, evenement);
                }
                activity.gridAdapter.notifyDataSetChanged();

            }
        });

    }

    private ArrayList<ImageEvenement> getListImageEvenements() {

        EvenementDAO evenementDAO = new EvenementDAO(getString(R.string.url_serveur)+ "serveur.php/");
        evenementDAO.chargerPlanning(this);

        ArrayList<ImageEvenement> imageEvenements = new ArrayList<>();

        return imageEvenements;
    }


    @Override
    protected void llayout(){
        setLayout(R.layout.activity_planning);
    }

    public GridView getGridView() {
        return gridView;
    }

    public GridViewAdapterPlanning getGridAdapter() {
        return gridAdapter;
    }


}