package br.usp.icmc.healthpal.healthpal.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.usp.icmc.healthpal.healthpal.MedicineViewActivity;
import br.usp.icmc.healthpal.healthpal.R;
import br.usp.icmc.healthpal.healthpal.database.Database;
import br.usp.icmc.healthpal.healthpal.database.Medic;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private Context context;
    private Database db;
    private List<Medic> contactList;

    public ContactListAdapter(Context context, final Database db) {
        this.contactList = new ArrayList<>();
        this.context = context;
        this.db = db;
        loadData();
    }

    private void loadData() {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Void> loadCursor =
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        contactList = db.medicDao().getAll();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void contacts) {
                        super.onPostExecute(contacts);
                        notifyDataSetChanged();
                    }
                };
        loadCursor.execute();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.fragment_contact_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Medic contact = this.contactList.get(position);
        holder.name.setText(contact.getName());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(this.context, MedicineViewActivity.class);
            intent.putExtra("CONTACT", contact);
            this.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.contactFragmentName);
        }
    }

}

