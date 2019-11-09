package com.example.busapp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Notification;
        import android.app.NotificationManager;
        import android.content.Context;
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



    }

    private void notify(String title, String content) {
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification.Builder(getApplicationContext()).
                setContentTitle(title).
                setContentText(content).
                build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(0, notification);
    }
}
