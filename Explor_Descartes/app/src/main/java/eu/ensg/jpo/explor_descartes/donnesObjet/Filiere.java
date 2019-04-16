package eu.ensg.jpo.explor_descartes.donnesObjet;

public class Filiere extends DataBaseObject {

    private String nom;

    public Filiere(int id, String nom) {
        super(id);
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
