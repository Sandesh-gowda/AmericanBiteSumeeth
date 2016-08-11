package com.think_different.am.util;

import android.widget.TextView;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by admin on 20/07/16.
 */
public class AmericanBiteTextViewBold extends TextView{





      public AmericanBiteTextViewBold(Context paramContext)
        {
            super(paramContext);
            init(paramContext, null);
        }

        public AmericanBiteTextViewBold(Context paramContext, AttributeSet paramAttributeSet)
        {
            super(paramContext, paramAttributeSet);
            init(paramContext, paramAttributeSet);
        }

        public AmericanBiteTextViewBold(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
        {
            super(paramContext, paramAttributeSet, paramInt);
            init(paramContext, paramAttributeSet);
        }

        public void init(Context paramContext, AttributeSet paramAttributeSet)
        {
            if (paramAttributeSet != null)
                setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/novabold.ttf"));
        }
    }

