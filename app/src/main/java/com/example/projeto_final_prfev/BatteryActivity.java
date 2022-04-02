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
    TextView batteryHealth;
    String currentBatteryHealth="Battery Health ";
    int deviceHealth;
    @SuppressLint("SetTextI18n")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);


        batteryTxt = this.findViewById(R.id.batteryTxt);
        batteryHealth = findViewById(R.id.batteryHealth);
        TextView chargeTime = this.findViewById(R.id.chargeTime);
        BatteryManager batteryM = (BatteryManager) getApplicationContext().getSystemService(Context.BATTERY_SERVICE);
        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        this.registerReceiver(this.broadcastreceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        timeRemaning = batteryM.computeChargeTimeRemaining()/60000;

        chargeTime.setText("Tempo restante para carregamento completo: " + timeRemaning);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent intentScreenName = new Intent(this, ScreenNameService.class);
        intentScreenName.putExtra("tela", "Tela Bateria");
        startService(intentScreenName);
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

    private BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            deviceHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_COLD){

                batteryHealth.setText(currentBatteryHealth+" = Cold");
            }

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_DEAD){

                batteryHealth.setText(currentBatteryHealth+" = Dead");
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_GOOD){

                batteryHealth.setText(currentBatteryHealth+" = Good");
            }

            if(deviceHealth == BatteryManager.BATTERY_HEALTH_OVERHEAT){

                batteryHealth.setText(currentBatteryHealth+" = OverHeat");
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE){

                batteryHealth.setText(currentBatteryHealth+" = Over voltage");
            }

            if (deviceHealth == BatteryManager.BATTERY_HEALTH_UNKNOWN){

                batteryHealth.setText(currentBatteryHealth+" = Unknown");
            }
            if (deviceHealth == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE){

                batteryHealth.setText(currentBatteryHealth+" = Unspecified Failure");
            }
        }
    };
}