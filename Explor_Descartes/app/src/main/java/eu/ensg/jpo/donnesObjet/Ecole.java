package eu.ensg.jpo.donnesObjet;

import java.net.URL;

public class Ecole extends DataBaseObject{

    private String nom;
    private URL site;
    private String description;

    public Ecole(int id, String nom, URL site, String description) {
        super(id);
        this.nom = nom;
        this.site = site;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public URL getSite() {
        return site;
    }

    public void setSite(URL site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
