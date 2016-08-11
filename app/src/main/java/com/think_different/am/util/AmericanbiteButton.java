package com.think_different.am.util;

import android.widget.Button;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


/**
 * Created by admin on 21/07/16.
 */
public class AmericanbiteButton extends Button {


       private boolean nextAction = false;

        public AmericanbiteButton(Context paramContext)
        {
            super(paramContext);
            init(paramContext, null);
        }

        public AmericanbiteButton(Context paramContext, AttributeSet paramAttributeSet)
        {
            super(paramContext, paramAttributeSet);
            init(paramContext, paramAttributeSet);
        }

        public AmericanbiteButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
        {
            super(paramContext, paramAttributeSet, paramInt);
            init(paramContext, paramAttributeSet);
        }

        public void hideKeyboard(View paramView)
        {((InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(paramView.getWindowToken(), 0);

        }

        public void init(Context paramContext, AttributeSet paramAttributeSet)
        {
            setClickable(true);
            if (paramAttributeSet != null)
                setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/novasemibold.ttf"));
        }
    }

