package eu.ensg.jpo.explor_descartes.donnesObjet;

public class Formation extends DataBaseObject {

    private String nom;
    private String niveau;
    private Ecole ecole;
    private Batiment batiment;
    private Filiere filiere;

    public Formation(int id, String nom, String niveau, Ecole ecole, Batiment batiment, Filiere filiere) {
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
