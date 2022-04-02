package com.example.projeto_final_prfev;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class WiFiIntentService extends IntentService {


    public WiFiIntentService() {

        super("WifiIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String name = intent.getStringExtra("wifi");
            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("HSS", "Service on destroy");
    }

}