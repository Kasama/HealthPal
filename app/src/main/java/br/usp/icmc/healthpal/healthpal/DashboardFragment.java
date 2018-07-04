package br.usp.icmc.healthpal.healthpal;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.usp.icmc.healthpal.healthpal.Alarm.AlarmHandler;
import br.usp.icmc.healthpal.healthpal.components.AlarmNotification;
import br.usp.icmc.healthpal.healthpal.components.ButtonCard;
import br.usp.icmc.healthpal.healthpal.components.DashboardCard;

public class DashboardFragment extends Fragment {
    AlarmHandler handler;
//    DashboardCard card;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.handler = new AlarmHandler(getActivity());

        ButtonCard card = getView().findViewById(R.id.card_add_button);
        ButtonCard cardAlarm = getView().findViewById(R.id.card_alarm_button);
        ButtonCard cardNotify = getView().findViewById(R.id.card_notify_button);
        DashboardCard card1 = getView().findViewById(R.id.dashicon);

        card1.setOnClickListener((e) -> {
//            getFragmentManager().beginTransaction().replace(R.id.fragment_container, new MedicineListFragment()).commit();
            Intent i = new Intent(getActivity(), EditAlarmActivity.class);
            getActivity().startActivity(i);
        });

        card.setOnClickListener((e) -> {
            Intent i = new Intent(getActivity(), AddMedicineActivity.class);
            getActivity().startActivity(i);
//            this.handler.setAlarm(
//                    System.currentTimeMillis() + (30 * 1000),
//                    1, 1,
//                    MainActivity.class
//            );
        });
        cardAlarm.setOnClickListener((e) -> {
            Intent i = new Intent(getActivity(), AlarmRaiseActivity.class);
            getActivity().startActivity(i);
        });
        cardNotify.setOnClickListener((e) -> {
            AlarmNotification notification = new AlarmNotification();
            notification.push(getActivity(), 0);
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
}
