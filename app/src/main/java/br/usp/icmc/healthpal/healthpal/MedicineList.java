package br.usp.icmc.healthpal.healthpal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import br.usp.icmc.healthpal.healthpal.components.MedicineListAdapter;
import br.usp.icmc.healthpal.healthpal.database.Database;

public class MedicineList extends AppCompatActivity {

    RecyclerView medicineList;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);
        this.database = Database.getInstance(this);

        this.medicineList = findViewById(R.id.medicineList);

        MedicineListAdapter adapter = new MedicineListAdapter(this, database);
        this.medicineList.setAdapter(adapter);
    }
}
