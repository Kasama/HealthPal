package br.usp.icmc.healthpal.healthpal;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.usp.icmc.healthpal.healthpal.components.ButtonCard;

public class AlarmRaiseActivity extends AppCompatActivity {
    private Vibrator vibrator;

    private ButtonCard bcMedicineTaken;
    private Animation shake;
    private TextView icon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_raise);

        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

        bcMedicineTaken = findViewById(R.id.button_medicine_taken);
        bcMedicineTaken.setOnClickListener((e) -> {
            this.vibrateStop();
            finish();
        });

        icon = findViewById(R.id.action_card_icon);
        icon.startAnimation(shake);

        this.vibrateStart();
    }

    private void vibrateStart() {
        long[] pattern = {0, 1000, 1000};
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) vibrator.vibrate(pattern, 0);
    }

    private void vibrateStop() {
        if (vibrator != null) vibrator.cancel();
    }
}
