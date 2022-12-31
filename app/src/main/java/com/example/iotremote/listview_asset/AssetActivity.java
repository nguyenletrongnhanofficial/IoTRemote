package com.example.iotremote.listview_asset;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.iotremote.MainActivity;
import com.example.iotremote.R;
import com.example.iotremote.assetdetail_database.DatabaseHandler;
import com.example.iotremote.databinding.ActivityAssetDetailsBinding;

public class AssetActivity extends AppCompatActivity {

    ActivityAssetDetailsBinding binding;
    DatabaseHandler db_ = MainActivity.db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAssetDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        if (intent != null){
            int id_database = intent.getIntExtra("id_dtb", 1);
            int imageid = intent.getIntExtra("imageid", R.drawable.img_1);
            binding.profileImage.setImageResource(imageid);
            binding.nameTv.setText((db_.getAsset(id_database).getName()));
            binding.idTv.setText((db_.getAsset(id_database).getId_asset()));
            binding.HumTv.setText((db_.getAsset(id_database).getHumidity()));
            binding.rainTv.setText((db_.getAsset(id_database).getRainfall()));
            binding.sun1Tv.setText((db_.getAsset(id_database).getSun_altitude()));
            binding.sun2Tv.setText((db_.getAsset(id_database).getSun_azimuth()));
            binding.sun3Tv.setText((db_.getAsset(id_database).getSun_irradiance()));
            binding.sun4Tv.setText((db_.getAsset(id_database).getSun_zenith()));
            binding.tempTv.setText((db_.getAsset(id_database).getTemperature()));
            binding.uvTv.setText((db_.getAsset(id_database).getUv_index()));
            binding.wind1Tv.setText((db_.getAsset(id_database).getWind_direction()));
            binding.wind2Tv.setText((db_.getAsset(id_database).getWind_speed()));
            binding.date.setText((db_.getAsset(id_database).getDate()));
            //Log.d ("tag", db_.getAsset(id_database).getRainfall());
        }

    }
}