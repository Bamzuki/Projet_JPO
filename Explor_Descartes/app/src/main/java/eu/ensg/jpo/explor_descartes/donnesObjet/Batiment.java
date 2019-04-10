package eu.ensg.jpo.explor_descartes.donnesObjet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.Polygon;
import com.mapbox.geojson.gson.GeometryGeoJson;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.FillLayer;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.PropertyValue;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillOpacity;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textField;

public class Batiment extends DataBaseObject{

    private String nom;
    private String fonction;
    private float lat;
    private float lng;
    private JsonObject geometrie;

    public Batiment(int id, String nom, String fonction, float lat, float lng, JsonObject geometrie) {
        super(id);
        this.nom = nom;
        this.fonction = fonction;
        this.lat = lat;
        this.lng = lng;
        this.geometrie = geometrie;
    }

    public void afficherSurCarte (Style style){
        /**
        mapboxMap.addMarker(new MarkerOptions()
                .position(new LatLng(this.lat, this.lng))
                .title(this.nom));
         */
        if (!geometrie.toString().equals("{}")) {
            System.out.println(geometrie.toString());
            GeoJsonSource polygon = new GeoJsonSource("polygon"+this.id, geometrie.toString());
            style.addSource(polygon);
            FillLayer layerBatiment = new FillLayer("batiment"+this.id, "polygon"+this.id).withProperties(fillOpacity(0.5f), fillColor("blue"));
            layerBatiment.withProperties(textField("geometry"));
            style.addLayer(layerBatiment);
        }

    }

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

}
