package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class WeatherPagerAdapter extends FragmentStateAdapter {

    public WeatherPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return WeatherAndForecastFragment.newInstance("Paris");
            case 1:
                return WeatherAndForecastFragment.newInstance("Tokyo");
            case 2:
                return WeatherAndForecastFragment.newInstance("London");
            default:
                return WeatherAndForecastFragment.newInstance("Paris");
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}