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

        ImageView imageView = convertView.findViewById(R.id.profile_pic);
        TextView userName = convertView.findViewById(R.id.personName);
        TextView lastMsg = convertView.findViewById(R.id.lastMessage);
        TextView time = convertView.findViewById(R.id.msgtime);

        imageView.setImageResource(lvAsset.imageId);
        userName.setText(lvAsset.name);
        lastMsg.setText(lvAsset.ID);
        time.setText(lvAsset.Status);


        return convertView;
    }
}
