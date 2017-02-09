package com.pex.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

/**
 * Created by avikaljain on 28/11/16.
 */
public class Font {

    private AssetManager mngr;

    public Font(Context context) {
        mngr = context.getAssets();
    }

    public enum AssetTypefaces {
        OpenSansBold,
        OpenSansItalic,
        OpenSanslight,
        OpenSansRegular,
        OpenSansSemiBold,

    }

    public Typeface getTypeface(AssetTypefaces font) {
        Typeface tf = null;
        switch (font) {
            case OpenSansBold:
                tf = Typeface.createFromAsset(mngr, "fonts/opensans_bold.ttf");
                break;
            case OpenSansItalic:
                tf = Typeface.createFromAsset(mngr, "fonts/opensans_italic.ttf");
                break;

            case OpenSanslight:
                tf = Typeface.createFromAsset(mngr, "fonts/opensans_light.ttf");
                break;

            case OpenSansRegular:
                tf = Typeface.createFromAsset(mngr, "fonts/opensans_regular.ttf");
                break;
            case OpenSansSemiBold:
                tf = Typeface.createFromAsset(mngr, "fonts/opensans_semibold.ttf");
                break;

            default:
                tf = Typeface.DEFAULT;
                break;
        }
        return tf;
    }
}
