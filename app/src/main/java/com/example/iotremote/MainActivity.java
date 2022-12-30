package com.example.iotremote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iotremote.api.ApiService;
import com.example.iotremote.api.AssetAPI;
import com.example.iotremote.assetclass.AssetCs;
import com.example.iotremote.assetdetail_database.DatabaseHandler;
import com.example.iotremote.listview_asset.LvActivity;
import com.example.iotremote.mapclass.Currency;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static DatabaseHandler db;
    private MapView map;
    private ArrayList<OverlayItem> items = new ArrayList<>();
    int test=1;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(
                getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
        );
        db = new DatabaseHandler(this);
        db.deleteTable();
        setContentView(R.layout.load_map);
        loadMap();
        callAssets();
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
        items.add(new OverlayItem(""+assetName, "Database Location: "+test, new GeoPoint(assetInfo.get(1), assetInfo.get(0)))); test++;
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
        assetName.setText(db.getAsset(loca).getName() + "\nID: " + db.getAsset(loca).getId_db());
        Button btn_1 = bottomSheetDialog.findViewById(R.id.button_show_insight);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartInsightIntent();
            }
        });
        bottomSheetDialog.show();
    }
    private void StartInsightIntent(){
        Intent intent = new Intent(MainActivity.this, LvActivity.class);
        startActivity(intent);
    }
}
