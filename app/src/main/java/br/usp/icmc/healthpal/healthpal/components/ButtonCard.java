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

public class ButtonCard extends ConstraintLayout {
    Context context;

    public LinearLayout layout;
    public TextView iconText;
    public TextView labelText;

    public ButtonCard(Context context) {
        this(context, null);
    }

    @SuppressLint("ResourceAsColor")
    public ButtonCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.button_card, this, true);
        }

        this.layout = this.findViewById(R.id.button_card_layout);
        this.iconText = this.findViewById(R.id.button_card_icon_text);
        this.labelText = this.findViewById(R.id.button_card_label_text);

        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.ButtonCard,
                0, 0
        );
        String icon = a.getString(R.styleable.ButtonCard_iconText);
        String label = a.getString(R.styleable.ButtonCard_labelText);
        int fontTextColor = a.getColor(R.styleable.ButtonCard_textColor, R.color.colorText);
        a.recycle();

        this.setTextColor(fontTextColor);
        this.setIcon(icon);
        labelText.setText(label);
    }

    private void setTextColor(int fontTextColor) {
        this.iconText.setTextColor(fontTextColor);
        this.labelText.setTextColor(fontTextColor);
    }

    public void setColor(int color) {
        this.layout.setBackgroundColor(color);
    }

    public void setIcon(String icon) {
        this.iconText.setText(icon);
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
