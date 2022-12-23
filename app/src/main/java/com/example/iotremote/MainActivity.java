package com.example.iotremote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.example.iotremote.api.ApiService;
import com.example.iotremote.api.AssetAPI;
import com.example.iotremote.assetclass.AssetCs;
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
    private MapView map;
    private ArrayList<OverlayItem> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(
                getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
        );
        setContentView(R.layout.load_map);
        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(16.0);
//============== Start call asset
        AssetAPI.assetAPI.getAsset("6H4PeKLRMea1L0WsRXXWp9").enqueue(new Callback<AssetCs>() {
            @Override
            public void onResponse(Call<AssetCs> call, Response<AssetCs> response) {
                AssetCs asset = response.body();
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
                loadAssetToMap(asset);
            }
            @Override
            public void onFailure(Call<AssetCs> call, Throwable t) {
            }
        });
//==============>end call asset
//============== Start call map bounds
        ApiService.apiService.loadMap().enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                //Toast.makeText(MainActivity.this, "Call API Thanh Cong!", Toast.LENGTH_LONG).show();
                Currency currency = response.body();
                ArrayList<Float> centerList = currency.getOptions().getDefaults().getBounds();
                //Log.d("API CALL", centerList.get(1)+""); //-> Dòng này để kiểm tra API call về
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
//==============>end call map bounds
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
    private void showBottomSheetDialog(AssetCs assetRtn) {
        TextView hum_txt, rainfall_txt, sunal, sunaz, sunir, sunze, temp_txt, uv_txt, winddir, windsp;
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
        double temp_db = assetRtn.getAttributes().getTemperature().getValue().doubleValue();
        double hum_db = assetRtn.getAttributes().getHumidity().getValue().doubleValue();
        double rainfall_db = assetRtn.getAttributes().getRainfall().getValue().doubleValue();
        double sunal_db = assetRtn.getAttributes().getSunAltitude().getValue().doubleValue();
        double sunaz_db = assetRtn.getAttributes().getSunAzimuth().getValue().doubleValue();
        double sunir_db = assetRtn.getAttributes().getSunIrradiance().getValue().doubleValue();
        double sunze_db = assetRtn.getAttributes().getSunZenith().getValue().doubleValue();
        double uv_db = assetRtn.getAttributes().getUVIndex().getValue().doubleValue();
        double winddir_db = assetRtn.getAttributes().getWindDirection().getValue().doubleValue();
        double windsp_db = assetRtn.getAttributes().getWindSpeed().getValue().doubleValue();
        temp_txt.setText(""+check_null_data(temp_db));
        hum_txt.setText(""+check_null_data(hum_db));
        rainfall_txt.setText(""+check_null_data(rainfall_db));
        sunal.setText(""+check_null_data(sunal_db));
        sunaz.setText(""+check_null_data(sunaz_db));
        sunir.setText(""+check_null_data(sunir_db));
        sunze.setText(""+check_null_data(sunze_db));
        uv_txt.setText(""+check_null_data(uv_db));
        winddir.setText(""+check_null_data(winddir_db));
        windsp.setText(""+check_null_data(windsp_db));
        bottomSheetDialog.show();
    }
    private String check_null_data(double a){
        String f = ""+a;
        if (a != -0.1)
            return f;
        else
            return "No data";
    }
    private void loadAssetToMap(AssetCs assetRtn){
        ArrayList<Float> assetInfo = assetRtn.getAttributes().getLocation().getValue().getCoordinates();
        String assetName = assetRtn.getName();
        String assetID = assetRtn.getId();
        items.add(new OverlayItem(""+assetName, ""+assetID, new GeoPoint(assetInfo.get(1), assetInfo.get(0))));
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(), items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                showBottomSheetDialog(assetRtn);
                //Toast.makeText(MainActivity.this, "Bottom Sheet Thanh Cong!", Toast.LENGTH_LONG).show();
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
}