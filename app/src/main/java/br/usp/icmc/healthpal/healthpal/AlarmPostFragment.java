package br.usp.icmc.healthpal.healthpal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import br.usp.icmc.healthpal.healthpal.R;
import br.usp.icmc.healthpal.healthpal.components.ButtonCard;

public class AlarmPostFragment extends Fragment {
    private TextView well, ok, bad;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alarm_post, container, false);
    }

    @Nullable
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        well = getView().findViewById(R.id.tv_feeling_well_icon);
        ok = getView().findViewById(R.id.tv_feeling_ok_icon);
        bad = getView().findViewById(R.id.tv_feeling_bad_icon);

        well.setOnClickListener((e) -> {
            getActivity().finish();
        });
        ok.setOnClickListener((e) -> {
            getActivity().finish();
        });
        bad.setOnClickListener((e) -> {
            getActivity().finish();
        });
    }
}
