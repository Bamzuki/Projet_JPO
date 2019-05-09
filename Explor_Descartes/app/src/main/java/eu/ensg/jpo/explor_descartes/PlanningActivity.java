package eu.ensg.jpo.explor_descartes;

import android.os.Bundle;
import android.widget.GridView;

import java.io.Serializable;
import java.util.ArrayList;

import eu.ensg.jpo.explor_descartes.GridView.GridViewAdapter;
import eu.ensg.jpo.explor_descartes.GridView.ImageEcole;
import eu.ensg.jpo.explor_descartes.GridViewPlanning.GridViewAdapterPlanning;
import eu.ensg.jpo.explor_descartes.GridViewPlanning.ImageEvenement;
import eu.ensg.jpo.explor_descartes.donneesAcces.EvenementDAO;


public class PlanningActivity extends template implements Serializable {

    private GridView gridView;
    private GridViewAdapterPlanning gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        contentTemp();

        gridView = (GridView) findViewById(R.id.main_grid);
        gridAdapter = new GridViewAdapterPlanning(this, this, R.layout.grid_item_layout_planning, getListImageEvenements());
        gridView.setAdapter(gridAdapter);

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