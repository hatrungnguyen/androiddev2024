package vn.edu.usth.weather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class WeatherAndForecastFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_weather_and_forecast, container, false);        WeatherFragment weatherFragment = new WeatherFragment();
//        getChildFragmentManager().beginTransaction()
//                .add(R.id.weather_container, weatherFragment)
//                .commit();
        ForecastFragment forecastFragment = new ForecastFragment();
        getChildFragmentManager().beginTransaction()
                .add(R.id.forecast_container, forecastFragment)
                .commit();
        return view;


    }
}