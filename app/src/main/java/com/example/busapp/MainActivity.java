package com.example.busapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    private TextView mTitleWindow;
    private TextView mMessageWindow;
    private NotificationChannel notificationChannel;

    private static final String[] STOPS = new String[] {
            "Stop 1", "Stop 2", "Stop 3", "Stop 4", "Stop 5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        createNotificationChannel("test", "this is a test channel");
        Thread updateThread = new Thread(new UpdateThread());
        setContentView(R.layout.activity_main);
        TextView mTitleWindow = (TextView) findViewById(R.id.titleWindow);
        mTitleWindow.setText("Stops");

        URLRequest urlr = new URLRequest("https://feeds.transloc.com/3/routes?agencies=84");
        String json = urlr.get();
        mTitleWindow.setText(json);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, STOPS);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.stops_list);
        textView.setAdapter(adapter);


        notify("test","test");
    }

    private void createNotificationChannel(String channelName, String channelDescription) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = channelName;
            String description = channelDescription;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("test", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void notify(String title, String content) {
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(),
                "test")
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.bus)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        manager.notify(0, notification.build());
    }





}