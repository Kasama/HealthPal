package br.usp.icmc.healthpal.healthpal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import br.usp.icmc.healthpal.healthpal.components.DashboardCard;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DashboardCard card = findViewById(R.id.dashicon);

        card.setOnClickListener(
                (e) -> Toast.makeText(this, "herro", Toast.LENGTH_LONG).show()
        );

    }
}
