package br.usp.icmc.healthpal.healthpal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import br.usp.icmc.healthpal.healthpal.autocomplete.AutocompleteAdapter;
import br.usp.icmc.healthpal.healthpal.database.Database;
import br.usp.icmc.healthpal.healthpal.database.Medicine;

public class EditMedicineActivity extends AppCompatActivity {

    private AutoCompleteTextView name;
    private EditText description;
    private EditText dosage;
    private Button save;

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_medicine);

        this.db = Database.getInstance(this);

        this.name = findViewById(R.id.medicineName);
        this.description = findViewById(R.id.medicineDescription);
        this.dosage = findViewById(R.id.medicineDosage);
        this.save = findViewById(R.id.medicineSaveButton);

        AutocompleteAdapter adapter =
                new AutocompleteAdapter(this, android.R.layout.simple_dropdown_item_1line);
        this.name.setAdapter(adapter);
        this.name.setOnItemClickListener((adapterView, view, position, l) -> {
            String medicineName = adapter.getItem(position).getName();
            this.name.setText(medicineName);
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
                String name = EditMedicineActivity.this.name.getText().toString();
                String description = EditMedicineActivity.this.description.getText().toString();
                String dosage = EditMedicineActivity.this.dosage.getText().toString();

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
