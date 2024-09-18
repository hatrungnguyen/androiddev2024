package vn.edu.usth.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class WeatherAndForecastFragment extends Fragment {
    private  static final String ARG_CITY = "city";

    public static WeatherAndForecastFragment newInstance(String city) {
        WeatherAndForecastFragment fragment = new WeatherAndForecastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            city = getArguments().getString("city");
//        }
//    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather_and_forecast, container, false);
//        getChildFragmentManager().beginTransaction()
//                .add(R.id.weather_container, weatherFragment)
//                .commit();
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ForecastFragment forecastFragment = new ForecastFragment();
        fragmentTransaction.add(R.id.ForecastFragment, forecastFragment);
        WeatherFragment weatherFragment = new WeatherFragment();
        fragmentTransaction.add(R.id.container, weatherFragment);
        fragmentTransaction.commit();

        return view;


    }
}