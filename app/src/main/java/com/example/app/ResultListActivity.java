package com.example.app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView userName;
    private Toolbar toolbar;
    TextView correctIncorrectText;
    ImageButton backToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        correctIncorrectText = findViewById(R.id.correctIncorrectTv);
        backToolbar = findViewById(R.id.backButtonToolbar);
        toolbar = findViewById(R.id.myToolBar);
        recyclerView = findViewById(R.id.resultListRecycler);
         db = FirebaseDatabase.getInstance("https://mathgame-25a50-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Results");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userName = findViewById(R.id.userNameDisplay);

        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultListActivity.this, DashboardActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ResultListActivity.this, correctIncorrectText, ViewCompat.getTransitionName(correctIncorrectText));
                startActivity(intent, options.toBundle());
            }
        });



        setSupportActionBar(toolbar);

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