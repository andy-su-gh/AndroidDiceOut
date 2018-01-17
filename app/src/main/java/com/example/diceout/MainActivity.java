package com.example.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView rollResult;
    TextView rollScore;
    Button rollButton;
    int score;




    ArrayList<Integer> dice;

    ArrayList<ImageView> diceImageViews;

    Random rand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice(view);
            }
        });

        rollResult = (TextView) findViewById(R.id.rollResult);
        rollScore = (TextView) findViewById(R.id.rollScore);
        rollButton = (Button)findViewById(R.id.rollButton);

        rand = new Random();

        dice = new ArrayList<Integer>();

        //Toast.makeText(getApplicationContext(), "Welcome to Dice Out! LENGTH_LONG", Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), "Welcome to Dice Out! LENGTH_SHORT", Toast.LENGTH_SHORT).show();

        ImageView dice1Image = (ImageView)findViewById(R.id.die1Image);
        ImageView dice2Image = (ImageView)findViewById(R.id.die2Image);
        ImageView dice3Image = (ImageView)findViewById(R.id.die3Image);

        diceImageViews = new ArrayList<ImageView>();
        diceImageViews.add(dice1Image);
        diceImageViews.add(dice2Image);
        diceImageViews.add(dice3Image);
    }

    public void rollDice(View v){

        //int num = rand.nextInt(6)+1;
        //String randomValue = "Number generated: " + num;
        //Toast.makeText(getApplicationContext(), randomValue, Toast.LENGTH_SHORT).show();
        int die1;
        int die2;
        int die3;

        die1 = rand.nextInt(6)+1;
        die2 = rand.nextInt(6)+1;
        die3 = rand.nextInt(6)+1;

        dice.clear();
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);

        for (int dieOfSet = 0; dieOfSet < 3; dieOfSet++){
            String imageName = "dice" + dice.get(dieOfSet) + ".png";
            try{
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                diceImageViews.get(dieOfSet).setImageDrawable(d);
            }catch (IOException e){
                e.printStackTrace();
            }

        }

        String msg = "You rolled:  " + die1 + ",  " + die2 + ",  " + die3;
        //Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        score = 0;

        if (die1 == die2 && die1 == die3){
            int scoreDelta = die1 * 100;
            msg = "You rolled a triple " + die1 + "! You score " + scoreDelta + " points!";
            score += scoreDelta;
        }else if (die1 == die2 || die1 == die3 || die2 == die3){
            int scoreDouble = 0;
            if (die1 == die2){score = die1 * 50 + die3; scoreDouble = die1 * 50;}
            if (die1 == die3){score = die1 * 50 + die2; scoreDouble = die1 * 50;}
            if (die2 == die3){score = die2 * 50 + die1; scoreDouble = die2 * 50;}
            msg = "You rolled doubles for " + scoreDouble + " points!";
        }else {
            msg = "You didn't score this roll. Try again!";
            score = die1 + die2 + die3;
        }
        rollResult.setText(msg);
        rollScore.setText("Score: " + score);
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
