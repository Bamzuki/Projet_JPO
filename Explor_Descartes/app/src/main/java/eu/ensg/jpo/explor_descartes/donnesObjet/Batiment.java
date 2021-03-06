package eu.ensg.jpo.explor_descartes.donnesObjet;

import com.google.gson.JsonObject;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.FillLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import eu.ensg.jpo.explor_descartes.ListeObjets;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillOpacity;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textField;

public class Batiment extends DataBaseObject{

    /**
     * Classe représentant un batiment
     * @param id : id
     * @param nom : nom
     * @param fonction : fonction du batiment
     * @param lat : lattitude
     * @param lng : longitutde
     * @param idEcole : id de l'école à laquelle appartient ce batiment
     * @param geometrie : géométrie
     */


    private String nom;
    private String fonction;
    private float lat;
    private float lng;
    private int id_ecole;
    private JsonObject geometrie;

    //Constructeur
    public Batiment(int id, String nom, String fonction, float lat, float lng, int idEcole, JsonObject geometrie) {
        super(id);
        this.nom = nom;
        this.fonction = fonction;
        this.lat = lat;
        this.lng = lng;
        this.id_ecole = idEcole;
        this.geometrie = geometrie;
    }


    public void afficherSurCarte (Style style){

        /**
         * Fonction permettant d'afficher l'emprise d'batiment sur la carte
         * @param style : outil carte de mapbox
         */

        if (!geometrie.toString().equals("{}")) {

            // I - Création et affichage du polygone :
            GeoJsonSource polygon = new GeoJsonSource("polygon"+this.id, geometrie.toString());
            style.addSource(polygon);
            FillLayer layerBatiment = new FillLayer("batiment"+this.id, "polygon"+this.id).withProperties(fillOpacity(0.5f), fillColor("blue"));
            style.addLayer(layerBatiment);


            // II - Création et affichage du nom :
            String geoJsonPoint = "{\"type\": \"Point\",\"coordinates\": [" + this.lng + ", " + this.lat + "]}";
            GeoJsonSource point = new GeoJsonSource("point"+this.id, geoJsonPoint);
            style.addSource(point);
            SymbolLayer layerNomBatiment = new SymbolLayer("nomBatiment"+this.id, "point"+this.id);
            Float[] translationText = new Float[2];
            translationText[0] = new Float(0);
            translationText[1] = new Float(10);
            String text = ListeObjets.getEcoleById(this.id_ecole).getNom() + "\n(" + this.nom + ")";
            layerNomBatiment.withProperties(PropertyFactory.iconImage("college-15"), textField(text), PropertyFactory.textTranslate(translationText), PropertyFactory.iconAllowOverlap(true), PropertyFactory.textAnchor(Property.TEXT_ANCHOR_TOP));
            style.addLayer(layerNomBatiment);
        }

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

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public int getIdEcole() {
        return id_ecole;
    }

    public void setIdEcole(int idEcole) {
        this.id_ecole = idEcole;
    }
}
