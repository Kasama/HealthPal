package br.usp.icmc.healthpal.healthpal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.usp.icmc.healthpal.healthpal.components.ButtonCard;
import br.usp.icmc.healthpal.healthpal.database.Database;
import br.usp.icmc.healthpal.healthpal.database.Medic;

public class AddContactActivity extends AppCompatActivity {
    private static final String TAG = "AddContactActivity";

    private EditText name;
    private EditText description;
    private EditText phone;
    private ButtonCard add;

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.db = Database.getInstance(this);

        this.name = findViewById(R.id.form_contact_name);
        this.description = findViewById(R.id.form_contact_description);
        this.phone = findViewById(R.id.form_contact_phone);
        this.add = findViewById(R.id.form_contact_button_add);

        this.add.setOnClickListener((View view) -> {
//            handleSave(view);
            finish();
        });
        this.add.setOnClickListener(this::handleSave);
    }

    public void handleCancel(View view) {
        finish();
    }

    public void handleSave(View view) {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Medic> save = new AsyncTask<Void, Void, Medic>() {
            @Override
            protected Medic doInBackground(Void... voids) {
                String name = AddContactActivity.this.name.getText().toString();
                String description = AddContactActivity.this.description.getText().toString();
                String phone = AddContactActivity.this.phone.getText().toString();

                Medic contact = new Medic(name, description, phone);

                db.medicDao().insert(contact);

                return contact;
            }

            @Override
            protected void onPostExecute(Medic contact) {
                super.onPostExecute(contact);
                Intent data = new Intent();
                data.putExtra("CONTACT", contact);
                setResult(RESULT_OK, data);
                handleCancel(view);
            }
        };
        save.execute();
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
