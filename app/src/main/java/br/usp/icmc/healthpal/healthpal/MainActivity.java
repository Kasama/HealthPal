package br.usp.icmc.healthpal.healthpal;

import android.support.v4.app.Fragment;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashboardFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Fragment selected = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                selected = new DashboardFragment();
                break;
            case R.id.navigation_medicine:
                selected = new MedicineListFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
        return true;
    };
}
