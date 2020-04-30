package com.example.guessinggame;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText txtGuess;
    private Button btnGuess;
    private TextView lblOutput;
    private int theNumber;
    private int triesLeft = 7;
    private TextView txtTries;
    private Button btnReset;

    public void checkGuess() {
        //Get the number which user entered
        String theirNumber = txtGuess.getText().toString();
        String message = "";
        String tries = "";
        triesLeft--;
        try {
            int guess = Integer.parseInt(theirNumber);

            if(guess > theNumber && triesLeft>0){//too high
                message = guess + " was too high. Try again";
                lblOutput.setText(message);
                tries = triesLeft + " tries left. Watch out!";
                txtTries.setText((tries));

            }else if (guess < theNumber && triesLeft>0) {//too low
                message = guess + " was too low. Try again";
                lblOutput.setText(message);
                tries = triesLeft + " tries left. Watch out!";
                txtTries.setText((tries));
            }else if (triesLeft<=0){
                message = "You have used all your tries. You lose!";
                lblOutput.setText(message);
                tries = "Try again";
                txtTries.setText((tries));
                closeKeyboard();
            }else {
                message = guess + " was correct. You win! Let's play again!";
                lblOutput.setText(message);
                closeKeyboard();
                ImageView myImageView = findViewById(R.id.imgPicture);
                myImageView.setImageResource(R.drawable.photo);
                myImageView.setVisibility(myImageView.VISIBLE);
            }
        } catch (Exception e) {
            message = "Error. Please enter a whole number above.";
            lblOutput.setText(message);
        } finally { //highlight the txtGuess field and put a focus there
            txtGuess.requestFocus();
            txtGuess.selectAll();
        }
    }
        public void closeKeyboard() {
        View view = this.getCurrentFocus();
            if(view != null){
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        public void newGame(){
        String message;
            theNumber = (int)(Math.random()*100+1);
            triesLeft = 7;
            message = "Number of tries  " + triesLeft;
            txtTries.setText(message);
            lblOutput.setText("Enter a number, then click Guess!");
            txtGuess.setText("");
            txtGuess.requestFocus();
            ImageView myImageView = findViewById(R.id.imgPicture);
            myImageView.setImageResource(R.drawable.photo);
            myImageView.setVisibility(myImageView.INVISIBLE);


        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //calls up activity name

        txtGuess = findViewById(R.id.txtGuess);
        btnGuess = findViewById(R.id.btnGuess);
        btnReset = findViewById(R.id.btnReset);
        lblOutput = findViewById(R.id.lblOutput);
        txtTries = findViewById(R.id.txtTries);

        newGame();

                // set the event listener for our guess button
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();

            }
        });
        ImageView myImageView = findViewById(R.id.imgBackground);
        myImageView.setImageResource(R.drawable.clouds19);

                // set up the event listener for our input field
        txtGuess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                checkGuess();
                return true;
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
