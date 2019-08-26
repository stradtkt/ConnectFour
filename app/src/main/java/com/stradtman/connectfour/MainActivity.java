package com.stradtman.connectfour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    boolean gameIsActive = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winingPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
        }
        counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
        for(int[] winingPosition : winingPositions) {
            if(gameState[winingPosition[0]] == gameState[winingPosition[1]]
                && gameState[winingPosition[1]] == gameState[winingPosition[2]]
                && gameState[winingPosition[0]] != 2) {
                gameIsActive = false;
                String winner = "Red";
                if(gameState[winingPosition[0]] == 0) {
                    winner = "Yellow";
                }
                TextView winnerMessage = (TextView) findViewById(R.id.textViewPlayAgain);
                winnerMessage.setText(winner + " has won!");
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgain(View view) {
        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for(int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
