package com.think_different.am.util;

import android.widget.TextView;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by admin on 20/07/16.
 */
public class AmericanBiteTextView extends TextView {

     public AmericanBiteTextView(Context paramContext)
        {
            super(paramContext);
            init(paramContext, null);
        }

        public AmericanBiteTextView(Context paramContext, AttributeSet paramAttributeSet)
        {
            super(paramContext, paramAttributeSet);
            init(paramContext, paramAttributeSet);
        }

        public AmericanBiteTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
        {
            super(paramContext, paramAttributeSet, paramInt);
            init(paramContext, paramAttributeSet);
        }

        public void init(Context paramContext, AttributeSet paramAttributeSet)
        {
            if (paramAttributeSet != null)
                setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/novaregular.ttf"));
        }
    }

