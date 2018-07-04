package br.usp.icmc.healthpal.healthpal;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.usp.icmc.healthpal.healthpal.components.ButtonCard;
import br.usp.icmc.healthpal.healthpal.database.Alarm;
import br.usp.icmc.healthpal.healthpal.database.Database;
import br.usp.icmc.healthpal.healthpal.database.Medicine;

public class EditAlarmActivity extends AppCompatActivity {

    private List<Medicine> medicineList;
    private Database db;
    private Date initialTime;
    private int interval;
    private Medicine selectedMedicine;
    private ButtonCard medicineSelector;
    private ButtonCard timeSelector;
    private ButtonCard buttonAdd;
    private EditText intervalText;

    private static final int medicineRequest = 827;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_alarm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.selectedMedicine = null;
        this.medicineList = new ArrayList<>();
        this.initialTime = null;
        this.interval = -1;
        this.db = Database.getInstance(this);

        this.medicineSelector = this.findViewById(R.id.alarm_select_medicine);
        this.timeSelector = findViewById(R.id.form_medicine_button_time);
        this.buttonAdd = findViewById(R.id.form_alarm_button_add);
        this.intervalText = findViewById(R.id.form_alarm_interval);

        this.buttonAdd.setEnabled(false);
        this.intervalText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    interval = Integer.parseInt(editable.toString());
                } catch (Exception e ) {
                    interval = -1;
                }
                updateAddButton();
            }
        });

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

    public void handleSelectMedicine(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        ArrayAdapter<Medicine> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_single_choice,
                this.medicineList
        );
        builder.setTitle(R.string.select_medicine);
        builder.setIcon(R.drawable.fa_capsules);
        builder.setSingleChoiceItems(adapter, -1,
                (dialogInterface, i) -> {
                    if (i >= 0 && i <= this.medicineList.size()) {
                        this.selectedMedicine = this.medicineList.get(i);
                    }
                }
        );
        builder.setNegativeButton(R.string.cancel, (d, i) -> {});
        builder.setNeutralButton(R.string.title_create_medicine, (d, i) -> {
            Intent intent = new Intent(this, AddMedicineActivity.class);
            this.startActivityForResult(intent, medicineRequest);
        });
        if (this.medicineList.size() > 0)
            builder.setPositiveButton(R.string.confirm, (d, i) -> updateMedicineName());
        Dialog dialog = builder.create();
        dialog.show();
    }

    public void handleSelectTime(View view) {
        Calendar cal = Calendar.getInstance();
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int m = cal.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(
                EditAlarmActivity.this,
                (v, hour, minute) -> {
                    this.initialTime = new Date();
                    this.initialTime.setHours(hour);
                    this.initialTime.setMinutes(minute);
                    this.initialTime.setSeconds(0);
                    this.updateInitialTime();
                }, h, m,
                android.text.format.DateFormat.is24HourFormat(EditAlarmActivity.this)
        );
        dialog.show();
    }

    public void handleAddButton(View view) {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Alarm> task = new AsyncTask<Void, Void, Alarm>() {
            @Override
            protected Alarm doInBackground(Void... voids) {
                EditAlarmActivity self = EditAlarmActivity.this;
                Alarm alarm = new Alarm(self.selectedMedicine.get_id(), self.initialTime, 1);
                self.db.alarmDao().insert(alarm);
                return alarm;
            }

            @Override
            protected void onPostExecute(Alarm alarm) {
                super.onPostExecute(alarm);
                Intent data = new Intent();
                data.putExtra("ALARM", alarm);
                setResult(RESULT_OK, data);
                finish();
            }
        };
        task.execute();
    }

    private void updateAddButton() {
        if (this.selectedMedicine != null && this.initialTime != null && this.interval > 0) {
            this.buttonAdd.setEnabled(true);
        }
    }

    private void updateInitialTime() {
        String time = String.valueOf(this.initialTime.getHours()) + ":" +
                this.initialTime.getMinutes();
        this.timeSelector.setLabelText(time);
        this.updateAddButton();
    }

    private void updateMedicineName() {
        if (this.selectedMedicine != null) {
            this.medicineSelector.setLabelText(this.selectedMedicine.toString());
        } else {
            this.medicineSelector.setLabelText(R.string.select_medicine);
        }
        this.updateAddButton();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != medicineRequest) return;
        if (resultCode != RESULT_OK) return;

        Medicine medicine = data.getParcelableExtra("MEDICINE");
        this.selectedMedicine = medicine;
        this.updateMedicineName();
        loadMedicineData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
