package com.example.lab3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.lab3.R.id.r2EditText;
import static com.example.lab3.R.layout.*;
import static com.example.lab3.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    //private Button button1;
    private static final String SHARED_PREFS = "sharedPrefs";
    private SharedPreferences prefs;
    private EditText textField;
    private static final String TEXT = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        textField = (EditText)findViewById(r2EditText);

        prefs = getSharedPreferences("DefaultEmail", Context.MODE_PRIVATE);

        String emailString = prefs.getString("DefaultEmail", "r@b.com");

        textField.setText(emailString);

        Button button1 = (Button)findViewById(R.id.r5Button);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        loadData();
    }

    public void openActivity2(){
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(SHARED_PREFS, textField.getText().toString());
        startActivity(intent);

    }
    private void saveData(){
        String saveData = textField.getText().toString();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TEXT, saveData);
        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();

    }
    private void loadData() {
        String savedString = prefs.getString(TEXT, "");
        textField.setText(savedString);
    }

    protected void onPause() {
        super.onPause();
        saveData();
    }
}
