package br.usp.icmc.healthpal.healthpal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.usp.icmc.healthpal.healthpal.components.IconTextView;

import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicineEntry.DESCRIPTION;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicineEntry.DOSAGE;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicineEntry.LEAFLET_LINK;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicineEntry.NAME;

public class MedicineViewActivity extends AppCompatActivity {

    private TextView name, last_dosage, prescription, observation;
    private IconTextView icon;
    private Button edit, medic;
    private String leaflet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.medicine_card);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        this.name = findViewById(R.id.medicineName);
        this.last_dosage = findViewById(R.id.lastDosage);
        this.prescription = findViewById(R.id.medicinePrescription);
        this.observation = findViewById(R.id.medicineObservation);
        this.edit = findViewById(R.id.medicineEditButton);
        this.medic = findViewById(R.id.medicineCallDoctor);
        this.icon = findViewById(R.id.medicineIcon);


        if (bundle != null) {
            this.name.setText(bundle.getString(NAME));
            this.last_dosage.setText(bundle.getString(DOSAGE));
            this.prescription.setText(bundle.getString(DESCRIPTION));
            this.observation.setText("Tomar em jejum");
            this.leaflet = bundle.getString(LEAFLET_LINK);
        }


    }

    public void handleEdit(View view) {
        Intent intent = new Intent(this, AddMedicineActivity.class);
        this.startActivity(intent);
    }

    public void handleCall(View view) {

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0377778888"));

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        this.startActivity(callIntent);
    }

    public void handleLeaflet(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(leaflet));
        this.startActivity(intent);
    }

}
