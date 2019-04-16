package eu.ensg.jpo.explor_descartes.donnesObjet;

import java.net.URL;

public class Ecole extends DataBaseObject{

    private String nom;
    private String adresse;
    private String site;
    private String description;

    public Ecole(int id, String nom, String adresse, String site, String description) {
        super(id);
        this.nom = nom;
        this.adresse = adresse;
        this.site = site;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
