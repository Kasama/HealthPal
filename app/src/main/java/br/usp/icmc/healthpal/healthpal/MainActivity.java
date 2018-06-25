package br.usp.icmc.healthpal.healthpal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.usp.icmc.healthpal.healthpal.Alarm.AlarmHandler;
import br.usp.icmc.healthpal.healthpal.components.DashboardCard;

public class MainActivity extends AppCompatActivity {

    AlarmHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.handler = new AlarmHandler(this);

        DashboardCard card = findViewById(R.id.dashicon);

        card.setOnClickListener((e) -> {
            Intent i = new Intent(MainActivity.this, EditMedicineActivity.class);
            MainActivity.this.startActivity(i);
//            this.handler.setAlarm(
//                    System.currentTimeMillis() + (30 * 1000),
//                    1, 1,
//                    MainActivity.class
//            );
        });

    }
}
