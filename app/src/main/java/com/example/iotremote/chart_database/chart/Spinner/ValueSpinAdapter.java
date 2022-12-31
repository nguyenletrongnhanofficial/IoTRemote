package com.example.iotremote.chart_database.chart.Spinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iotremote.R;

import java.util.List;

public class ValueSpinAdapter extends ArrayAdapter<ValueSpin> {

    public ValueSpinAdapter(@NonNull Context context, int resource, @NonNull List<ValueSpin> objects) {
        super (context,resource,objects);
    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_thumbnail, parent, false);
        TextView txtNameThumbnailSelect = convertView.findViewById(R.id.txtNameThumbnailSelect);

        ValueSpin valueSpin = this.getItem(position);

        if (valueSpin !=null){
            txtNameThumbnailSelect.setText(valueSpin.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thumbnail, parent, false);
        TextView txtNameThumbnail = convertView.findViewById(R.id.txtNameThumbnail);
        ImageView ivImgThumbnail = convertView.findViewById(R.id.ivImgThumbnail);

        ValueSpin valueSpin = this.getItem(position);
        if (valueSpin != null){
            txtNameThumbnail.setText((valueSpin.getName()));
            ivImgThumbnail.setImageResource(valueSpin.getImg());
        }
        return convertView;
    }
}
