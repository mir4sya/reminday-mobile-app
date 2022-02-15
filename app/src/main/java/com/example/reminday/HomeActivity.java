package com.example.reminday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Button btnlogout, btnSearch;
    public CardView information, reminder, location, graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        btnlogout = findViewById(R.id.btnlogout);
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        information = (CardView) findViewById(R.id.information);
        reminder = (CardView) findViewById(R.id.reminder);
        location = (CardView) findViewById(R.id.location);
        graph = (CardView) findViewById(R.id.graph);

        information.setOnClickListener(this);
        reminder.setOnClickListener(this);
        location.setOnClickListener(this);
        graph.setOnClickListener(this);
    }

    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null)
        {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
        }
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
    }

    public void openActivity2(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.information:
                i = new Intent(this,Information.class);
                startActivity(i);
                break;

            case R.id.reminder:
                i = new Intent(this,Reminder.class);
                startActivity(i);
                break;

            case R.id.location:
                i = new Intent(this,MapsActivity.class);
                startActivity(i);
                break;

            case R.id.graph:
                i = new Intent(this,Graphical.class);
                startActivity(i);
                break;

        }

    }
}