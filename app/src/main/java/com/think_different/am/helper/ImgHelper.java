package com.think_different.am.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.squareup.picasso.Picasso;
import com.think_different.am.R;

/**
 * Created by admin on 01/07/16.
 */
public class ImgHelper {

    public static Picasso picasso;
    private static boolean isPicassoEnabled;


    public static void loadImage(Context ctx, String imageUrl, final ImageView imageView) {
//        if (isPicassoEnabled) {
//            picasso.load(imageUrl).into(imageView);
//        } else {
//            Glide.with(ctx).load(imageUrl).into(imageView);
//        }
        loadImage(ctx, imageUrl, R.drawable.am, R.drawable.am, imageView);
    }

    public static void loadImage(Context ctx, String imageUrl, int placeHolder, int errorPlaceHolder, final ImageView imageView) {
        if (isPicassoEnabled) {
            picasso.load(imageUrl).placeholder(placeHolder).error(errorPlaceHolder).into(imageView);
        } else {
            Glide.with(ctx).load(imageUrl).placeholder(placeHolder).error(errorPlaceHolder).into(imageView);
        }
    }
}
