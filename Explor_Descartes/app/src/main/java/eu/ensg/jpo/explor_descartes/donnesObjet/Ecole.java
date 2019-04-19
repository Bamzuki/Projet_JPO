<<<<<<< HEAD
package eu.ensg.jpo.explor_descartes.donnesObjet;

import java.net.URL;
import java.util.List;

public class Ecole extends DataBaseObject{

    private String nom;
    private String adresse;
    private String site;
    private String description;
    private List<Formation> formations;

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

    public String getAdresse() { return adresse; }

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

    public List<Formation> getFormations() { return formations; }

    public void setFormations(List<Formation> formations) { this.formations = formations; }

}
=======
package eu.ensg.jpo.explor_descartes.donnesObjet;

import java.net.URL;
import java.util.List;

public class Ecole extends DataBaseObject{

    private String nom;
    private String image;
    private String adresse;
    private String site;
    private String description;
    private List<Formation> formations;

    public Ecole(int id, String image, String nom, String adresse, String site, String description) {
        super(id);
        this.nom = nom;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdresse() { return adresse; }

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

    public List<Formation> getFormations() { return formations; }

    public void setFormations(List<Formation> formations) { this.formations = formations; }

}
>>>>>>> da15f66a63e66a76d32c747c387c8337550bb115
