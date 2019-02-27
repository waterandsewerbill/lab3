package com.example.lab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.lab3.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        button1 = (Button)findViewById(R.id.r5Button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });


    }
    public void openActivity2(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);

    }

}
