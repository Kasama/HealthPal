package br.usp.icmc.healthpal.healthpal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.usp.icmc.healthpal.healthpal.components.ButtonCard;
import br.usp.icmc.healthpal.healthpal.components.ContactListAdapter;
import br.usp.icmc.healthpal.healthpal.database.Database;

public class ContactListFragment extends Fragment {

    RecyclerView contactList;
    Database database;
    ButtonCard addButton;


    @Nullable
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.database = Database.getInstance(getActivity());

        this.contactList = getView().findViewById(R.id.contactList);
        this.contactList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ContactListAdapter adapter = new ContactListAdapter(getActivity(), database);
        this.contactList.setAdapter(adapter);

        this.addButton = getView().findViewById(R.id.contactListAddButton);
        this.addButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddContactActivity.class);
            getActivity().startActivity(intent);
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_list, container, false);
    }
}
