package com.example.iotremote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iotremote.api.ApiService;
import com.example.iotremote.api.AssetAPI;
import com.example.iotremote.assetclass.AssetCs;
import com.example.iotremote.assetdetail_database.DatabaseHandler;
import com.example.iotremote.chart_database.ChartDBHandler;
import com.example.iotremote.chart_database.chart.LineChartActivity;
import com.example.iotremote.listview_asset.LvActivity;
import com.example.iotremote.mapclass.Currency;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static DatabaseHandler db;
    public static ChartDBHandler db_chart;
    private MapView map;
    private ArrayList<OverlayItem> items = new ArrayList<>();
    int db_num =1;
    int saveDataTrigger =0;
    int assetLoaded =3;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(
                getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
        );
        db = new DatabaseHandler(this);
        db_chart = new ChartDBHandler(this);
        //db_chart.deleteTable();
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);
        if (db_chart.checkDate(day,month,year) == 0){
            Toast.makeText(MainActivity.this, "Loaded data to database", Toast.LENGTH_LONG).show();
            saveDataTrigger =1;
        }
        else Toast.makeText(MainActivity.this, "All data update", Toast.LENGTH_LONG).show();
        db.deleteTable();
        setContentView(R.layout.load_map);
        loadMap();
        callAssets();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actionMap:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.buttonAList:
                        Intent intent2 = new Intent(MainActivity.this, LvActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.buttonStatistics:
                        Intent intent3 = new Intent(MainActivity.this, LineChartActivity.class);
                        startActivity(intent3);
                        return true;
//                    case R.id.buttonlogout:
//                        Intent intent4 = new Intent(MainActivity.this, LoginActivity.class);
//                        startActivity(intent4);
//                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    private void callAssets(){
        AssetAPI.assetAPI.getAsset("6H4PeKLRMea1L0WsRXXWp9").enqueue(new Callback<AssetCs>() {
            @Override
            public void onResponse(Call<AssetCs> call, Response<AssetCs> response) {
                AssetCs asset = response.body();
                db.addAsset(asset);
                if (saveDataTrigger == 1) db_chart.addAssetDailyData(asset);
                loadAssetToMap(asset);
            }
            @Override
            public void onFailure(Call<AssetCs> call, Throwable t) {
            }
        });
        AssetAPI.assetAPI.getAsset("2UZPM2Mvu11Xyq5jCWNMX1").enqueue(new Callback<AssetCs>() {
            @Override
            public void onResponse(Call<AssetCs> call, Response<AssetCs> response) {
                AssetCs asset = response.body();
                db.addAsset(asset);
                if (saveDataTrigger == 1) db_chart.addAssetDailyData(asset);
                loadAssetToMap(asset);
            }
            @Override
            public void onFailure(Call<AssetCs> call, Throwable t) {
            }
        });
        AssetAPI.assetAPI.getAsset("4cdWlxEvmDRBBDEc2HRsaF").enqueue(new Callback<AssetCs>() {
            @Override
            public void onResponse(Call<AssetCs> call, Response<AssetCs> response) {
                AssetCs asset = response.body();
                db.addAsset(asset);
                if (saveDataTrigger == 1) db_chart.addAssetDailyData(asset);
                loadAssetToMap(asset);
            }
            @Override
            public void onFailure(Call<AssetCs> call, Throwable t) {
            }
        });
    }
    private void loadMap(){
        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(16.0);
        ApiService.apiService.loadMap().enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Currency currency = response.body();
                ArrayList<Float> centerList = currency.getOptions().getDefaults().getBounds();
                Float kd = centerList.get(0);
                Float vd = centerList.get(1);
                GeoPoint startPoint = new GeoPoint(vd, kd);
                mapController.setCenter(startPoint);
            }
            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API Map That Bai!", Toast.LENGTH_LONG).show();
                Log.d("API CALL", t.getMessage().toString());
            }
        });
    }
    public void loadAssetToMap(AssetCs assetRtn){
        ArrayList<Float> assetInfo = assetRtn.getAttributes().getLocation().getValue().getCoordinates();
        String assetName = assetRtn.getName();
        String assetID = assetRtn.getId();
        items.add(new OverlayItem(""+assetName, "Database Location: "+ db_num, new GeoPoint(assetInfo.get(1), assetInfo.get(0)))); db_num++;
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(), items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                showBottomSheetDialog(item.getSnippet());
                return true;
            }
            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });
        mOverlay.setFocusItemsOnTap(false);
        map.getOverlays().add(mOverlay);
    }
    private void showBottomSheetDialog(String assetDB_Loca) {
        int loca = Integer.parseInt(assetDB_Loca.substring(assetDB_Loca.length() - 1));
        TextView hum_txt, rainfall_txt, sunal, sunaz, sunir, sunze, temp_txt, uv_txt, winddir, windsp, assetName;
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet);
        temp_txt = bottomSheetDialog.findViewById(R.id.asset_temp);
        hum_txt = bottomSheetDialog.findViewById(R.id.asset_hum);
        rainfall_txt = bottomSheetDialog.findViewById(R.id.asset_rainfall);
        sunal = bottomSheetDialog.findViewById(R.id.asset_sunal);
        sunaz = bottomSheetDialog.findViewById(R.id.asset_sunaz);
        sunir = bottomSheetDialog.findViewById(R.id.asset_sunir);
        sunze = bottomSheetDialog.findViewById(R.id.asset_sunze);
        uv_txt = bottomSheetDialog.findViewById(R.id.asset_uv);
        winddir = bottomSheetDialog.findViewById(R.id.asset_winddir);
        windsp = bottomSheetDialog.findViewById(R.id.asset_windsp);
        assetName = bottomSheetDialog.findViewById(R.id.name_tv);
        temp_txt.setText(db.getAsset(loca).getTemperature());
        hum_txt.setText(db.getAsset(loca).getHumidity());
        rainfall_txt.setText(db.getAsset(loca).getRainfall());
        sunal.setText(db.getAsset(loca).getSun_altitude());
        sunaz.setText(db.getAsset(loca).getSun_azimuth());
        sunir.setText(db.getAsset(loca).getSun_irradiance());
        sunze.setText(db.getAsset(loca).getSun_zenith());
        uv_txt.setText(db.getAsset(loca).getUv_index());
        winddir.setText(db.getAsset(loca).getWind_direction());
        windsp.setText(db.getAsset(loca).getWind_speed());
        assetName.setText(db.getAsset(loca).getName() + "\nID: " + db.getAsset(loca).getId_asset());
        bottomSheetDialog.show();
        Button btn_add_asset = bottomSheetDialog.findViewById(R.id.btn_add_asset_10);
        btn_add_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assetLoaded++;
                if (assetLoaded <=6)
                    showAddDialog();
            }
        });
    }
    private void showAddDialog(){
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_asset);
        EditText assetName = dialog.findViewById(R.id.asset_add_et_1);
        EditText assetID = dialog.findViewById(R.id.asset_add_et_2);
        EditText assetX = dialog.findViewById(R.id.asset_add_et_3);
        EditText assetY = dialog.findViewById(R.id.asset_add_et_4);
        EditText assetHumidity = dialog.findViewById(R.id.asset_add_et_5);
        EditText assetRainfall = dialog.findViewById(R.id.asset_add_et_6);
        EditText assetSun_altitude = dialog.findViewById(R.id.asset_add_et_7);
        EditText assetSun_azimuth = dialog.findViewById(R.id.asset_add_et_8);
        EditText assetSun_irradiance = dialog.findViewById(R.id.asset_add_et_9);
        EditText assetSun_zenith = dialog.findViewById(R.id.asset_add_et_10);
        EditText assetTemperature = dialog.findViewById(R.id.asset_add_et_11);
        EditText assetUV_index = dialog.findViewById(R.id.asset_add_et_12);
        EditText assetWind_direction = dialog.findViewById(R.id.asset_add_et_13);
        EditText assetWind_speed = dialog.findViewById(R.id.asset_add_et_14);
        Button butAdd = dialog.findViewById(R.id.asset_add_btn_add);
        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = assetName.getText().toString().trim();
                String ID = assetID.getText().toString().trim();
                Float locationX = Float.parseFloat(assetX.getText().toString().trim());
                Float locationY = Float.parseFloat(assetY.getText().toString().trim());
                String hum = assetHumidity.getText().toString().trim();
                String rain = assetRainfall.getText().toString().trim();
                String sun1 = assetSun_altitude.getText().toString().trim();
                String sun2 = assetSun_azimuth.getText().toString().trim();
                String sun3 = assetSun_irradiance.getText().toString().trim();
                String sun4 = assetSun_zenith.getText().toString().trim();
                String temp = assetTemperature.getText().toString().trim();
                String uv = assetUV_index.getText().toString().trim();
                String wind1 = assetWind_direction.getText().toString().trim();
                String wind2 = assetWind_speed.getText().toString().trim();
                ArrayList<Float> arList = new ArrayList<>();
                arList.add(locationX);
                arList.add(locationY);
                AssetCs assetCs_10;
                assetCs_10 = new AssetCs(name, ID, hum, rain, sun1, sun2, sun3, sun4, temp, uv, wind1, wind2, arList);
                loadAssetToMap(assetCs_10);
                db.addAsset(assetCs_10);
                Toast.makeText(getApplicationContext(), "Add Successfully!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
