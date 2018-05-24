package br.usp.icmc.healthpal.healthpal.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.usp.icmc.healthpal.healthpal.R;

public class DashboardCard extends ConstraintLayout {
    Context context;
    public DashboardCard(Context context) {
        this(context, null);
    }

    @SuppressLint("ResourceAsColor")
    public DashboardCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        String iconText, labelText;
        int bgColor;
        TypedArray a = context.obtainStyledAttributes(
                attrs,
                R.styleable.DashboardCard,
                0,
                0
        );
        iconText = a.getString(R.styleable.DashboardCard_iconText);
        labelText = a.getString(R.styleable.DashboardCard_labelText);
        bgColor = a.getColor(R.styleable.DashboardCard_bgColor, R.color.colorAccent);
        a.recycle();

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.dashboard_card, this, true);
        }

        LinearLayout layout = this.findViewById(R.id.dashboard_card_layout);
        IconTextView icon = this.findViewById(R.id.dashboard_card_icon);
        TextView label = this.findViewById(R.id.dashboard_card_text);

        layout.setBackgroundColor(bgColor);
        icon.setText(iconText);
        label.setText(labelText);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int squareLen = width;
        if (height > width) {
            squareLen = height;
        }
        setMeasuredDimension(
                MeasureSpec.makeMeasureSpec(squareLen, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(squareLen, MeasureSpec.EXACTLY)
        );
    }
}
