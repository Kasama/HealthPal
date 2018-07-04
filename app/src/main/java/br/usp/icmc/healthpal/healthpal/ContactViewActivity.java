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

import br.usp.icmc.healthpal.healthpal.database.Medic;

import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicEntry.PHONE;
import static br.usp.icmc.healthpal.healthpal.database.HealPalContract.MedicineEntry.NAME;

public class ContactViewActivity extends AppCompatActivity {

    private TextView name, number;
    private Button edit, call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact_card);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        this.name = findViewById(R.id.contactName);
        this.name = findViewById(R.id.contactNumber);

        this.edit = findViewById(R.id.medicineEditButton);
        this.edit = findViewById(R.id.medicineCallDoctor);


        if (bundle != null) {
            Medic medic = bundle.getParcelable("MEDIC");
            this.name.setText(medic.getName());
            this.number.setText(medic.getPhone());
        }
    }

//    public void handleEdit(View view) {
//        Intent intent = new Intent(this, AddContactActivity.class);
//        this.startActivity(intent);
//    }

    public void handleCall(View view) {

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0377778888"));

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        this.startActivity(callIntent);
    }

}
