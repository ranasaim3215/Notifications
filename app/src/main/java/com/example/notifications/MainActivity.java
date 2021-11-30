package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.badge.BadgeUtils;

public class MainActivity extends AppCompatActivity {
    Button btn,btn2;
    EditText dataa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        dataa=findViewById(R.id.data);
        btn2=findViewById(R.id.button3);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("Motify","My ", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager =getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder =new NotificationCompat.Builder(MainActivity.this,"Motify");
                builder.setContentTitle("Notification");
                builder.setContentTitle("lorem");
                builder.setAutoCancel(true);
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                NotificationManagerCompat manger=NotificationManagerCompat.from(MainActivity.this);
                 manger.notify(1,builder.build());
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sendData =dataa.getText().toString();
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                i.putExtra("receiver",sendData);
                startActivityForResult(i,2);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String message=data.getStringExtra("MESSAGE");
            dataa.setText(message);
        }
    }
}