package br.usp.icmc.healthpal.healthpal.autocomplete;

import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;

import br.usp.icmc.healthpal.healthpal.R;

public class AutocompleteAdapter extends ArrayAdapter implements Filterable {
    private ArrayList<AnvisaMedication> medicines;
    public AutocompleteAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.medicines = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return medicines.size();
    }

    @Nullable
    @Override
    public AnvisaMedication getItem(int position) {
        return this.medicines.get(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraints) {
                FilterResults results = new FilterResults();
                if (constraints != null) {
                    try {
                        AutocompleteAdapter self = AutocompleteAdapter.this;
                        String term = constraints.toString();
                        self.medicines = new ArrayList<>();
                        ArrayList<AnvisaMedication> allMeds = AutocompleteAdapter.this.readJson();
                        if (allMeds != null) {
                            for (AnvisaMedication med : allMeds) {
                                if (med.getName().toLowerCase().contains(term.toLowerCase())) {
                                    self.medicines.add(med);
                                }
                            }
                        }
                    } catch (Exception e) {
                        Log.e("HealthPal", "Failed trying to autocomplete", e);
                    }
                    results.values = AutocompleteAdapter.this.medicines;
                    results.count = AutocompleteAdapter.this.medicines.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (filterResults != null && filterResults.count > 0) {
                    AutocompleteAdapter.this.notifyDataSetChanged();
                } else {
                    AutocompleteAdapter.this.notifyDataSetInvalidated();
                }
            }
        };
    }

    private ArrayList<AnvisaMedication> readJson() {
        String medicationJson = readStringResource(R.raw.medications);
        ArrayList<AnvisaMedication> medications = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(medicationJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                AnvisaMedication medication = new AnvisaMedication();
                medication.setName(jsonObject.getString("medicamento"));
                medication.setCompany(jsonObject.getString("empresa"));
                medication.setCode(jsonObject.getString("expediente"));
                medication.setLeaflet(jsonObject.getString("bula"));
                medications.add(medication);
            }
            return medications;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readStringResource(int resource) {
        InputStream stream = this.getContext().getResources().openRawResource(resource);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return writer.toString();
    }
}
