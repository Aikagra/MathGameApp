package com.example.app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResultListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference db;
    MyAdapter myAdapter;
    ArrayList<Result> list;
    TextView userName, additionResultCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        recyclerView = findViewById(R.id.resultListRecycler);
        db = FirebaseDatabase.getInstance("https://mathgame-25a50-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Results").child("Subtraction");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userName = findViewById(R.id.userNameDisplay);
        additionResultCorrect = findViewById(R.id.additionResultsCorrect);

        list = new ArrayList<>();

        myAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Result result = dataSnapshot.getValue(Result.class);
                    list.add(result);
                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}