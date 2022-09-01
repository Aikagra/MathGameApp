package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    TextView addition, subtraction;
    CardView additionCard, subtractionCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        addition = findViewById(R.id.additionDashboard);
        subtraction = findViewById(R.id.subtractionDashboard);
        additionCard = findViewById(R.id.additionCard);
        subtractionCard = findViewById(R.id.subtractionCard);

        additionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(DashboardActivity.this, addition, ViewCompat.getTransitionName(addition));
                startActivity(intent, options.toBundle());
            }
        });

        subtractionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, SubtractionActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(DashboardActivity.this, subtraction, ViewCompat.getTransitionName(subtraction));
                startActivity(intent, options.toBundle());
            }
        });

    }

    public void additionGame(View view) {

    }
}