package com.assayah.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.assayah.weatherapp.DataModel.WeatherResult;
import com.assayah.weatherapp.Network.ApiClient;
import com.assayah.weatherapp.Network.WeatherApiInterface;
import com.assayah.weatherapp.Utils.PersistDataUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListWeatherFragment extends Fragment {


    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private ArrayList<String> listOfCityByZipCode;
    private WeatherApiInterface apiService;
    private ArrayList<WeatherResult> list;
    private ListCityRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        apiService = ApiClient.getClient().create(WeatherApiInterface.class);
        listOfCityByZipCode = PersistDataUtils.getListFromData(getContext());
        setRecyclerView();
        setPlusSignVisibility();
        setActionBar();

        getActivity().getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener(){
                    public void onBackStackChanged() {
                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        if (manager != null) {
                            int backStackEntryCount = manager.getBackStackEntryCount();
                            if (backStackEntryCount == 0) {
                                setPlusSignVisibility();
                                setActionBar();
                            }

                        }
                    }
                });
        return view;
    }


    public void setPlusSignVisibility() {
        Activity activity = getActivity();
        if (activity != null && activity instanceof MainActivity) {
            ((MainActivity) activity).setVisibilityForPlusSign(View.VISIBLE);
        }
    }


    public void setActionBar() {
        ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(false);
            mActionBar.setTitle(getString(R.string.title_fragment));
        }
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<WeatherResult>();
        adapter = new ListCityRecyclerViewAdapter(list, (ListCityRecyclerViewAdapter.OnCityClickedListener) getActivity());
        recyclerView.setAdapter(adapter);
        geListOfWeather();

    }

    private void geListOfWeather() {
        for(String zip : listOfCityByZipCode) {
            getYahooWeatherForZipCode(zip);
        }
    }


    public void getYahooWeatherForZipCode(String city) {
        Call<WeatherResult> weatherCall = apiService.getYahooWeather(ApiClient.buildQuery(city));
        weatherCall.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                list.add(response.body());
                adapter.notifyItemInserted(list.size()-1);
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                Toast.makeText(getActivity(), getString(R.string.problem_message), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addCityWithZip(String zipCode) {
        listOfCityByZipCode.add(zipCode);
        getYahooWeatherForZipCode(zipCode);
        PersistDataUtils.saveListInData(listOfCityByZipCode,getContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
    }
}
