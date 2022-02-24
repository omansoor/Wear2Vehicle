package com.learning.watch2vehicle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.wear.ongoing.OngoingActivity;
import androidx.wear.ongoing.Status;

import com.learning.watch2vehicle.databinding.ActivityChargeTasksBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChargeTasksActivity extends Activity {

    private TextView mTextView;
    private ActivityChargeTasksBinding binding;
    private String CHANNEL_ID = "watch2vehicleChannel";
    private NotificationCompat.Builder builder;
    PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();

        //create notification builder for OngoingNotification
         //with .setOngoing set to true
        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.battery_charging_10)
        .setContentTitle("W2V")
        .setContentText("Charging..")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setProgress(100,35,false)
        // Set the intent that will fire when the user taps the notification
        .setContentIntent(pendingIntent)
        //.setCategory("CATEGORY_STOPWATCH")
        .setOngoing(true)
        .setAutoCancel(true);

        binding = ActivityChargeTasksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.text;
        @SuppressLint("SimpleDateFormat") DateFormat dateFormatter = new SimpleDateFormat("EEE d hh:mm");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s =  dateFormatter.format(today);
        TextView mTextView = binding.text;
        mTextView.setText(s);
    }


    public void createOnGoingActivityNotification(View view) {
        Toast.makeText(this,"An OnGoingNoti",Toast.LENGTH_SHORT).show();

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, ChargeTasksActivity.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        Status ongoingActivityStatus = new Status.Builder()
                // Sets the text used across various surfaces.
                .addTemplate("mainText")
                .build();

        try {
            OngoingActivity ongoingActivity =new
                    OngoingActivity.Builder(
                    this, 199, builder
            )
                    // Sets the animated icon that will appear on the watch face in
                    // active mode.
                    // If it isn't set, the watch face will use the static icon in
                    // active mode.
                    .setAnimatedIcon(R.drawable.battery_charging_10)
                    // Sets the icon that will appear on the watch face in ambient mode.
                    // Falls back to Notification's smallIcon if not set.
                    // If neither is set, an Exception is thrown.
                    .setStaticIcon(R.drawable.battery)
                    // Sets the tap/touch event, so users can re-enter your app from the
                    // other surfaces.
                    // Falls back to Notification's contentIntent if not set.
                    // If neither is set, an Exception is thrown.
                    .setTouchIntent(pendingIntent)
                    // In our case, sets the text used for the Ongoing Activity (more
                    // options are available for timers and stopwatches).
                    .setStatus(ongoingActivityStatus)
                    .build();

            ongoingActivity.apply(this);


            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(199, builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}