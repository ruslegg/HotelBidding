package hotel.aau.hotelbidding2;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import hotel.aau.hotelbidding2.adapter.TabsPagerFragmentAdapter;

public class BidsActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bids);
        initTabs();

        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_albums:

                            case R.id.navigation_artists:

                            case R.id.navigation_songs:

                        }
                        return true;
                    }
                });
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        TabsPagerFragmentAdapter tabsPagerFragmentAdapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsPagerFragmentAdapter);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }
}
