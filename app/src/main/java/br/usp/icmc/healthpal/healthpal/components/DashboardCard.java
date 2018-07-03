package br.usp.icmc.healthpal.healthpal.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.usp.icmc.healthpal.healthpal.R;

public class DashboardCard extends ConstraintLayout {
    Context context;

    public LinearLayout layout;
    public TextView icon;
    public TextView title, text, footer;
    public AutoCompleteTextView medicines;

    public DashboardCard(Context context) {
        this(context, null);
    }

    @SuppressLint("ResourceAsColor")
    public DashboardCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.dashboard_card, this, true);
        }

        this.layout = this.findViewById(R.id.dashboard_card_layout);
        this.icon = this.findViewById(R.id.dashboard_card_icon);
        this.title = this.findViewById(R.id.dashboard_card_title);
        this.text = this.findViewById(R.id.dashboard_card_text);
        this.footer = this.findViewById(R.id.dashboard_card_footer);

        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.DashboardCard,
                0, 0
        );
        String iconText = a.getString(R.styleable.DashboardCard_iconText);
        String labelTitle = a.getString(R.styleable.DashboardCard_labelTitle);
        String labelText = a.getString(R.styleable.DashboardCard_labelText);
        String labelFooter = a.getString(R.styleable.DashboardCard_labelFooter);
        int fontTextColor = a.getColor(R.styleable.DashboardCard_textColor, R.color.colorText);
        a.recycle();

        this.setTextColor(fontTextColor);
        this.setIcon(iconText);
        title.setText(labelTitle);
        text.setText(labelText);
        footer.setText(labelFooter);
    }

    private void setTextColor(int fontTextColor) {
        this.title.setTextColor(fontTextColor);
        this.text.setTextColor(fontTextColor);
        this.footer.setTextColor(fontTextColor);
        this.icon.setTextColor(fontTextColor);
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
