package com.example.iotremote.chart_database.chart;

import com.example.iotremote.MainActivity;
import com.example.iotremote.R;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iotremote.chart_database.ChartDBHandler;
import com.example.iotremote.chart_database.chart.Spinner.AssetSpin;
import com.example.iotremote.chart_database.chart.Spinner.AssetSpinAdapter;
import com.example.iotremote.chart_database.chart.Spinner.ValueSpin;
import com.example.iotremote.chart_database.chart.Spinner.ValueSpinAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LineChartActivity extends ChartBase implements
        OnChartValueSelectedListener, SeekBar.OnSeekBarChangeListener {
    Spinner spnAsset, spnValue;
    String choosingAsset, choosingValue;
    private LineChart chart;
    ChartDBHandler db_chart = MainActivity.db_chart;
    int img;
    private SeekBar seekBar;
    private int barvalue=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_linechart);
        //View inflatedView = getLayoutInflater().inflate(R.layout.activity_linechart, null);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setProgress(5);

        spnAsset = findViewById(R.id.spnThumbnail2);
        spnValue = findViewById(R.id.spnThumbnail);
        AssetSpinAdapter AssetAdapter = new AssetSpinAdapter(this,R.layout.item_thumbnail, getAssetListCategory());
        ValueSpinAdapter ValueAdapter = new ValueSpinAdapter(this,R.layout.item_thumbnail, getValueListCategory());
        spnAsset.setAdapter(AssetAdapter);
        spnValue.setAdapter(ValueAdapter);
        spnAsset.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                img = AssetAdapter.getItem(i).getImg();
                choosingAsset = AssetAdapter.getItem(i).getName();
                chart.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                img = ValueAdapter.getItem(i).getImg();
                choosingValue = ValueAdapter.getItem(i).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button btnA = findViewById(R.id.btnAddfillter);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedate(barvalue);
                Toast.makeText(LineChartActivity.this,"Loading\n" +choosingAsset+ " "+ choosingValue,Toast.LENGTH_SHORT).show();
                List <Float> a = db_chart.getValueData(choosingAsset,choosingValue,barvalue);
                setData(barvalue, barvalue, a);
                chart.invalidate();
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        setTitle("LineChartActivity");

        chart = findViewById(R.id.chart1);
        chart.setOnChartValueSelectedListener(this);

        // no description text
        chart.getDescription().setEnabled(false);

        chart.setTouchEnabled(true);

        chart.setDragDecelerationFrictionCoef(0.9f);

        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(true);

        chart.setPinchZoom(true);

        chart.setBackgroundColor(Color.WHITE);

        chart.animateX(1500);

        Legend l = chart.getLegend();

        l.setForm(LegendForm.LINE);
        l.setTypeface(tfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);

        XAxis xAxis = chart.getXAxis();
        xAxis.setTypeface(tfLight);
        xAxis.setTextSize(0f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setTypeface(tfLight);
        rightAxis.setTextColor(Color.RED);
        rightAxis.setAxisMaximum(100f);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);

    }
    private  List<AssetSpin> getAssetListCategory(){
        List<AssetSpin> list = new ArrayList<>();
        list.add(AssetSpin.ThumbnailAsset1);
        list.add(AssetSpin.ThumbnailAsset2);
        list.add(AssetSpin.ThumbnailAsset3);
        return list;
    }
    private  List<ValueSpin> getValueListCategory(){
        List<ValueSpin> list = new ArrayList<>();
        list.add(ValueSpin.ThumbnailValue1);
        list.add(ValueSpin.ThumbnailValue2);
        list.add(ValueSpin.ThumbnailValue3);
        list.add(ValueSpin.ThumbnailValue4);
        return list;
    }
    private void setData(int count, float range, List<Float> values) {
//        int Max = getMaxValue(values)*3;
        int Max =200;
        if (choosingValue == "Humidity (%)"){
            Max = 200;
        }
        else if (choosingValue == "Temperature (°C)"){
            Max = 100;
        }
        else if (choosingValue == "Wind direction"){
            Max = 700;
        }
        else if (choosingValue == "Wind speed (km/h)"){
            Max = 8;
        }
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(Max);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setTypeface(tfLight);
        rightAxis.setTextColor(Color.RED);
        rightAxis.setAxisMaximum(Max);
        rightAxis.setAxisMinimum(0f);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);

        ArrayList<Entry> values1 = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Float val = values.get(i);
            values1.add(new Entry(i, val));
        }
        LineDataSet set1;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values1);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values1, choosingValue);

            set1.setAxisDependency(AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(Color.BLUE);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);

            LineData data = new LineData(set1);
            data.setValueTextColor(Color.BLUE);
            data.setValueTextSize(9f);

            chart.setData(data);
        }
    }
    private void updatedate(int day_count){
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);
        String date_start;
        if (day > day_count) {
            date_start = (day-day_count) + "/" + month + "/"+year;
        }
        else if (day < day_count && month !=1){
            date_start = (day+(30-day_count)) + "/" + (month-1) + "/"+year;
        }
        else date_start = (day+(30-day_count)) + "/" + 12 + "/"+ (year-1);
        TextView start_ = findViewById(R.id.date_start_line_chart);
        TextView end_ = findViewById(R.id.date_end_line_chart);
        start_.setText(date_start);
        end_.setText(""+day+ "/"+month+ "/"+year);

    }
//    private int getMaxValue(List <Float> values){
//        float max = 0;
//        for (int i=0; i< values.size()-1; i++){
//            if (values.get(i) > values.get(i+1))
//                max = values.get(i);
//        }
//        int max_int = (int) max+1;
//        return max_int;
//    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        barvalue = progress;
    }

    @Override
    protected void saveToGallery() {
        saveToGallery(chart, "LineChartActivity");
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());

        chart.centerViewToAnimated(e.getX(), e.getY(), chart.getData().getDataSetByIndex(h.getDataSetIndex())
                .getAxisDependency(), 500);
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}
}