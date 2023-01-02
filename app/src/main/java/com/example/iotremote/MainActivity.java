package com.example.iotremote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alan.alansdk.AlanCallback;
import com.alan.alansdk.AlanConfig;
import com.alan.alansdk.button.AlanButton;
import com.alan.alansdk.events.EventCommand;
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

import org.json.JSONException;
import org.json.JSONObject;
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
    private AlanButton alanButton;
    private static final String ALAN_BUTTON = "AlanButton";
    AlanConfig config = AlanConfig.builder().setProjectId("e52a875b8325624ba37eb531c05383192e956eca572e1d8b807a3e2338fdd0dc/stage").build();
    public static DatabaseHandler db;
    public static ChartDBHandler db_chart;
    private MapView map;
    private ArrayList<OverlayItem> items = new ArrayList<>();
    int db_num =1;
    int saveDataTrigger =0;
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

        //
        alanButton = findViewById(R.id.alan_button);
        alanButton.initWithConfig(config);
        AlanCallback alanCallback = new AlanCallback() {
            /// Handle commands from Alan Studio
            @Override
            public void onCommand(final EventCommand eventCommand) {
                try {
                    JSONObject command = eventCommand.getData();
//                    Log.d("JSONObject", "Basic Object: " + command);
                    JSONObject data = command.getJSONObject("data");
//                    Log.d("JSONObject", "Data Object: " + data);
                    String commandName = data.getString("command");
                    executeCommand(commandName, data);
                } catch (JSONException e) {
                    Log.e(ALAN_BUTTON, e.getMessage());
                }
            }
        };
        /// Register callbacks
        alanButton.registerCallback(alanCallback);



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

    private void executeCommand(String commandName, JSONObject data) {
        if (commandName.equals("go_back")) {
            onBackPressed();
        }

        if (commandName.equals("exit")) {
            alanButton.deactivate();
            Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
            finish();
        }

        if (commandName.equals("log_out")) {
            Toast.makeText(this, "log_out", Toast.LENGTH_SHORT).show();
        }

        if (commandName.equals("open_todo")) {
            Toast.makeText(this, "open_todo", Toast.LENGTH_SHORT).show();
        }

        if (commandName.equals("add_task")) {
            Toast.makeText(this, "add_task", Toast.LENGTH_SHORT).show();
        }

        if (commandName.equals("set_title")) {
            try {
                String title = data.getString("title");
                Toast.makeText(this, " "+title, Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                Log.e(ALAN_BUTTON, e.getMessage());
                alanButton.playText("I'm sorry I'm unable to do this at the moment");
            }
        }

        if (commandName.equals("set_description")) {
            try {
                String desc = data.getString("description");
                Toast.makeText(this, " "+desc, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                Log.e(ALAN_BUTTON, e.getMessage());
                alanButton.playText("I'm sorry I'm unable to do this at the moment");
            }
        }

        if (commandName.equals("set_type")) {
            try {
                String type = data.getString("type");
                Toast.makeText(this, " "+type, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                Log.e(ALAN_BUTTON, e.getMessage());
                alanButton.playText("I'm sorry I'm unable to do this at the moment");
            }
        }

        if (commandName.equals("refresh_tasks")) {
            Toast.makeText(this, "refresh_tasks", Toast.LENGTH_SHORT).show();
        }

        if (commandName.equals("confirm_add_task")) {
            Toast.makeText(this, "confirm_add_task", Toast.LENGTH_SHORT).show();
        }

        if (commandName.equals("read_tasks")) {
            Toast.makeText(this, "read_tasks", Toast.LENGTH_SHORT).show();
        }

        if (commandName.equals("highlight_task")) {
            try {
                int position = data.getInt("taskNo");
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                Log.e(ALAN_BUTTON, e.getMessage());
                alanButton.playText("I'm sorry I'm unable to do this at the moment");
            }
        }

        if (commandName.equals("check_task")) {
            try {
                int position = data.getInt("taskNo");
                Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                Log.e(ALAN_BUTTON, e.getMessage());
                alanButton.playText("I'm sorry I'm unable to do this at the moment");
            }
        }

        if (commandName.equals("open_timer")) {
            Toast.makeText(this, "open_timer", Toast.LENGTH_SHORT).show();
        }

        if (commandName.equals("start_timer")) {
            Toast.makeText(this, "start_timer", Toast.LENGTH_SHORT).show();
        }

        if (commandName.equals("pause_timer")) {
            Toast.makeText(this, "pause_timer", Toast.LENGTH_SHORT).show();
        }

        if (commandName.equals("reset_timer")) {
            Toast.makeText(this, "reset_timer", Toast.LENGTH_SHORT).show();
        }

        if (commandName.equals("short_break")) {
            Toast.makeText(this, "short_break", Toast.LENGTH_SHORT).show();
        }

        if (commandName.equals("long_break")) {
            Toast.makeText(this, "long_break", Toast.LENGTH_SHORT).show();
        }

        if (commandName.equals("stop_break")) {
            Toast.makeText(this, "stop_break", Toast.LENGTH_SHORT).show();
        }
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
    private void loadAssetToMap(AssetCs assetRtn){
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
        mOverlay.setFocusItemsOnTap(true);
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
    }
}
