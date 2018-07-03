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

public class EmergencyCard extends ConstraintLayout{
    Context context;

    public LinearLayout layout;
    public TextView icon;
    public TextView footer;

    public EmergencyCard(Context context) {this(context, null);}

    @SuppressLint("ResourceAsColor")
    public EmergencyCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.emergency_card, this, true);
        }

        this.layout = this.findViewById(R.id.emergency_card_layout);
        this.icon = this.findViewById(R.id.emergency_card_icon);
        this.footer = this.findViewById(R.id.emergency_card_footer);

        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.EmergencyCard,
                0, 0
        );
        String iconText = a.getString(R.styleable.EmergencyCard_iconText);
        String labelFooter = a.getString(R.styleable.EmergencyCard_labelFooter);
        int fontTextColor = a.getColor(R.styleable.EmergencyCard_textColor, R.color.colorText);
        a.recycle();

        this.setTextColor(fontTextColor);
        this.setIcon(iconText);
        footer.setText(labelFooter);
    }

    private void setTextColor(int fontTextColor) {
        this.icon.setTextColor(fontTextColor);
        this.footer.setTextColor(fontTextColor);
    }

    public void setColor(int color) {
        this.layout.setBackgroundColor(color);
    }

    public void setIcon(String icon) {
        this.icon.setText(icon);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int width = getMeasuredWidth();
//        int height = getMeasuredHeight();
//        int squareLen = width;
//        if (height > width) {
//            squareLen = height;
//        }
//        setMeasuredDimension(
//                MeasureSpec.makeMeasureSpec(squareLen, MeasureSpec.EXACTLY),
//                MeasureSpec.makeMeasureSpec(squareLen, MeasureSpec.EXACTLY)
//        );
    }
}
