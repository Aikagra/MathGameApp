package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Result> list;

    public MyAdapter(Context context, ArrayList<Result> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item_result, parent, false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Result result = list.get(position);

        String s = Long.toString(result.getCorrect());
        String f = Long.toString(result.getIncorrect());
        holder.incorrect.setText("Incorrect: " + s);
        holder.correct.setText("Correct: " + f);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView incorrect;
        TextView correct;
        TextView incorrectAdd;
        TextView correctAdd;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            incorrect = itemView.findViewById(R.id.resultsIncorrect);
            correct = itemView.findViewById(R.id.resultsCorrect);
        }
    }
}
