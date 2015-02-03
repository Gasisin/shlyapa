package com.home.azot.shlyap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private ArrayList<TaskWord> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView resultWordView;
        public TextView resultTimeView;

        public ViewHolder(View v) {
            super(v);
            resultWordView = (TextView) v.findViewById(R.id.recycler_word);
            resultTimeView = (TextView) v.findViewById(R.id.recycler_time);
        }
    }

    public ResultAdapter(ArrayList<TaskWord> dataset) {
        mDataset = dataset;
    }

    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.resultWordView.setText(mDataset.get(position).getWord());
        holder.resultTimeView.setText(String.valueOf(mDataset.get(position).getTime()));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}