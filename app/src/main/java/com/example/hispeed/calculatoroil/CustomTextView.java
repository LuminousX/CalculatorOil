package com.example.hispeed.calculatoroil;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Hispeed on 18/9/2560.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {


    public CustomTextView(Context context) {
        super(context);
        TypeText();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypeText();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypeText();
    }
    public void TypeText(){
//        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "droidsans.ttf");
//        setTypeface(typeface);
    }

}
