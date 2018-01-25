package com.echallansystem;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import java.util.HashMap;
import java.util.Map;
import android.os.Bundle;
import android.widget.TextView;


public class Font extends TextView {

    public static final String ATTRIBUTE_TTF_KEY = "ttf_name";

    public static final String ATTRIBUTE_SCHEMA = "http://schemas.android.com/apk/lib/com.echallansystem.Splash";
    private Context mContext;

    private String ttfName;

    String TAG = getClass().getName();

    public Font(Context context) {
        super(context);
        this.mContext = context;
    }

    public Font(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;

        // Typeface.createFromAsset doesn't work in the layout editor.
        // Skipping...
        if (isInEditMode()) {
            return;
        }

        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            this.ttfName = attrs.getAttributeValue(ATTRIBUTE_SCHEMA,
                    ATTRIBUTE_TTF_KEY);

            if (null != ttfName)
                init();
        }

    }

    private static Map<String, Typeface> TYPEFACE = new HashMap<String, Typeface>();

    public static Typeface getFonts(Context context, String fontName) {
        Typeface typeface = TYPEFACE.get(fontName);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "font/"
                    + fontName);
            TYPEFACE.put(fontName, typeface);
        }
        return typeface;
    }
    public Font(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    private void init() {
        Typeface font = getFonts(mContext, ttfName);
        if (null != font)
            setTypeface(font);
    }


    @Override
    public void setTypeface(Typeface tf) {

        super.setTypeface(tf);
    }

}
