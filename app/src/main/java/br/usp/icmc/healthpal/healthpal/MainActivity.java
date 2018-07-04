package br.usp.icmc.healthpal.healthpal;

import android.support.v4.app.Fragment;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mFragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
        } else {
            mFragment = new DashboardFragment();
        }

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mFragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Fragment selected = null;

        switch (item.getItemId()) {
            case R.id.navigation_medicine:
                selected = new MedicineListFragment();
                break;
            default:
                selected = new DashboardFragment();
                break;
        }

        mFragment = selected;
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
        return true;
    };

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mFragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        getSupportFragmentManager().putFragment(savedInstanceState, "currentFragment", mFragment);
    }
}
