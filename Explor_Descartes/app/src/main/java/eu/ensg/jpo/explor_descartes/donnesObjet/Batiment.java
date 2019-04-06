package eu.ensg.jpo.explor_descartes.donnesObjet;

public class Batiment extends DataBaseObject{

    private String nom;
    private String fonction;
    private float lat;
    private float lng;

    Batiment(int id, String nom, String fonction, float lat, float lng) {
        super(id);
        this.nom = nom;
        this.fonction = fonction;
        this.lat = lat;
        this.lng = lng;

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