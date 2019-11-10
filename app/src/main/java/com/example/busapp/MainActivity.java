package com.example.busapp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Notification;
        import android.app.NotificationManager;
        import android.content.Context;
        import android.os.Bundle;
        import android.widget.ArrayAdapter;
        import android.widget.AutoCompleteTextView;
        import android.widget.TextView;
        import android.widget.Button;

        import android.content.Intent;
        import android.view.View;

        import android.widget.Toast;



        import android.view.View.OnClickListener;
        import android.widget.Toast;


        import org.w3c.dom.Text;

        import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    private TextView mTitleWindow;
    private TextView mMessageWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread updateThread = new Thread(new UpdateThread());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mTitleWindow = (TextView) findViewById(R.id.titleWindow);
        mTitleWindow.setText("Stops");


        //Adapter for auto complete feature
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, STOPS);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.stops_list);
        textView.setAdapter(adapter);


        //Button Creator that sends User to SetActivity page
        Button button1 = (Button) findViewById(R.id.stops123);


        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(MainActivity.this, SetActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),
                        "You are home", Toast.LENGTH_LONG)
                        .show();

            }

        });





    }
    private static final String[] STOPS = new String[] {
            "Stop 1", "Stop 2", "Stop 3", "Stop 4", "Stop 5"
    };

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

