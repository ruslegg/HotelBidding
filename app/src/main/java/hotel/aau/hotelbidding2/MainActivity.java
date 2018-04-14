package hotel.aau.hotelbidding2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends FragmentActivity {
    BottomNavigationView bottomNavigationView;
    public static final String TAG = "Hotel bid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        Fragment loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,loginFragment).commit();

    }
}
