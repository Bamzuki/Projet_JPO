package eu.ensg.jpo.explor_descartes.donnesObjet;

import com.google.gson.Gson;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.Polygon;
import com.mapbox.geojson.gson.GeometryGeoJson;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.style.layers.FillLayer;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.fillOpacity;

public class Batiment extends DataBaseObject{

    private String nom;
    private String fonction;
    private float lat;
    private float lng;
    private Gson geometrie;

    public Batiment(int id, String nom, String fonction, float lat, float lng, Gson geometrie) {
        super(id);
        this.nom = nom;
        this.fonction = fonction;
        this.lat = lat;
        this.lng = lng;
        this.geometrie = geometrie;
    }

    public void afficherSurCarte (MapboxMap mapboxMap){
        mapboxMap.addMarker(new MarkerOptions()
                .position(new LatLng(this.lat, this.lng))
                .title(this.nom));
        GeoJsonSource polygon = new GeoJsonSource("polygon"+this.id, geometrie.toString());
        mapboxMap.getStyle().addLayer((new FillLayer("layer"+this.id, "polygon"+this.id).withProperties(fillOpacity(0.5f))));
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
