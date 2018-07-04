package br.usp.icmc.healthpal.healthpal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.usp.icmc.healthpal.healthpal.components.EmergencyCard;

public class EmergencyListFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_emergency_list, container, false);
    }


    @Nullable
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        EmergencyCard hospital = getView().findViewById(R.id.hospital);
        EmergencyCard firefighters = getView().findViewById(R.id.firefighters);
        EmergencyCard police = getView().findViewById(R.id.police);
        EmergencyCard contact = getView().findViewById(R.id.emergencyContact);


        hospital.setOnClickListener((e) -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:0377778888"));
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},0);
            }
            else {
                try {
                    startActivity(callIntent);
                }
                catch (SecurityException error) {
                    error.printStackTrace();
                }
            }
        });

        firefighters.setOnClickListener((e) -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:0377778888"));

            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},0);
            }
            else {
                try {
                    startActivity(callIntent);
                }
                catch (SecurityException error) {
                    error.printStackTrace();
                }
            }
        });

        police.setOnClickListener((e) -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:0377778888"));
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},0);
            }
            else {
                try {
                    startActivity(callIntent);
                }
                catch (SecurityException error) {
                    error.printStackTrace();
                }
            }
        });

        contact.setOnClickListener((e) -> {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:0377778888"));
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},0);
            }
            else {
                try {
                    startActivity(callIntent);
                }
                catch (SecurityException error) {
                    error.printStackTrace();
                }
            }
        });

    }
}
