package com.egco428.firebasenotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by 6272user on 11/24/2016 AD.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){

        for (String k:remoteMessage.getData().keySet()){
            String title  = remoteMessage.getData().get(k);
            String text  = remoteMessage.getData().get(k);
//            Log.i("Result","onMessageReceived: "+s);
        }
        if (remoteMessage.getNotification() != null){
            String title = remoteMessage.getNotification().getTitle();
            String text = remoteMessage.getNotification().getBody();
//            Log.i("Result","onMessageReceived: "+s);
            sendNotification(title,text);
        }
    }

    private void sendNotification(String title,String text){

        Intent intent = new Intent(MyFirebaseMessagingService.this,CallActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(MyFirebaseMessagingService.this, (int)System.currentTimeMillis(), intent, 0);

        Intent intent1 = new Intent(MyFirebaseMessagingService.this,SettingActivity.class);
        PendingIntent pIntent1 = PendingIntent.getActivity(MyFirebaseMessagingService.this, (int)System.currentTimeMillis(), intent1, 0);

        Intent intent2 = new Intent(MyFirebaseMessagingService.this,WarningActivity.class);
        PendingIntent pIntent2 = PendingIntent.getActivity(MyFirebaseMessagingService.this, (int)System.currentTimeMillis(), intent2, 0);

        NotificationCompat.Action callAction = new NotificationCompat.Action.Builder(R.drawable.ic_call , "Call", pIntent).build();
        NotificationCompat.Action settingAction = new NotificationCompat.Action.Builder(R.drawable.ic_setting , "Setting", pIntent1).build();
        NotificationCompat.Action warningAction = new NotificationCompat.Action.Builder(R.drawable.ic_warning , "Warning", pIntent2).build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyFirebaseMessagingService.this);
        builder.setSmallIcon(R.drawable.ic_message);
        builder.setContentTitle("New Message from " + title);
        builder.setContentText(text);
        builder.addAction(callAction);
        builder.addAction(settingAction);
        builder.addAction(warningAction);
        builder.setWhen(System.currentTimeMillis()+10);

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }
}
