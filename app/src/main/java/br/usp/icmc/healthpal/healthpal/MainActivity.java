package br.usp.icmc.healthpal.healthpal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import br.usp.icmc.healthpal.healthpal.Alarm.AlarmHandler;
import br.usp.icmc.healthpal.healthpal.autocomplete.AutocompleteAdapter;
import br.usp.icmc.healthpal.healthpal.components.ButtonCard;

public class MainActivity extends AppCompatActivity {

    AlarmHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.handler = new AlarmHandler(this);

        ButtonCard card = findViewById(R.id.card_add_button);

        card.setOnClickListener((e) -> {
            Intent i = new Intent(MainActivity.this, AddMedicineActivity.class);
            MainActivity.this.startActivity(i);
//            this.handler.setAlarm(
//                    System.currentTimeMillis() + (30 * 1000),
//                    1, 1,
//                    MainActivity.class
//            );
        });
    }
}
