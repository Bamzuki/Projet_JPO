package eu.ensg.jpo.explor_descartes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

import java.util.Objects;

public class Notif {
    private String CHANNEL_ID="test";
    private int NOTIFICATION_ID=1;
    Intent intent;
    PendingIntent pendingIntent;

    public void showNotification(View view, Context context){

        // Création du NotificationChannel, seulement pour API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification channel name";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription("Notification channel description");
            channel.enableVibration(true);
            channel.enableLights(true);

            // Enregistement du canal sur le système
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = new Intent(context, AccueilActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.icone_lancement_round)
                    .setContentTitle("test titre")
                    .setContentText("text notif")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            notificationManager.notify(NOTIFICATION_ID, notifBuilder.build());
        } else {

        }
    }
}
