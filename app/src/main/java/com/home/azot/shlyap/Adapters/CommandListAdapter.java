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
import com.home.azot.shlyap.Team;

import java.util.ArrayList;

/**
 * Created by Женя on 13.02.2015.
 */
public class CommandListAdapter extends RecyclerView.Adapter<CommandListAdapter.ViewHolder> {

    private ArrayList<Team> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView user1Name;
        public TextView user2Name;
        public TextView commandName;

        public ViewHolder(View v) {
            super(v);
            user1Name = (TextView) v.findViewById(R.id.user_one);
            user2Name = (TextView) v.findViewById(R.id.user_two);
            commandName = (TextView) v.findViewById(R.id.title_command);
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    View newc = (View)v.getParent();
//                    CheckBox cb = (CheckBox) v.findViewById(R.id.theme_switch);
//                    cb.setChecked(!cb.isChecked());
//                    StaticHolder.userSelectActivityInstance.removeItem(v);
//                }
//            });
        }
    }

    public CommandListAdapter(ArrayList<Team> dataset) {
        mDataset = dataset;
    }

    @Override
    public CommandListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.command_raw, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.user1Name.setText(mDataset.get(position).getGameUser(Team.FIRST_USER).getUserName());
        holder.user2Name.setText(mDataset.get(position).getGameUser(Team.SECOND_USER).getUserName());
        holder.commandName.setText("Команда №"+String.valueOf(position-1));
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