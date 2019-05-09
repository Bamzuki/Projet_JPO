package eu.ensg.jpo.explor_descartes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import eu.ensg.jpo.explor_descartes.donneesAcces.EcoleDAO;
import eu.ensg.jpo.explor_descartes.donneesAcces.EvenementDAO;
import eu.ensg.jpo.explor_descartes.donneesAcces.FormationDAO;
import eu.ensg.jpo.explor_descartes.donnesObjet.Ecole;

public class EcoleActivity extends AppCompatActivity {

    private Ecole ecole;
    private ExpandableListView listView;
    private ExpandableListAdapterEvenement listAdapterEvenement;
    private ExpandableListAdapterFormation listAdapterFormation;
    private List<String> listDataHeader;

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
        ImageView imageEcole = (ImageView) findViewById(R.id.imageEcole);
        int id = (int) this.getResources().getIdentifier("wait","drawable", getPackageName());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
        imageEcole.setImageBitmap(bitmap);
        EcoleDAO ecoleDAO = new EcoleDAO(getString(R.string.url_serveur));
        ecoleDAO.addPicture(this, imageEcole, this.ecole);

        // Chargement de l'adresse de l'école
        TextView adresseEcole = (TextView)findViewById(R.id.adresseEcole);
        adresseEcole.setText(this.ecole.getAdresse());

        // Chargement du site de l'école
        TextView siteEcole = (TextView)findViewById(R.id.siteEcole);
        siteEcole.setText(this.ecole.getSite());
        Linkify.addLinks(siteEcole, Linkify.WEB_URLS);

        // Chargement du texte descriptif de l'école
        TextView descriptionEcole = (TextView)findViewById(R.id.descriptionEcole);
        descriptionEcole.setText(this.ecole.getDescription());

        // 2- Affichage des formations et événements
        FormationDAO formationDAO = new FormationDAO(getString(R.string.url_serveur_ecoles));
        formationDAO.afficherFormation(this);

        EvenementDAO evenementDAO = new EvenementDAO(getString(R.string.url_serveur_ecoles));
        evenementDAO.afficherEvenement(this);

    }

    public void setListViewHeightEvenement(ExpandableListView listView, int group) {
        ExpandableListAdapterEvenement listAdapterEvenement = (ExpandableListAdapterEvenement) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapterEvenement.getGroupCount(); i++) {
            View groupItem = listAdapterEvenement.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapterEvenement.getChildrenCount(i); j++) {
                    View listItem = listAdapterEvenement.getChildView(i, j, false, null, listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapterEvenement.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public void setListViewHeightFormation(ExpandableListView listView, int group) {
        ExpandableListAdapterFormation listAdapterFormation = (ExpandableListAdapterFormation) listView.getExpandableListAdapter();
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(),
                View.MeasureSpec.EXACTLY);
        for (int i = 0; i < listAdapterFormation.getGroupCount(); i++) {
            View groupItem = listAdapterFormation.getGroupView(i, false, null, listView);
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

            totalHeight += groupItem.getMeasuredHeight();

            if (((listView.isGroupExpanded(i)) && (i != group))
                    || ((!listView.isGroupExpanded(i)) && (i == group))) {
                for (int j = 0; j < listAdapterFormation.getChildrenCount(i); j++) {
                    View listItem = listAdapterFormation.getChildView(i, j, false, null,
                            listView);
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);

                    totalHeight += listItem.getMeasuredHeight();

                }
            }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int height = totalHeight
                + (listView.getDividerHeight() * (listAdapterFormation.getGroupCount() - 1));
        if (height < 10)
            height = 200;
        params.height = height;
        listView.setLayoutParams(params);
        listView.requestLayout();
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

    public ExpandableListAdapterEvenement getListAdapterEvenement() {
        return listAdapterEvenement;
    }

    public void setListAdapter(ExpandableListAdapterEvenement listAdapterEvenement) { this.listAdapterEvenement = listAdapterEvenement; }

    public ExpandableListAdapterFormation getListAdapterFormation() {
        return listAdapterFormation;
    }

    public void setListAdapter(ExpandableListAdapterFormation listAdapterFormation) { this.listAdapterFormation = listAdapterFormation; }

    public List<String> getListDataHeader() {
        return listDataHeader;
    }

    public void setListDataHeader(List<String> listDataHeader) { this.listDataHeader = listDataHeader; }




}
