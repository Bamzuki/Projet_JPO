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

    public void afficherSurCarte(NavigationActivity activity, Style style) {

        // I - Récupération du bâtiment accueillant l'évènement
        Batiment batiment = ListeObjets.getBatimentFromNom(this.batiment);
        if (batiment == null){
            return;
        }

        // II - Création et affichage du nom :

        // II.1 - Création du GeoJson :

        String geoJsonPoint = "{\"type\": \"Feature\", \"properties\": {\"nom\": \"" + this.nom + "\", \"type\": \"favori\", \"debut\": \"" + this.debut + "\", \"fin\": \"" + this.fin + "\", \"selected\": false, \"favourite\": true}, \"geometry\": {\"type\": \"Point\",\"coordinates\": [" + batiment.getLng() + ", " + batiment.getLat() + "]}}";
        Feature feature = Feature.fromJson(geoJsonPoint);
        GeoJsonSource pointFavori = new GeoJsonSource("pointFavori"+this.id, geoJsonPoint);
        style.addSource(pointFavori);

        // II.2 - Création du vecteur de translation de l'icone :
        Float[] translationIcon = new Float[2];
        translationIcon[0] = new Float(0);
        translationIcon[1] = new Float(-5);

        // II.3 - Ajout du layer sur la carte :
        SymbolLayer layerFavori = new SymbolLayer("favori"+this.id, "pointFavori"+this.id);
        layerFavori.withProperties(PropertyFactory.iconImage("college-15"), PropertyFactory.iconTranslate(translationIcon), PropertyFactory.iconAllowOverlap(true), PropertyFactory.iconSize(1.5f));
        style.addLayer(layerFavori);

        // II.4 - Création des Views :
        createViewCallout(activity, feature);

        // II.5 - Ajout des bulles :
        SymbolLayer infoWindow = new SymbolLayer("calloutFavori"+this.id, "pointFavori"+this.id).withProperties(PropertyFactory.iconImage("{nom}"), PropertyFactory.iconAnchor(Property.ICON_ANCHOR_BOTTOM_LEFT), PropertyFactory.iconAllowOverlap(true),PropertyFactory.iconOffset(new Float[] {-20.0f, -10.0f})).withFilter(eq((get("selected")), literal(true)));
        style.addLayer(infoWindow);

        System.out.println("Ajout évènement " + this.id);
        System.out.println(geoJsonPoint);
    }

    public void createViewCallout(NavigationActivity activity, Feature feature){

        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.layout_callout, null);

        String title = feature.getStringProperty("nom");
        TextView titleTv = (TextView) view.findViewById(R.id.title);
        titleTv.setText(title);

        String horaires = feature.getStringProperty("debut").substring(11,18) + " - " + feature.getStringProperty("fin").substring(11,18);
        TextView horairesTv = (TextView) view.findViewById(R.id.horaires);
        horairesTv.setText(horaires);

        boolean favourite = feature.getBooleanProperty("favourite");
        ImageView imageView = (ImageView) view.findViewById(R.id.logoView);
        imageView.setImageResource(favourite ? R.drawable.ic_star_15 : R.drawable.ic_star_stroked_15);

        Bitmap bitmap = SymbolGenerator.generate(view);
        activity.imagesMap.put(title, bitmap);
        activity.viewMap.put(title, view);
    }

    public String getJson(){
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

    private static class SymbolGenerator {

        /**
         * Generate a Bitmap from an Android SDK View.
         *
         * @param view the View to be drawn to a Bitmap
         * @return the generated bitmap
         */
        static Bitmap generate(@NonNull View view) {
            int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(measureSpec, measureSpec);

            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();

            view.layout(0, 0, measuredWidth, measuredHeight);
            Bitmap bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
            bitmap.eraseColor(Color.TRANSPARENT);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            return bitmap;
        }
    }

    public String getHoraires(){
        return this.debut.substring(11,16) + " - " + this.fin.substring(11,16);
    }

    public boolean estUnFavori(Visiteur visiteur){
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
