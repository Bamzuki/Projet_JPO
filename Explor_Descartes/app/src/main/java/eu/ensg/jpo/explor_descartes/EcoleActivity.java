package eu.ensg.jpo.explor_descartes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eu.ensg.jpo.explor_descartes.donneesAcces.FormationDAO;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;

public class EcoleActivity extends AppCompatActivity {

    private Ecole ecole;
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecole);

        // 1- Chargement des données relatives à l'école sélectionnée
        this.ecole = ListeObjets.ecoleSelectionnee;

        // Chargement du nom de l'école
        TextView nomEcole = (TextView)findViewById(R.id.nomEcole);
        nomEcole.setText(this.ecole.getNom());

        // Chargement de l'image de l'école
        ImageView imageView = (ImageView) findViewById(R.id.imageEcole);

        // Chargement de l'adresse de l'école
        TextView adresseEcole = (TextView)findViewById(R.id.adresseEcole);
        adresseEcole.setText(this.ecole.getAdresse());

        // Chargement du site de l'école
        TextView siteEcole = (TextView)findViewById(R.id.siteEcole);
        siteEcole.setText(this.ecole.getSite());
        Linkify.addLinks(siteEcole, Linkify.WEB_URLS);

        // 2- Affichage des formations et événements
        FormationDAO formationDAO = new FormationDAO(getString(R.string.url_serveur_ecoles));
        formationDAO.afficherFormation(this);

        // 3- Desactivation

    }

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

    public ExpandableListView getListView() {
        return listView;
    }

    public void setListView(ExpandableListView listView) {
        this.listView = listView;
    }

    public ExpandableListAdapter getListAdapter() {
        return listAdapter;
    }

    public void setListAdapter(ExpandableListAdapter listAdapter) {
        this.listAdapter = listAdapter;
    }

    public List<String> getListDataHeader() {
        return listDataHeader;
    }

    public void setListDataHeader(List<String> listDataHeader) {
        this.listDataHeader = listDataHeader;
    }

    public HashMap<String, List<String>> getListHashMap() {
        return listHashMap;
    }

    public void setListHashMap(HashMap<String, List<String>> listHashMap) {
        this.listHashMap = listHashMap;
    }



}
