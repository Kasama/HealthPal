package br.usp.icmc.healthpal.healthpal;

import android.content.Context;
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

public class AlarmAlertFragment extends Fragment {
    private Vibrator vibrator;

    private ButtonCard bcMedicineTaken;
    private Animation shake;
    private TextView icon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alarm_alert, container, false);
    }

    @Nullable
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);

        bcMedicineTaken = getView().findViewById(R.id.button_medicine_taken);
        bcMedicineTaken.setOnClickListener((e) -> {
            this.vibrateStop();
            getActivity().finish();
        });

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
