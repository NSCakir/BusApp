package com.example.busapp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Notification;
        import android.app.NotificationManager;
        import android.content.Context;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.view.View;
        import android.widget.Toast;

        import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTitleWindow;
    LinearLayout parent;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread updateThread = new Thread(new UpdateThread());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        String[] btn_name = {"Button1","Button2", "Button3", "Button4", "Button5"};
        parent = (LinearLayout) findViewById(R.id.d11_parentlayout);

        for (int i = 0; i < 5; i++) {
            Button b1 = new Button(MainActivity.this);
            b1.setId(i + 1);
            b1.setText(btn_name[0]);
            b1.setTag(i);
            parent.addView(b1);
            b1.setOnClickListener(MainActivity.this);
        }
    }
    @Override
    public void onClick(View v){
        String str=v.getTag().toString();
        if(str.equals("0"))
        {
            Toast.makeText(getApplicationContext(),"Click Button1", Toast.LENGTH_SHORT).show();
        }
        else if(str.equals("1"))
        {
            Toast.makeText(getApplicationContext(),"Click Button2", Toast.LENGTH_SHORT).show();
        }
        else if(str.equals("2"))
        {
            Toast.makeText(getApplicationContext(),"Click Button3", Toast.LENGTH_SHORT).show();
        }
        else if(str.equals("3"))
        {
            Toast.makeText(getApplicationContext(),"Click Button4", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Click Button5", Toast.LENGTH_SHORT).show();
        }



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
