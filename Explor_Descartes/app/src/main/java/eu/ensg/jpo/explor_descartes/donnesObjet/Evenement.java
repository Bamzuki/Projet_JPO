package eu.ensg.jpo.explor_descartes.donnesObjet;

import java.util.Date;

public class Evenement extends DataBaseObject{

    private String nom;
    private Date debut;
    private Date fin;
    private String ecole;
    private String batiment;

    public Evenement(int id, String nom, Date debut, Date fin, String ecole, String batiment) {
        super(id);
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.ecole = ecole;
        this.batiment = batiment;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
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
