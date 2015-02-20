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
public class CommandResultAdapter extends RecyclerView.Adapter<CommandResultAdapter.ViewHolder> {

    private ArrayList<Team> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView user1Name;
        public TextView user2Name;
        public TextView user1Timer;
        public TextView user2Timer;
        public TextView commandNumber;
        public View user1Click;
        public View user2Click;
        public CheckBox check1User;
        public CheckBox check2User;

        public ViewHolder(View v) {
            super(v);
            user1Name = (TextView) v.findViewById(R.id.user1_name_in_row);
            user2Name = (TextView) v.findViewById(R.id.user2_name_in_row);
            user1Timer = (TextView) v.findViewById(R.id.user1_time_in_row);
            user2Timer = (TextView) v.findViewById(R.id.user2_time_in_row);
            commandNumber = (TextView) v.findViewById(R.id.command_number);
            user1Click = v.findViewById(R.id.user1_click);
            user2Click = v.findViewById(R.id.user2_click);
            check1User = (CheckBox) v.findViewById(R.id.user1_switch);
            check2User = (CheckBox) v.findViewById(R.id.user2_switch);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    View newc = (View)v.getParent();
//                    switch (v.getId()) {
//                        case R.id.user1_click:
//                            teamManager.addUserInTeam(teamManager.getUserById(1));
//                            setTextAboutCommand();
//                            break;
//                    }
//                    CheckBox cb = (CheckBox) v.findViewById(R.id.user1_switch);
//                    cb.setChecked(!cb.isChecked());
//                    StaticHolder.userSelectActivityInstance.removeItem(v);
                }
            });
        }
    }

    public CommandResultAdapter(ArrayList<Team> dataset) {
        mDataset = dataset;
    }

    @Override
    public CommandResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.command_result_raw, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Team currentTeam = mDataset.get(position);
        GameUser firstUser = currentTeam.getGameUser(Team.FIRST_USER);
        GameUser secondUser = currentTeam.getGameUser(Team.SECOND_USER);

        holder.user1Name.setText(firstUser.getUserName());
        holder.user2Name.setText(secondUser.getUserName());
        holder.commandNumber.setText("Команда №"+ currentTeam.teamNumber);


        if (firstUser.IsComplete()){
            holder.check1User.setChecked(true);
            holder.user1Timer.setText(firstUser.getUserTask().getTotalTime());
        }

        if (secondUser.IsComplete()){
            holder.check2User.setChecked(true);
            holder.user2Timer.setText(secondUser.getUserTask().getTotalTime());
        }



//        holder.user1Name.setText(mDataset.get(position).getUserName());
//        holder.user1Name.setTag(mDataset.get(position).getUserId());
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