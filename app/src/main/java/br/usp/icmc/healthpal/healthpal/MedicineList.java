package br.usp.icmc.healthpal.healthpal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import br.usp.icmc.healthpal.healthpal.components.MedicineListAdapter;

public class MedicineList extends AppCompatActivity {

    RecyclerView medicineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);

        this.medicineList = findViewById(R.id.medicineList);

    }
}
