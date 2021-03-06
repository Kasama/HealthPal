package br.usp.icmc.healthpal.healthpal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.usp.icmc.healthpal.healthpal.components.ButtonCard;
import br.usp.icmc.healthpal.healthpal.components.MedicineListAdapter;
import br.usp.icmc.healthpal.healthpal.database.Database;

public class MedicineListFragment extends Fragment {

    RecyclerView medicineList;
    Database database;
    ButtonCard addButton;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        this.database = Database.getInstance(getActivity());

        this.medicineList = getView().findViewById(R.id.medicineList);
        this.medicineList.setLayoutManager(new LinearLayoutManager(getActivity()));
        MedicineListAdapter adapter = new MedicineListAdapter(getActivity(), database);
        this.medicineList.setAdapter(adapter);

        this.addButton = getView().findViewById(R.id.medicineListAddButton);
        this.addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddMedicineActivity.class);
            getActivity().startActivity(intent);
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_medicine_list, container, false);
    }
}
