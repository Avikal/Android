package com.cdn.techquiq.consumer.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.cdn.techquiq.consumer.Utils.Utils;


/**
 * Created by kajalsoni on 28/1/17.
 * custom TextView to show Regular style Text
 */
public class RegularTextView extends TextView {

public static Typeface robotoRegular, robotoBold, robotoLight, robotoMedium;	

    public RegularTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RegularTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {

            Typeface tf = null;
            if (Utils.getLocale().equalsIgnoreCase("EN")) {
                tf = Utils.robotoRegular;
                if (tf != null) {
                    setTypeface(tf);
                }
            }
        }
    }

 public static void loadFontFiles(Context mContext) {
        robotoRegular = Typeface.createFromAsset(mContext.getAssets(), "roboto_regular.ttf");
        robotoBold = Typeface.createFromAsset(mContext.getAssets(), "roboto_black.ttf");
        robotoLight = Typeface.createFromAsset(mContext.getAssets(), "roboto_light.ttf");
        robotoMedium = Typeface.createFromAsset(mContext.getAssets(), "roboto_medium.ttf");
    }

    public static void hideKeyBoard(Context mContext) {
        try {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(((Activity) mContext).getWindow().getCurrentFocus()
                    .getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
