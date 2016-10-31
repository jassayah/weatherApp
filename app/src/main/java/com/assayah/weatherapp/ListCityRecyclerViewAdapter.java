package com.assayah.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.assayah.weatherapp.DataModel.WeatherResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListCityRecyclerViewAdapter extends RecyclerView.Adapter<ListCityRecyclerViewAdapter.CustomViewHolder> {
    private List<WeatherResult> weatherItemList;
    private OnCityClickedListener listener;


    public ListCityRecyclerViewAdapter(List<WeatherResult> weatherItemList, OnCityClickedListener listener) {
        this.weatherItemList = weatherItemList;
        this.listener = listener;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_city_row, null);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final WeatherResult item = weatherItemList.get(i);
        if(item!=null) {
            customViewHolder.bindItem(item);
        }
    }

    @Override
    public int getItemCount() {
        return (null != weatherItemList ? weatherItemList.size() : 0);
    }


    public interface OnCityClickedListener {
        void onCitySelected(WeatherResult cityWeather);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.row_city)
        TextView textViewCity;
        @Bind(R.id.row_temp)
        TextView textViewTemp;

        public CustomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindItem(final WeatherResult item) {
            this.textViewCity.setText(item.getCityName());
            this.textViewTemp.setText(String.valueOf(item.getTemperature()));
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCitySelected(item);
                }
            });
        }
    }
}
