package com.example.pjotrvanhulst;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;

public class Choose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);
    }

    private InputStream inputStory;

    public void make(View view) {
        // save the clicked button
        Button button = (Button) view;
        // if clicked on buttons for make a story
        switch(button.getId()) {
            case(R.id.simple):
                // get story from which button choose
                inputStory = this.getResources().openRawResource(R.raw.madlib0_simple);
                break;
            case(R.id.tarzan):
                inputStory = this.getResources().openRawResource(R.raw.madlib1_tarzan);
                break;
            case(R.id.university):
                inputStory = this.getResources().openRawResource(R.raw.madlib2_university);
                break;
            case(R.id.clothes):
                inputStory = this.getResources().openRawResource(R.raw.madlib3_clothes);
                break;
            case(R.id.dance):
                inputStory = this.getResources().openRawResource(R.raw.madlib4_dance);
                break;
        }
        // instantiate the story
        Story chosenStory = new Story(inputStory);
        // create new intent to direct user to second activity with the chosen story
        Intent activityTwo = new Intent(Choose.this, ActivityTwo.class);
        activityTwo.putExtra("story", chosenStory);
        startActivity(activityTwo);
    }
}