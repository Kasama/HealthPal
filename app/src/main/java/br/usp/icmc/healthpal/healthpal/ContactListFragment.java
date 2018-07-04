package br.usp.icmc.healthpal.healthpal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.usp.icmc.healthpal.healthpal.components.MedicineListAdapter;
import br.usp.icmc.healthpal.healthpal.database.Database;

public class ContactListFragment extends Fragment {

    @Nullable
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_list, container, false);
    }
}
