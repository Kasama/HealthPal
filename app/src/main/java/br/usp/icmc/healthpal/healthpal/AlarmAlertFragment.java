package br.usp.icmc.healthpal.healthpal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import br.usp.icmc.healthpal.healthpal.components.ButtonCard;
import br.usp.icmc.healthpal.healthpal.database.Alarm;
import br.usp.icmc.healthpal.healthpal.database.Database;
import br.usp.icmc.healthpal.healthpal.database.Medicine;

public class AlarmAlertFragment extends Fragment {
    private Vibrator vibrator;

    private ButtonCard bcMedicineTaken;
    private Animation shake;
    private TextView icon;
    private Alarm triggeredAlarm;
    private Medicine triggeredMedicine;
    private Database db;
    private TextView medicineName;
    private TextView medicineDescription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle arguments = this.getArguments();
        db = Database.getInstance(getContext());
        if (arguments != null) {
            triggeredAlarm = arguments.getParcelable("ALARM");
            this.setupMedicine();
        }
        return inflater.inflate(R.layout.fragment_alarm_alert, container, false);
    }

    private void setupMedicine() {
        @SuppressLint("StaticFieldLeak")
        AsyncTask<Void, Void, Medicine> task = new AsyncTask<Void, Void, Medicine>() {
            @Override
            protected Medicine doInBackground(Void... voids) {
                triggeredMedicine = db.medicineDao().getById(triggeredAlarm.getMedicine());
                return triggeredMedicine;
            }

            @Override
            protected void onPostExecute(Medicine medicine) {
                super.onPostExecute(medicine);
                updateView();
            }

        };
        task.execute();
    }

    private void updateView() {
        medicineName.setText(triggeredMedicine.getName() + ", " + triggeredMedicine.getDosage());
        medicineDescription.setText(triggeredMedicine.getDescription());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);

        medicineName = getView().findViewById(R.id.tv_medicine_name);
        medicineDescription = getView().findViewById(R.id.tv_medicine_description);
        bcMedicineTaken = getView().findViewById(R.id.button_medicine_taken);
        bcMedicineTaken.setOnClickListener((e) -> {
            this.vibrateStop();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new AlarmPostFragment()).commit();
        });

        this.updateView();

        icon = getView().findViewById(R.id.action_card_icon);
        icon.startAnimation(shake);

        this.vibrateStart();
    }

    private void vibrateStart() {
        long[] pattern = {0, 1000, 1000};
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) vibrator.vibrate(pattern, 0);
    }

    private void vibrateStop() {
        if (vibrator != null) vibrator.cancel();
    }
}
