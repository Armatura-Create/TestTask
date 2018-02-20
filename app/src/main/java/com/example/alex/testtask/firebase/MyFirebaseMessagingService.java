package com.example.alex.testtask.firebase;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by alex on 20.02.18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCMService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "From title: " + remoteMessage.getNotification().getTitle());
            Log.e(TAG, "From body: " + remoteMessage.getNotification().getBody());
            Log.e(TAG, "actionClick: " + remoteMessage.getNotification().getClickAction());
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData().toString());
        }

        Intent intent = new Intent("TestTask");
        intent.putExtra("body", remoteMessage.getNotification().getBody());
        intent.putExtra("from", remoteMessage.getNotification().getTitle());

//        Toast.makeText(getBaseContext(), remoteMessage.getNotification().getBody(), Toast.LENGTH_SHORT).show();

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
