package com.example.projeto_final_prfev;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AppsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);


        PackageManager packageManager=getPackageManager();
        @SuppressLint("QueryPermissionsNeeded") List<ApplicationInfo> list = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        List<String> values = new ArrayList<>(0);
        for(ApplicationInfo ap:list){
            values.add(ap.packageName);
            if (ap.enabled){
                values.add("Enabled");
            } else {
                values.add("Disabled");
            }
        }
        ListView lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values));
        System.out.println(lista);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent intentScreenName = new Intent(this, ScreenNameService.class);
        intentScreenName.putExtra("tela", "Tela Lista de Apps");
        startService(intentScreenName);
    }
}