package eu.ensg.jpo.explor_descartes.donnesObjet;

import android.graphics.Color;

import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.Date;

import eu.ensg.jpo.explor_descartes.ListeObjets;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textField;

public class Evenement extends DataBaseObject{

    private String nom;
    private String debut;
    private String fin;
    private String ecole;
    private String batiment;

    public Evenement(int id, String nom, String debut, String fin, String ecole, String batiment) {
        super(id);
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.ecole = ecole;
        this.batiment = batiment;
    }

    public void afficherSurCarte(Style style){

        // I - Récupération du bâtiment accueillant l'évènement
        Batiment batiment = ListeObjets.getBatimentFromNom(this.batiment);
        if (batiment == null){
            return;
        }

        // II - Création et affichage du nom :

        // II.1 - Création du GeoJson :
        String geoJsonPoint = "{\"type\": \"Point\",\"coordinates\": [" + batiment.getLng() + ", " + batiment.getLat() + "]}";
        GeoJsonSource pointFavori = new GeoJsonSource("pointFavori"+this.id, geoJsonPoint);
        style.addSource(pointFavori);

        // II.2 - Création du vecteur de translation de l'icone :
        Float[] translationIcon = new Float[2];
        translationIcon[0] = new Float(0);
        translationIcon[1] = new Float(-5);

        // II.3 - Ajout du layer sur la carte :
        SymbolLayer layerFavori = new SymbolLayer("favori"+this.id, "pointFavori"+this.id);
        layerFavori.withProperties(PropertyFactory.iconImage("marker-15"), PropertyFactory.iconTranslate(translationIcon), PropertyFactory.fillColor(Color.YELLOW), PropertyFactory.iconAllowOverlap(true));
        style.addLayer(layerFavori);

        System.out.println("Ajout évènement " + this.id);
    }

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

}
