package br.usp.icmc.healthpal.healthpal;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.usp.icmc.healthpal.healthpal.components.MedicineListAdapter;
import br.usp.icmc.healthpal.healthpal.database.Database;
import br.usp.icmc.healthpal.healthpal.database.Medicine;

public class EditAlarmActivity extends AppCompatActivity {

    private List<Medicine> medicineList;
    private Database db;
    private Medicine selectedMedicine;
    private TextView medicineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_alarm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        this.selectedMedicine = null;
        this.medicineList = new ArrayList<>();
        this.db = Database.getInstance(this);
        this.medicineName = this.findViewById(R.id.alarm_medicine_name);

        loadMedicineData();
    }

    private void loadMedicineData() {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                medicineList = db.medicineDao().getAll();
                return null;
            }
        };
        task.execute();
    }

    public void handleSelectAlarm(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ArrayAdapter<Medicine> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_single_choice,
                this.medicineList
        );
        builder.setTitle(R.string.select_medicine);
        builder.setSingleChoiceItems(adapter, -1,
                (dialogInterface, i) -> {
                    if (i > 0 && i <= this.medicineList.size()) {
                        this.selectedMedicine = this.medicineList.get(i);
                    } else {
                        this.selectedMedicine = null;
                    }
                }
        );
        builder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                EditAlarmActivity self = EditAlarmActivity.this;
                if (i > 0 && i <= EditAlarmActivity.this.medicineList.size()) {
                    self.selectedMedicine = self.medicineList.get(i);
                    self.medicineName.setText(self.selectedMedicine.getName());
                } else {
                    self.selectedMedicine = null;
                    self.medicineName.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                EditAlarmActivity self = EditAlarmActivity.this;
                self.selectedMedicine = null;
                self.medicineName.setText("");
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
