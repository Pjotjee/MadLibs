package com.example.pjotrvanhulst;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

public class ActivityThree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);;

        // retrieve the story from ActivityTwo
        Intent intent = getIntent();
        String retrievedStory = intent.getStringExtra("fullstory");

        // access textview in the layout to set the text it
        TextView story = findViewById(R.id.textStory);
        story.setText(retrievedStory);

        // implement scrolling
        story.setMovementMethod(new ScrollingMovementMethod());
    }

    // reset button method for redirection back to MainActivity
    public void reSet(View view) {
        Intent reset = new Intent(ActivityThree.this, MainActivity.class);
        startActivity(reset);
    }

    // set the backbutton redirection to MainActivity
    @Override
    public void onBackPressed() {
        Intent goback = new Intent(this, MainActivity.class);
        startActivity(goback);
    }
}