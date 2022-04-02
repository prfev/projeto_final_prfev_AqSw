package com.example.projeto_final_prfev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WifiStatusReceiver receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, WiFiIntentService.class);
        Intent intent2 = new Intent(this, WiFiIntentService.class);
        intent.putExtra("wifi", "Enabled");
        intent2.putExtra("wifi2", "Disabled");

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        String name = intent.getStringExtra("wifi");
        String name2 = intent2.getStringExtra("wifi2");
        button1.setOnClickListener(
                v -> {
                    if (!checkWifiOnAndConnected()) {
                        Toast.makeText(this, name2, Toast.LENGTH_LONG).show();
                    }
                    else if (checkWifiOnAndConnected()) {
                        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
                    }
                }
        );
        button2.setOnClickListener(
                v -> startActivity(new Intent(this, AppsListActivity.class))
        );
        button3.setOnClickListener(
                v -> startActivity(new Intent(this, BatteryActivity.class))
        );

    }
    @Override
    protected void onStart() {
        super.onStart();
        receiver = new WifiStatusReceiver();
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(receiver, filter );
    }
    @Override
    protected void onStop() {
        super.onStop();
        receiver = new WifiStatusReceiver();
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(receiver, filter);
    }
    public boolean checkWifiOnAndConnected() {
        WifiManager wifiMgr = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiMgr.isWifiEnabled();
    }
}