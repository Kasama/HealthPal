package br.usp.icmc.healthpal.healthpal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class AlarmRaiseActivity extends AppCompatActivity {
    private Fragment mFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_raise);

        if (savedInstanceState != null) {
            mFragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");
        } else {
            mFragment = new AlarmAlertFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mFragment).commit();
    }

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