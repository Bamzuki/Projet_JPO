package eu.ensg.jpo.explor_descartes.Menu;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import eu.ensg.jpo.explor_descartes.ListeObjets;
import eu.ensg.jpo.explor_descartes.donnesObjet.Evenement;

public class BackgroundNotification extends Service {
    Timer timer = new Timer();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                ArrayList<Evenement> listEvent = ListeObjets.listeEvenement;
                if(ListeObjets.visiteur != null) {
                    ArrayList<Integer> listFav = ListeObjets.visiteur.getListeFavoris();
                    for (int ct = 0; ct < listEvent.size(); ct++) {
                        if (listFav.contains(listEvent.get(ct).getId())) {
                            Date debut;
                            try {
                                debut = sdf.parse(listEvent.get(ct).getDebut());
                                long interval = debut.getTime() - date.getTime();
                                if (!(ListeObjets.listeNotif.contains(listEvent.get(ct))) && (interval < 30 * 60 * 100000) && (interval > 0)) {
                                    System.out.println(listEvent.get(ct).getEcole());
                                    listEvent.get(ct).setInterval(interval);
                                    ListeObjets.listeNotif.add(listEvent.get(ct));

                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                System.out.println(1+" hey oh");

            }
        };
        timer.schedule(t,1000,10000);
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        System.out.println("fin timer");
        timer.cancel();
        timer.purge();
        timer = null;
    }
}
