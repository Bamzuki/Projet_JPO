package eu.ensg.jpo.explor_descartes.donnesObjet;

public class Formation extends DataBaseObject {

    /**
     * Classe représentant une formation
     * @param id : id
     * @param nom : intitulé de la formatio,
     * @param niveau : niveau d'étude associé à la formation
     * @param ecole : école proposant cette formation
     * @param batiment : batiment où est réalisé cette formation
     * @param filiere : filère associé à la formation
     */


    private String nom;
    private String niveau;
    private String ecole;
    private String batiment;
    private String filiere;


    //Constructeur
    public Formation(int id, String nom, String niveau, String ecole, String batiment, String filiere) {
        super(id);
        this.nom = nom;
        this.niveau = niveau;
        this.ecole = ecole;
        this.batiment = batiment;
        this.filiere = filiere;
    }


    /*
    Getter et Setter
     */

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