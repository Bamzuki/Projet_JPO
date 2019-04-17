package eu.ensg.jpo.explor_descartes.donnesObjet;

public class Formation extends DataBaseObject {

    private String nom;
    private String niveau;
    private String ecole;
    private String batiment;
    private String filiere;

    public Formation(int id, String nom, String niveau, String ecole, String batiment, String filiere) {
        super(id);
        this.nom = nom;
        this.niveau = niveau;
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

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
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

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

}