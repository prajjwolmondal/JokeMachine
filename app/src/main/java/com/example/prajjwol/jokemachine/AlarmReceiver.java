package com.example.prajjwol.jokemachine;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import java.util.Random;

/**
 * Created by Prajjwol on 25/12/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {

    public String generateNiceMessage(){
        String[] messages = {"You're soo fucking beautiful!","You're ammizing!","I Love You Sooooooo Much","Your smile brightens the world", "Is this getting annoying?","Damn girl!! You look stunning!","You smell amaaazing!","If looks could kill, you'd be a mass murderer","<Insert cheesy yet funny comment about how beautiful Ammi looks>","I'm betting that right now I'm thinking about how beautiful you are naked","You in that outfit, is the cure for depression","YOU have the CUTEST smile and laugh :D","Your eyes are BEA-UU-TIFUL. They look like caramel covered candy.","You're such an adorable and cute butt!"};
        int len = messages.length;
        int rand = returnRand(len);
        return messages[rand];
    }

    public int returnRand(int max){
        Random rn = new Random();
        int min = 0;
        return rn.nextInt(max - min + 1) + min;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);
        String message = generateNiceMessage();
        Notification notification = new NotificationCompat.Builder(context)
                //.setCategory(Notification.CATEGORY_PROMO)
                .setContentTitle(message)
                .setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true)
                        //.addAction(android.R.drawable.ic_menu_view, "View details", contentIntent)
                .setContentIntent(contentIntent)
                .setPriority(Notification.PRIORITY_LOW)
                .setVibrate(new long[]{1000, 1000}).build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(001, notification);
//            Intent notificationIntent = new Intent(context, MainActivity.class);
//            PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
//                    notificationIntent, 0);
//            String message = generateNiceMessage();
//            Notification notification = new NotificationCompat.Builder(context)
//                    //.setCategory(Notification.CATEGORY_PROMO)
//                    .setContentTitle(message)
//                    .setSmallIcon(R.drawable.ic_launcher)
//                    .setAutoCancel(true)
//                            //.addAction(android.R.drawable.ic_menu_view, "View details", contentIntent)
//                            //.setContentIntent(contentIntent)
//                    .setPriority(Notification.PRIORITY_LOW)
//                    .setVibrate(new long[]{1000, 1000}).build();
//            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.notify(001, notification);
    }
}