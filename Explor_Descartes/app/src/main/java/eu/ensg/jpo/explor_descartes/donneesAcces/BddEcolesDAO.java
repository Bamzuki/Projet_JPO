package eu.ensg.jpo.explor_descartes.donneesAcces;

import okhttp3.OkHttpClient;

public abstract class BddEcolesDAO<T> {

    protected String urlServeur;
    protected final OkHttpClient client = new OkHttpClient();


    public BddEcolesDAO(String urlServeur) {
        this.urlServeur = urlServeur;
    }

    public abstract T create(T obj);

    public abstract boolean update(T obj);

    public abstract boolean delete(T obj);

}
