package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

      holder.db.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {
              if (snapshot.exists()){
                String string =  snapshot.getValue().toString();
                holder.name.setText(string.toUpperCase());
              } else {
                  Toast.makeText(context, "Oops, Error", Toast.LENGTH_SHORT).show();
              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

          }
      });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView incorrect, correct, name;
        DatabaseReference db;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            db = FirebaseDatabase.getInstance("https://mathgame-25a50-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("name");
            incorrect = itemView.findViewById(R.id.resultsIncorrect);
            correct = itemView.findViewById(R.id.resultsCorrect);
            name = itemView.findViewById(R.id.userName);


        }
    }
}
