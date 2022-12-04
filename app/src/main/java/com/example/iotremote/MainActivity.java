package com.example.iotremote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

//import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.iotremote.api.ApiService;
import com.example.iotremote.model.Center;
import com.example.iotremote.model.Currency;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private MapView map;

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
//        GeoPoint startPoint = new GeoPoint(10.86815, 106.79931);
        IMapController mapController = map.getController();
        mapController.setZoom(14.0);

        ApiService.apiService.loadMap().enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(MainActivity.this, "Call API Thanh Cong!", Toast.LENGTH_LONG).show();
                Log.d("API CALL", response.code()+"");
                Currency currency = response.body();
                Log.d("API CALL", currency.options+"");
//                Currency currency = response.body();
//
//                List<Center> centerList = currency.getOptions().getDefaults().getCenters();

//============== Start

                // => Bốn dòng bên dưới bị lỗi, t truy xuất phần tử đầu và cuối của center để truyền vào tọa độ
//                Float kd = centerList.get(0);
//                Float vd = centerList.get(1);
//                GeoPoint startPoint = new GeoPoint(kd, vd);
//                mapController.setCenter(startPoint);

//============== End

            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API Map That Bai!", Toast.LENGTH_LONG).show();
                Log.d("API CALL", t.getMessage().toString());
            }
        });


        //end

        ArrayList<OverlayItem> items = new ArrayList<>();
        OverlayItem home = new OverlayItem("Rallo's office", "my office", new GeoPoint(10.87304, 106.80524));
        Drawable m = home.getMarker(0);
        items.add(home);
        items.add(new OverlayItem("Resto", "chez babar", new GeoPoint(10.87304, 106.80524)));
        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(), items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
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

}