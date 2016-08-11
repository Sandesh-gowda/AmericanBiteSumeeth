package com.think_different.am.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.think_different.am.OrderActivity;
import com.think_different.am.R;
import com.think_different.am.helper.ImgHelper;
import com.think_different.am.helper.OnitemClickLIstener;
import com.think_different.am.helper.RecyclerView_OnClickListener;
import com.think_different.am.holder.ViewHolder;
import com.think_different.am.model.Menu;

import java.util.ArrayList;

/**
 * Created by admin on 01/07/16.
 */
public class MenuAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final LayoutInflater mInflater;
    private ArrayList<Menu> menuList;
    private Context context;
    private OnitemClickLIstener onItemClickListener;
    int count = 0;

    public MenuAdapter(ArrayList<Menu> menuList, Context context) {

        this.menuList = menuList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {


        /*TODO:CardViewInialize here */

        /* change the test_card_layout */

       // ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.test_card_layout, viewGroup, false);
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(R.layout.f_r_card, viewGroup, false);

        return new ViewHolder(mainGroup);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        Log.d("MenuAdapter", "onBindViewHolder : " + position);
        final Menu model = menuList.get(position);

      //  holder.itemDescription.setText(model.getDescription());
        holder.itemCost.setText(model.getMenu_price());
        holder.itemName.setText(model.getMenuItemName());
        ImgHelper.loadImage(context, model.getImage(), holder.itemImage);
      //  holder.itemTotal.setText(String.valueOf(model.count));  //this the value which will show when we press plus or minus

        holder.itemQuantity.setText(String.valueOf(model.count));
        holder.setClickListener(new RecyclerView_OnClickListener.OnClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

                switch (view.getId()) {
                   // case R.id.plus_button:
                    case R.id.plus:

                        if (onItemClickListener != null) {
                            model.count++;
                            notifyDataSetChanged();
                            int total = 0;
                            int count = 0;
                            for (Menu menu : menuList) {
                                if (menu.count > 0) {
                                    total = total + (menu.count * Integer.parseInt(menu.getMenu_price()));
                                    count = count + menu.count;
                                }
                            }
                            Snackbar snackbar = Snackbar.make(view, count + "\t|\t" + String.valueOf(total) + ".00\tRs", Snackbar.LENGTH_INDEFINITE);
                            View snacbar = snackbar.getView();
                            TextView snac_tx = (TextView) snacbar.findViewById(android.support.design.R.id.snackbar_text);
                            snac_tx.setCompoundDrawablesWithIntrinsicBounds(R.drawable.basket, 0, 0, 0);
                            snackbar.setAction("Proceed", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //This will take to list of item in the shopping cart.
                                    Toast.makeText(context, "Moving to next activity were cart activity", Toast.LENGTH_SHORT).show();

                                     // this is where the code of array list will go shopping cart
                                    for (Menu m : menuList) {

                                        if (m.count>0){
                                          Log.v("List:",m.count+":"+m.getId()+":"+m.getMenuItemName()+":");
                                          //  Intent intent = new Intent(context, OrderActivity.class);
                                          //   intent.putExtra("mylist", myList);
                                          //  context.startActivity(intent);
                                        }
                                    }


                                    Intent intent = new Intent(context, OrderActivity.class);
                                    context.startActivity(intent);
                                }
                            });
                            snac_tx.setGravity(Gravity.CENTER);
                            snackbar.show();
//                            Toast.makeText(context,"Plus Clicked",Toast.LENGTH_LONG).show();

                        }

                        break;
                  //  case R.id.minus_button:
                    case R.id.subtract:
                        if (onItemClickListener != null) {
                            if (model.count > 0) {
                                model.count--;
                                notifyDataSetChanged();
                                int total = 0;
                                int count = 0;
                                for (Menu menu : menuList) {
                                    if (menu.count > 0) {
                                        total = total + (menu.count * Integer.parseInt(menu.getMenu_price()));
                                        count = count + menu.count;
                                    }
                                }

                                Snackbar snackbar = Snackbar.make(view, count + "\tRs:" + String.valueOf(total), Snackbar.LENGTH_INDEFINITE);
                                View snacbar = snackbar.getView();
                                TextView snac_tx = (TextView) snacbar.findViewById(android.support.design.R.id.snackbar_text);
                               // TextView snac_tx = (TextView) snacbar.findViewById(android.support.design.R.id.snackbar_text);
                                snac_tx.setCompoundDrawablesWithIntrinsicBounds(R.drawable.basket, 0, 0, 0);

                                snackbar.setAction("Proceed", new View.OnClickListener() {

                                    @Override
                                    public void onClick(View v) {
                                        //This will take to list of item in the shopping cart.
                                        Toast.makeText(context, "Moving to next activity were cart activity", Toast.LENGTH_SHORT).show();


                                       //   this is where the code of array list will go shopping cart
                                        for (Menu m : menuList) {

                                            if (m.count>0){


                                                Log.v("List_lessbutton:",m.count+":"+m.getId()+":"+m.getMenuItemName()+":");


                                                //  Intent intent = new Intent(context, OrderActivity.class);

                                                //   intent.putExtra("mylist", myList);

                                                //  context.startActivity(intent);

                                            }
                                        }
                                        Intent intent = new Intent(context, OrderActivity.class);
                                        context.startActivity(intent);

                                    }
                                });

                                snac_tx.setGravity(Gravity.CENTER);
                                snackbar.show();
                            }

                        }

                }
            }
        });
    }


    public void increment() {


    }


    @Override
    public int getItemCount() {
        return (null != menuList ? menuList.size() : 0);
    }

    public void setOnItemClickListener(OnitemClickLIstener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
