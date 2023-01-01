package com.example.iotremote.listview_asset;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.iotremote.LoginActivity;
import com.example.iotremote.R;
import com.example.iotremote.assetdetail_database.DatabaseHandler;
import com.example.iotremote.chart_database.chart.LineChartActivity;
import com.example.iotremote.databinding.ActivityListviewBinding;

import java.util.ArrayList;
import com.example.iotremote.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LvActivity extends AppCompatActivity {

    ActivityListviewBinding binding;
    DatabaseHandler db_ = MainActivity.db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int[] imageId = {R.drawable.vector__5_, R.drawable.vector__5_, R.drawable.vector__5_};
        String[] name = {""+db_.getAsset(1).getName() ,""+db_.getAsset(2).getName(),""+db_.getAsset(3).getName()};
        String[] id_asset = {""+db_.getAsset(1).getId_asset(),""+db_.getAsset(2).getId_asset(),""+db_.getAsset(3).getId_asset()};
        String[] status = {"Online","Online","Online"};
        ArrayList<LvAsset> userArrayList = new ArrayList<>();

        for(int i = 0;i< imageId.length;i++){

            LvAsset asset = new LvAsset(name[i],id_asset[i],status[i],imageId[i]);
            userArrayList.add(asset);

        }
        ListAdapter listAdapter = new ListAdapter(LvActivity.this,userArrayList);

        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(LvActivity.this, AssetActivity.class);
                i.putExtra("id_dtb", position+1);
                i.putExtra("imageid",imageId[position]);
                startActivity(i);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actionhome:
                        Intent intent = new Intent(LvActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.buttonAList:
                        Intent intent2 = new Intent(LvActivity.this, LvActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.buttonStatistics:
                        Intent intent3 = new Intent(LvActivity.this, LineChartActivity.class);
                        startActivity(intent3);
                        return true;
                    case R.id.buttonpersonal:
                        Intent intent4 = new Intent(LvActivity.this, LoginActivity.class);
                        startActivity(intent4);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }
}