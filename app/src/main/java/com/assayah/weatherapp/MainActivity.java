package com.assayah.weatherapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.assayah.weatherapp.DataModel.WeatherResult;
import com.assayah.weatherapp.Utils.AlertDialogFactory;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ListCityRecyclerViewAdapter.OnCityClickedListener, AlertDialogFactory.CreateCityListener {

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_container, new ListWeatherFragment());
        transaction.commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addACityPopup();
            }
        });
    }


    public void setVisibilityForPlusSign(int visibility) {
        if (fab != null) {
            fab.setVisibility(visibility);
        }
    }

    private void addACityPopup() {
        AlertDialogFactory.createDialogForAdding(this, this).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCitySelected(WeatherResult cityWeather) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetailWeatherFragment detailWeatherFragment = new DetailWeatherFragment();
        detailWeatherFragment.setWeatherItem(cityWeather);
        transaction.add(R.id.main_container, detailWeatherFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onAddingCityWithZipCode(String zipCode) {
        Fragment currentFragment = this.getSupportFragmentManager().findFragmentById(R.id.main_container);
        if(currentFragment !=null && currentFragment instanceof ListWeatherFragment) {
            ((ListWeatherFragment)currentFragment).addCityWithZip(zipCode);
        }
    }
}
