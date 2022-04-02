package com.example.projeto_final_prfev;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BatteryActivity extends AppCompatActivity {
    private TextView batteryTxt;
    public long timeRemaning;
    @SuppressLint("SetTextI18n")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        batteryTxt = (TextView) this.findViewById(R.id.batteryTxt);
        TextView chargeTime = (TextView) this.findViewById(R.id.chargeTime);
        BatteryManager bateryM = (BatteryManager) getApplicationContext().getSystemService(Context.BATTERY_SERVICE);
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        timeRemaning = bateryM.computeChargeTimeRemaining();
        chargeTime.setText("Falta Carregar: " + timeRemaning);
    }

    private final BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra( BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float batteryPct = level * 100 / (float)scale;
            batteryTxt.setText(batteryPct + "%");
        }
    };
}