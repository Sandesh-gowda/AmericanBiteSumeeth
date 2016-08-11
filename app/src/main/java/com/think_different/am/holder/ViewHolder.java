package com.think_different.am.holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.think_different.am.R;
import com.think_different.am.helper.RecyclerView_OnClickListener;
import com.think_different.am.util.AmericanBiteTextView;


/**
 * Created by admin on 01/07/16.
 */
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

public TextView itemDescription, itemCost, itemName, pButton, mButton;
    public  TextView itemTotal;
    public AmericanBiteTextView itemQuantity ;
    public ImageView itemImage  ;
    public ImageView itemSubtract  , itemAdd ;
    public CardView cardView ;
    private RecyclerView_OnClickListener.OnClickListener onClickListener;
    //public Button pButton, mButton;


    public ViewHolder(View view){
        super(view);

        //this.itemCost = (TextView) view.findViewById(R.id.text_cost);
        // new card view
        this.itemCost = (AmericanBiteTextView) view.findViewById(R.id.price);
        this.itemDescription = (TextView) view.findViewById(R.id.item_discrp);
        //this.itemName = (TextView) view.findViewById(R.id.item_name);
        // new card view
        this.itemName = (AmericanBiteTextView) view.findViewById(R.id.tv_title);

      // this.itemImage = (ImageView) view.findViewById(R.id.item_image);

        // new card view
         this.itemImage = (ImageView) view.findViewById(R.id.iv_photo);

       //this.cardView = (CardView) view.findViewById(R.id.item_card);
      // new card view
        this.cardView = (CardView) view.findViewById(R.id.card_view_item);

       //this.itemTotal = (TextView)view.findViewById(R.id.totalNumber);
    //   this.pButton =(TextView)view.findViewById(R.id.plus_button);
  //    this.mButton = (TextView)view.findViewById(R.id.minus_button);
        this.itemSubtract = (ImageView) view.findViewById(R.id.subtract);
        this.itemAdd = (ImageView) view.findViewById(R.id.plus);

        //item quantity
        this.itemQuantity = (AmericanBiteTextView) view.findViewById(R.id.quantity);


//this is new card interface
this.cardView.setOnClickListener(this);
//        this.pButton.setOnClickListener(this);
    //    this.mButton.setOnClickListener(this);

        // button click for plus and minus
        this.itemAdd.setOnClickListener(this);
        this.itemSubtract.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (onClickListener != null) {
            onClickListener.OnItemClick(view, getAdapterPosition());

        }
    }


    // Setter for listener
    public void setClickListener(
            RecyclerView_OnClickListener.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
