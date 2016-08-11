package com.think_different.am.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.think_different.am.Ba;
import com.think_different.am.R;
import com.think_different.am.helper.OnitemClickLIstener;

/**
 * Created by admin on 02/07/16.
 */
public class DailyMenu extends Ba implements OnitemClickLIstener {
    private View view;
    String requ = "http://appshoppee.in/americanbyte/API/today_sp_mnu.php";
    public DailyMenu(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_list_fragment, container, false);
        initRecyclerView();
        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intiTask();
    }


    private void initRecyclerView() {
     //   progressBar = (ProgressBar) view.findViewById(R.id.my_stock_progressbar);
    //    empty_stock = (TextView) view.findViewById(R.id.empty_stock);
        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.menu_recycler_view);
       // recyclerView.setHasFixedSize(true);
       // recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 1));// Here 2 is no. of columns to be displayed

        recyclerView.setLayoutManager(layoutManager);
    }


    private void intiTask(){
        if (responseData == null) {
            callMenuItem(requ);
        } else {
            setData(responseData);
        }

    }

    @Override
    protected void setData(String response) {
        super.setData(response);
        if (adapter != null) {
            adapter.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(RecyclerView.Adapter adapter, View view, int position) {
        Toast.makeText(getContext(),"clicked on pluse and minus ",Toast.LENGTH_LONG).show();
    }
}
