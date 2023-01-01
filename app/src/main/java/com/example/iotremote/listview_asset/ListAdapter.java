package com.example.iotremote.listview_asset;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.iotremote.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<LvAsset> {


    public ListAdapter(Context context, ArrayList<LvAsset> userArrayList){

        super(context, R.layout.list_item,userArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LvAsset lvAsset = getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }

        ImageView imageView = convertView.findViewById(R.id.asset_pic);
        TextView AssetName = convertView.findViewById(R.id.assetName);
        TextView AssetID = convertView.findViewById(R.id.assetID);
        TextView AssetStatus = convertView.findViewById(R.id.asset_Status);

        imageView.setImageResource(lvAsset.imageId);
        AssetName.setText(lvAsset.name);
        AssetID.setText(lvAsset.ID);
        AssetStatus.setText(lvAsset.Status);


        return convertView;
    }
}
