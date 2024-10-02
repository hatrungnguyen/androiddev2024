package vn.edu.usth.weather;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.view.Menu;
import android.view.MenuItem;


public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = "WeatherActivity";
    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 1;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ForecastFragment())
                    .commit();
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Weather App");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
        } else {
            extractAndPlayMusic();
        }


        ViewPager2 viewPager = findViewById(R.id.view_pager);
        WeatherPagerAdapter adapter = new WeatherPagerAdapter(this);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Paris");
                    break;
                case 1:
                    tab.setText("Tokyo");
                    break;
                case 2:
                    tab.setText("London");
                    break;
            }
        }).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                simulateNetworkRequest();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void simulateNetworkRequest() {
        new Thread(() -> {
            try {

                Thread.sleep(3000);


                handler.post(() -> {
                    Toast.makeText(WeatherActivity.this, "Data refreshed successfully!", Toast.LENGTH_SHORT).show();
                });
            } catch (InterruptedException e) {
                Log.e(TAG, "Error in network simulation: " + e.getMessage());
            }
        }).start();
    }

    private void refreshData() {

        Log.d(TAG, "Data refreshed!");
        Toast.makeText(this, "Data refreshed!", Toast.LENGTH_SHORT).show();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void extractAndPlayMusic() {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = getResources().openRawResource(R.raw.m);
            File musicFile = new File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "m.mp3");
            outputStream = new FileOutputStream(musicFile);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();


            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(musicFile.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(this, "Music is playing", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Log.e(TAG, "Error playing music: " + e.getMessage());
            Toast.makeText(this, "Error playing music", Toast.LENGTH_SHORT).show();
        } finally {
            // Close streams safely
            try {
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "Error closing streams: " + e.getMessage());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                extractAndPlayMusic();
            } else {
                Toast.makeText(this, "Permission denied to write to external storage", Toast.LENGTH_SHORT).show();
            }
        }
    }

}