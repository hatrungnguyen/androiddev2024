package vn.edu.usth.weather;

import android.os.Bundle;
import android.graphics.Color;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ForecastFragment extends Fragment {

    public ForecastFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        LinearLayout linearLayout = new LinearLayout(getContext());
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT
//        ));
//        linearLayout.setBackgroundColor(Color.parseColor("#200000FF"));
//        TextView textView = new TextView(getContext());
//        textView.setText(" A day ");
//        textView.setTextColor(Color.MAGENTA);
//        textView.setTextSize(24);
//        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        textParams.setMargins(16, 16, 16, 8);
//        linearLayout.addView(textView, textParams);
//        ImageView imageView = new ImageView(getContext());
//        imageView.setImageResource(R.drawable.cloudy);
//        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        textParams.setMargins(16, 16, 16, 8);
//        linearLayout.addView(imageView, imageParams);

        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }

}