package eu.ensg.jpo.explor_descartes.donnesObjet;

import java.util.Date;

public class Evenement extends DataBaseObject{

    private String nom;
    private Date debut;
    private Date fin;
    private Ecole ecole;
    private Batiment batiment;
    private Filiere filiere;

    public Evenement(int id, String nom, Date debut, Date fin, Ecole ecole, Batiment batiment, Filiere filiere) {
        super(id);
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.ecole = ecole;
        this.batiment = batiment;
        this.filiere = filiere;
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

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

}
