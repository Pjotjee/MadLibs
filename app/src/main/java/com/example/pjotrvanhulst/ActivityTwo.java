package com.example.pjotrvanhulst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity {

    Button buttonOK ;
    EditText editText;
    TextView wordsLeft;
    TextView textType;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        // get the instantiated story
        Intent intent = getIntent();
        Story getStory = (Story) intent.getSerializableExtra("story");

        // show what kind of word type needs to be filled in
        editText = findViewById(R.id.editText);
        textType = findViewById(R.id.textView4);
        String wordType = getStory.getNextPlaceholder();
        editText.setHint(wordType);
        textType.setText("Please fill in an " + wordType);

        // display how many words are left
        wordsLeft = findViewById(R.id.textWordsleft);
        int count = getStory.getPlaceholderCount();
        wordsLeft.setText("There are " + count + " words left!");

        // set clicklistener on button in layout
        buttonOK = findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new saveWords());

        // retrieve the sharedPreferences to store the words
        int count1 = getStory.getPlaceholderRemainingCount();
        editor = getSharedPreferences("settings", MODE_PRIVATE).edit();
        editor.putInt("count", count1);
        editor.apply();

        // check and set count
        SharedPreferences prefscount = getSharedPreferences("settings", MODE_PRIVATE);
        int savedCount = prefscount.getInt("count", count1);

        // update textview
        if (savedCount == 1) {
            wordsLeft.setText("There is " + savedCount + " word left!");
        }
        else {
            wordsLeft.setText("There are " + savedCount + " words left!");
        }
    }

    // class saves the words typed in
    private class saveWords implements Button.OnClickListener {
        // get the instantiated story
        Intent intent = getIntent();
        Story getStory = (Story) intent.getSerializableExtra("story");

        @Override
        public void onClick(View v) {

            // initialize the parameters
            editText = findViewById(R.id.editText);
            buttonOK = findViewById(R.id.buttonOK);
            String userInput = editText.getText().toString();
            editor = getSharedPreferences("settings", MODE_PRIVATE).edit();

            // save user input in SharedPreferences
            editor.putString("word", userInput);
            editor.apply();

            // get the user input from SharedPreferences
            SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
            String savedWord = prefs.getString("word", "");

            // fill in the story
            getStory.fillInPlaceholder(savedWord);

            // let user know what kind of word type needs to be filled in
            editText = findViewById(R.id.editText);
            textType = findViewById(R.id.textView4);
            String wordType = getStory.getNextPlaceholder();
            editText.setHint(wordType);
            textType.setText("Please fill in an " + wordType);

            // count how many words to fill
            int count = getStory.getPlaceholderRemainingCount();
            editor.putInt("count", count);
            editor.apply();

            // retrieve number of words
            SharedPreferences prefscount = getSharedPreferences("settings", MODE_PRIVATE);
            int savedCount = prefscount.getInt("count", 0);

            // set the textview to let user know how many words are left
            if (savedCount == 1) {
                wordsLeft.setText("There is " + savedCount + " word left!");
            }
            else {
                wordsLeft.setText("There are " + savedCount + " words left!");
            }

            // clear the textfield after submitting
            editText.getText().clear();

            // if all words in story have been filled, go to ActivityThree
            if (getStory.isFilledIn() == true) {
                // create the intent for activity_three
                Intent activityThree = new Intent(ActivityTwo.this, ActivityThree.class);
                activityThree.putExtra("fullstory", getStory.toString());
                startActivity(activityThree);
            }
        }
    }
}