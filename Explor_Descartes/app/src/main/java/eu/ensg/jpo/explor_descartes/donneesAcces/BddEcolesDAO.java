package eu.ensg.jpo.explor_descartes.donneesAcces;

import okhttp3.OkHttpClient;

public abstract class BddEcolesDAO<T> {

    protected String urlServeur;
    protected final OkHttpClient client = new OkHttpClient();


    public BddEcolesDAO(String urlServeur) {
        this.urlServeur = urlServeur;
    }

    public abstract void create(T obj);

    public abstract void update(T obj);

    public abstract void delete(T obj);

}