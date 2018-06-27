package br.usp.icmc.healthpal.healthpal;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import br.usp.icmc.healthpal.healthpal.autocomplete.AutocompleteAdapter;
import br.usp.icmc.healthpal.healthpal.components.ButtonCard;
import br.usp.icmc.healthpal.healthpal.database.Database;
import br.usp.icmc.healthpal.healthpal.database.Medicine;

public class AddMedicineActivity extends AppCompatActivity {
    private static final String TAG = "AddMedicineActivity";

    private AutoCompleteTextView name;
    private EditText description;
    private EditText dosage;
    private ButtonCard add;
    private Button date, time;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;

    private int year, month, day;
    private int hour, minute;

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        this.db = Database.getInstance(this);

        this.name = findViewById(R.id.form_medicine_name);
        this.dosage = findViewById(R.id.form_medicine_dosage);
        this.description = findViewById(R.id.form_medicine_description);
        this.add = findViewById(R.id.form_medicine_button_add);
        this.date = findViewById(R.id.form_medicine_button_date);
        this.time = findViewById(R.id.form_medicine_button_time);

        this.add.setOnClickListener((View view) -> {
//            handleSave(view);
            finish();
        });
        AutocompleteAdapter adapter =
                new AutocompleteAdapter(this, android.R.layout.simple_dropdown_item_1line);
        this.name.setAdapter(adapter);
        this.name.setOnItemClickListener((adapterView, view, position, l) -> {
            String medicineName = adapter.getItem(position).getName();
            this.name.setText(medicineName);
        });
        this.dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d(TAG, "onDateSet: date: " + year + "/" + month + "/" + dayOfMonth);
            }
        };
        this.date.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    Calendar cal = Calendar.getInstance();
                    year = cal.get(Calendar.YEAR);
                    month = cal.get(Calendar.MONTH);
                    day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                        AddMedicineActivity.this,
                        dateSetListener,
                        year, month, day);
                    dialog.show();
                }
            }
        );
        this.timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.d(TAG, "onTimeSet: time: " + hourOfDay + ":" + minute);
            }
        };
        this.time.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                hour = cal.get(Calendar.HOUR_OF_DAY);
                minute = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(
                        AddMedicineActivity.this,
                        timeSetListener,
                        hour,
                        minute,
                        android.text.format.DateFormat.is24HourFormat(AddMedicineActivity.this)
                );
                dialog.show();
            }
        });
    }

    public void handleCancel(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    public void handleSave(View view) {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Void> save = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                String name = AddMedicineActivity.this.name.getText().toString();
                String description = AddMedicineActivity.this.description.getText().toString();
                String dosage = AddMedicineActivity.this.dosage.getText().toString();

                Medicine medicine = new Medicine(name, description, dosage, 0);

                db.medicineDao().insert(medicine);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                handleCancel(view);
            }
        };
        save.execute();
    }
}
