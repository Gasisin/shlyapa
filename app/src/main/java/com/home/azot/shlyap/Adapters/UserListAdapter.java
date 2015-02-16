package com.home.azot.shlyap.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.home.azot.shlyap.GameUser;
import com.home.azot.shlyap.Helpers.StaticHolder;
import com.home.azot.shlyap.R;

import java.util.ArrayList;

/**
 * Created by Женя on 13.02.2015.
 */
public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private ArrayList<GameUser> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;

        public ViewHolder(View v) {
            super(v);
            userName = (TextView) v.findViewById(R.id.user_name_in_row);
           v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    View newc = (View)v.getParent();
                    CheckBox cb = (CheckBox) v.findViewById(R.id.theme_switch);
                    cb.setChecked(!cb.isChecked());
                    StaticHolder.userSelectActivityInstance.removeItem(v);
                }
            });
        }
    }

    public UserListAdapter(ArrayList<GameUser> dataset) {
        mDataset = dataset;
    }

    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_raw, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.userName.setText(mDataset.get(position).getUserName());
//        holder.userName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                StaticHolder.userSelectActivityInstance.removeItem(holder);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}