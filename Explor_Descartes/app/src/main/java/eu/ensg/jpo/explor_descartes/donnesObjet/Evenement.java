package eu.ensg.jpo.explor_descartes.donnesObjet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mapbox.geojson.Feature;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.Date;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.NavigationActivity;
import eu.ensg.jpo.explor_descartes.R;


import static com.mapbox.mapboxsdk.style.expressions.Expression.eq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import com.mapbox.mapboxsdk.style.layers.Property;

public class Evenement extends DataBaseObject implements Comparable<Evenement>{

    /**
     * Classe représentant un événement
     * @param id : id
     * @param nom : intitulé
     * @param debut : heure de début
     * @param fin : heure de fin
     * @param ecole : école où a lieu l'événement
     * @param batiment : batiment où à lieu l'événement
     */

    private String nom;
    private String debut;
    private String fin;
    private String ecole;
    private String batiment;
    private long interval;

    //Constructeur
    public Evenement(int id, String nom, String debut, String fin, String ecole, String batiment) {
        super(id);
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.ecole = ecole;
        this.batiment = batiment;
    }



     public String getJson(){

         /**
          * Fonction permettant de transformer un événement en GeoJSON
          *
          * @return événement sous forme de GeoJSON
          */

        // I - Récupération du bâtiment accueillant l'évènement
        Batiment batiment = ListeObjets.getBatimentFromNom(this.batiment);
        if (batiment == null){
            return null;
        }
        String Json = "{\"type\": \"Feature\", \"properties\": {\"id\": \""+this.id+"\", \"nom\": \"" + this.nom + "\", \"titre\": \"" + this.ecole+ "\n(" + this.batiment +")\", \"type\": \"favori\", \"debut\": \"" + this.debut + "\", \"fin\": \"" + this.fin + "\", \"selected\": false, \"favourite\": true}, \"geometry\": {\"type\": \"Point\",\"coordinates\": [" + batiment.getLng() + ", " + batiment.getLat() + "]}}";
        return Json;
    }

    @Override
    public int compareTo(Evenement o) {
        return this.getHoraires().compareTo(o.getHoraires());
    }

    public String getHoraires(){

        /**
         * Fonction permettant de récupérer l'horraire dun événement
         *
         * @return horaires de début et de fin de l'événement
         */

        return this.debut.substring(11,16) + " - " + this.fin.substring(11,16);
    }

    public boolean estUnFavori(Visiteur visiteur){

        /**
         * Fonction permettant de vérifier si un événement est en favori ou pas
         *
         * @return booléen true si l'événement est en favori, false sinon
         */

        if (ListeObjets.visiteur == null){
            return false;
        }
        for (int idFavori : visiteur.getListeFavoris()){
            if (idFavori == this.getId()){
                return true;
            }
        }
        return false;
    }


    /*
    Getter et Setter
     */

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getEcole() {
        return ecole;
    }

    public void setEcole(String ecole) {
        this.ecole = ecole;
    }

    public String getBatiment() {
        return batiment;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }

    public long getInterval(){
        return interval;
    }

    public void setInterval(long interval){
        this.interval = interval;
    }

}
