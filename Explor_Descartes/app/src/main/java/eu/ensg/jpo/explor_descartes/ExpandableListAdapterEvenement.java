package eu.ensg.jpo.explor_descartes;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eu.ensg.jpo.explor_descartes.donneesAcces.VisiteurDAO;
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;

public class ExpandableListAdapterEvenement extends BaseExpandableListAdapter {

    private EcoleActivity context;
    private List<String> listDataHeader;
    private HashMap<String, List<Evenement>> listHashMap;

    public ExpandableListAdapterEvenement(EcoleActivity context, List<String> listDataHeader, HashMap<String, List<Evenement>> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }


    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String)getGroup(groupPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.liste_entete,null);
        }
        TextView lblListHeader = (TextView)convertView.findViewById(R.id.titre_entete);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Evenement child = (Evenement)getChild(groupPosition, childPosition);
        final String childText = child.getNom();
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.liste_evenement,null);
        }
        final TextView txtListChild = (TextView)convertView.findViewById(R.id.contenu_objets);
        txtListChild.setText(childText);
        if (ListeObjets.visiteur == null){
            txtListChild.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        else {
            ArrayList<Integer> favoris = ListeObjets.visiteur.getListeFavoris();
            int id = listHashMap.get(listDataHeader.get(0)).get(childPosition).getId();
            if (favoris.contains(id)) {
                Drawable img = context.getDrawable(android.R.drawable.star_big_on);
                txtListChild.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
            }
            else {
                Drawable img = context.getDrawable(android.R.drawable.star_big_off);
                txtListChild.setCompoundDrawablesWithIntrinsicBounds(null,null, img, null);
            }
        }
        txtListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ListeObjets.visiteur != null){
                    Boolean tag = Boolean.valueOf(v.getTag().toString());
                    ArrayList<Integer> favoris = ListeObjets.visiteur.getListeFavoris();
                    int id = listHashMap.get(listDataHeader.get(0)).get(childPosition).getId();
                    if ( !favoris.contains(id) ){
                        Drawable img = context.getDrawable(android.R.drawable.star_big_on);
                        txtListChild.setCompoundDrawablesWithIntrinsicBounds(null,null, img, null);
                        VisiteurDAO visiteurDAO = new VisiteurDAO(getContext().getString(R.string.url_serveur));
                        visiteurDAO.ajouterFavori(context, ListeObjets.visiteur, id);
                        v.setTag("true");
                    }
                    else {
                        Drawable img = context.getDrawable(android.R.drawable.star_big_off);
                        txtListChild.setCompoundDrawablesWithIntrinsicBounds(null,null, img, null);
                        VisiteurDAO visiteurDAO = new VisiteurDAO(getContext().getString(R.string.url_serveur));
                        visiteurDAO.supprimerFavori(context, ListeObjets.visiteur, id);
                        v.setTag("false");
                    }
                }
                else {
                    Toast.makeText(context, R.string.favori_impossible, Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public Context getContext() { return context; }

    public void setContext(EcoleActivity context) { this.context = context; }

    public List<String> getListDataHeader() { return listDataHeader; }

    public void setListDataHeader(List<String> listDataHeader) { this.listDataHeader = listDataHeader; }

    public HashMap<String, List<Evenement>> getListHashMap() { return listHashMap; }

    public void setListHashMap(HashMap<String, List<Evenement>> listHashMap) { this.listHashMap = listHashMap; }
}
