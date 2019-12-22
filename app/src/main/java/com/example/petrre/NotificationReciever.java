package com.example.petrre;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import java.util.ArrayList;

import static com.example.petrre.DisplayActivity.AGE_CATEGORY;
import static com.example.petrre.DisplayActivity.BREED_NAME;
import static com.example.petrre.DisplayActivity.PET_NAME;


public class NotificationReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String breed = intent.getStringExtra(BREED_NAME);
        String pet = intent.getStringExtra(PET_NAME);
        String age_category = intent.getStringExtra(AGE_CATEGORY);
        Boolean flag1 = intent.getBooleanExtra(ReminderActivity.FLAG1,false);
        Boolean flag2 = intent.getBooleanExtra(ReminderActivity.FLAG2,false);
        Boolean flag3 = intent.getBooleanExtra(ReminderActivity.FLAG3,false);
        Boolean flag4 = intent.getBooleanExtra(ReminderActivity.FLAG4,false);

        Log.i("Start", "notification");
        int numMessages = 0;
        /* Invoking the default notification service */
        NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(context);

        mBuilder.setContentTitle("PetRe Reminder");
        mBuilder.setContentText("Plese don't forget your pet");
        mBuilder.setTicker("New Message Alert!");
        mBuilder.setSmallIcon(R.drawable.catdog);

        /* Increase notification number every time a new notification arrives */
        mBuilder.setNumber(++numMessages);

        /* Add Big View Specific Configuration */
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        String[] events = new String[6];
        events[0] = new String("Have nice day with your pet!");
        events[1] = new String("PetRe always with you :)");
        events[2] = new String("XOXO");

// Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle("PetRe:");

// Moves events into the big view
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }

        mBuilder.setStyle(inboxStyle);

        /* Creates an explicit intent for an Activity in your app */
        Intent resultIntent = new Intent(context, NotificationView.class);
        resultIntent.putExtra(BREED_NAME, breed);
        resultIntent.putExtra(PET_NAME, pet);
        resultIntent.putExtra(AGE_CATEGORY, age_category);
        resultIntent.putExtra(ReminderActivity.FLAG1, flag1);
        resultIntent.putExtra(ReminderActivity.FLAG2, flag2);
        resultIntent.putExtra(ReminderActivity.FLAG3, flag3);
        resultIntent.putExtra(ReminderActivity.FLAG4, flag4);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(NotificationView.class);

        /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(234324243,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        /* notificationID allows you to update the notification later on. */
        notificationManager.notify(234324243, mBuilder.build());


    }
}
