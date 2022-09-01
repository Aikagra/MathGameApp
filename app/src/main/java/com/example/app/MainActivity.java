package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tvNum1, tvNum2, tvAns, tvResult;
    ImageButton backAdd;
    TextView addition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addition = findViewById(R.id.additionDashboard);
        tvNum1 = findViewById(R.id.tv_num_1);
        tvNum2 = findViewById(R.id.tv_num_2);
        tvAns = findViewById(R.id.tv_ans);
        tvResult = findViewById(R.id.tv_result);
        backAdd = findViewById(R.id.backAddition);



        run_reset();


        backAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, addition, ViewCompat.getTransitionName(addition));
                startActivity(intent, options.toBundle());
            }
        });

    }

    void run_reset(){
        Random myRandom = new Random();

        int num1 = myRandom.nextInt(101);
        int num2 = myRandom.nextInt(101);
        tvNum1.setText(""+num1);
        tvNum2.setText(""+num2);
        tvResult.setText("");
        tvAns.setText("");
    }

        void clear(){
            tvAns.setText("");
            tvResult.setText("");
        }

    void printAns(String a){
        String text = tvAns.getText().toString();
        tvAns.setText(text+a);
    }

    public void one(View view) {
        printAns("1");
    }

    public void two(View view) {
        printAns("2");
    }

    public void three(View view) {
        printAns("3");
    }

    public void four(View view) {
        printAns("4");
    }

    public void five(View view) {
        printAns("5");
    }

    public void six(View view) {
        printAns("6");
    }

    public void seven(View view) {
        printAns("7");
    }

    public void eight(View view) {
        printAns("8");
    }

    public void nine(View view) {
        printAns("9");
    }

    public void zero(View view) {
        printAns("0");
    }

    public void clear(View view) {
        clear();
    }

    public void submit(View view) {
        int num1 = Integer.parseInt(tvNum1.getText().toString());
        int num2 = Integer.parseInt(tvNum2.getText().toString());
        int ans = num1 + num2;
        int get_user_ans = Integer.parseInt(tvAns.getText().toString());

        if (ans == get_user_ans){
            tvResult.setText("Correct!");
        }
        else {
            tvResult.setText("Incorrect!");
        }



    }





    public void next(View view) {
        run_reset();
    }


}

