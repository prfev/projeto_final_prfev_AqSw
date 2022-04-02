package com.example.projeto_final_prfev;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class ScreenNameService extends IntentService {

    public ScreenNameService() {
        super("ScreenNameService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String name = intent.getStringExtra("tela");
            Toast.makeText(this, "Nome da tela: "+ name, Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("PRFEV", "Service onDestroy");
    }
}