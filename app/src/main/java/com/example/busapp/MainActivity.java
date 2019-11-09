package com.example.busapp;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.widget.TextView;

        import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

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
        for(int i = 0; i < 100; i++){
            stringBuilder.append(someMessage);
        }
        mMessageWindow.setText(stringBuilder.toString());
    }
}
