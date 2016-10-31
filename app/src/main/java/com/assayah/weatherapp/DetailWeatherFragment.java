package com.assayah.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.assayah.weatherapp.DataModel.WeatherResult;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailWeatherFragment extends Fragment {

    WeatherResult item;
    @Bind(R.id.title_description_textView)
    TextView descriptionTextview;
    @Bind(R.id.temperature_textview)
    TextView temperatureTextview;
    @Bind(R.id.wind_textview)
    TextView windTextview;
    @Bind(R.id.condition_textView)
    TextView conditionTextView;


    public void setWeatherItem(WeatherResult item) {
        this.item = item;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        setUpView();
        if (item == null) {
            Toast.makeText(getActivity(), getString(R.string.problem_message), Toast.LENGTH_LONG).show();
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }

        setActionBar();
        setPlusSignVisibility();
        return view;
    }


    public void setPlusSignVisibility() {
        Activity activity = getActivity();
        if (activity != null && activity instanceof MainActivity) {
            ((MainActivity) activity).setVisibilityForPlusSign(View.GONE);
        }
    }

    private void setActionBar() {
        ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setTitle(item.getCityName());
        }
    }


    private void setUpView() {
        descriptionTextview.setText(item.getTitle());
        temperatureTextview.setText(item.getTemperatureString());
        windTextview.setText(item.getWindSpeedText());
        conditionTextView.setText(item.getConditionText());
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        item = null;
    }

}
