package com.example.busapp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Notification;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.content.res.Resources;
        import android.graphics.BitmapFactory;
        import android.os.Bundle;
        import android.widget.TextView;

        import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView mTitleWindow;
    private TextView mMessageWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread updateThread = new Thread(new UpdateThread());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mTitleWindow = (TextView) findViewById(R.id.titleWindow);
        TextView mMessageWindow = (TextView) findViewById(R.id.messageWindow);


        mTitleWindow.setText("Stops");
        StringBuilder stringBuilder = new StringBuilder();

        String someMessage = " This is a message ";
        for(int i = 0; i < 10; i++){
            stringBuilder.append(someMessage);
        }
        mMessageWindow.setText(stringBuilder.toString());

        notify("test", "test");
    }

    private void notify(String title, String content) {
        Context ctx = getApplicationContext();
        Intent notificationIntent = new Intent(ctx, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(ctx,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager nm = (NotificationManager) ctx
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Resources res = ctx.getResources();
        Notification.Builder builder = new Notification.Builder(ctx);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.bus)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(content);
        Notification n = builder.build();

        nm.notify(0, n);
    }
}
