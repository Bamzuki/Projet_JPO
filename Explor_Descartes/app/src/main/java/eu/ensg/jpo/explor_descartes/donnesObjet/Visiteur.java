package eu.ensg.jpo.explor_descartes.donnesObjet;

import java.util.ArrayList;

public class Visiteur extends DataBaseObject{

    private String prenom;
    private String nom;
    private String pseudo;
    private String email;
    private String mdp;
    private Boolean admin;
    private ArrayList<Integer> listeFavoris;

    public Visiteur(int id, String prenom, String nom, String pseudo, String email, String mdp,Boolean admin) {
        super(id);
        this.prenom = prenom;
        this.nom = nom;
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
        this.admin = admin;
        this.listeFavoris = new ArrayList<Integer>();
    }

    public Visiteur(String prenom, String nom, String pseudo, String email, String mdp,Boolean admin) {
        super(0);
        this.prenom = prenom;
        this.nom = nom;
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
        this.admin = admin;
        this.listeFavoris = new ArrayList<Integer>();
    }

    public Visiteur(int id, String prenom, String nom, String pseudo, String email, String mdp,Boolean admin, ArrayList<Integer> listeFavoris) {
        super(id);
        this.prenom = prenom;
        this.nom = nom;
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
        this.admin = admin;
        this.listeFavoris = listeFavoris;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp ;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public ArrayList<Integer> getListeFavoris() {
        return listeFavoris;
    }

    public void setListeFavoris(ArrayList<Integer> listeFavoris) {
        this.listeFavoris = listeFavoris;
    }
}