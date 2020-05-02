package com.example.playtictacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //0 - X
    //1 - O
    int activePlayer = 0;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    //  State meanings:
    //  0 - X
    //  1 - O
    //  2 - NULL
    int [][] winPositions = {   {0,1,2}, {3,4,5}, {6,7,8},
                                {0,3,6}, {1,4,7}, {2,5,8},
                                {0,4,8}, {2,4,6}};

    public void gameReset(View view){
        gameActive = true;
        activePlayer =0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
            ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn: Tap to play");
    }
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn: Tap to play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn: Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winPosition: winPositions){
            if (gameState[winPosition[0]] ==gameState[winPosition[1]] &&
                    gameState[winPosition[1]] ==gameState[winPosition[2]] &&
                    gameState[winPosition[0]]!=2){
                //Somebody has won - Find out who!
                String winnerStr;
                if(gameState[winPosition[0]]== 0){
                    winnerStr = "X has won";
                }
                else{
                    winnerStr = "O has won";
                }
                //Update the status bar
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                gameActive = false;
            }
        }
        int j;
        for(j=0;j<gameState.length;j++){
            if(gameState[j]==2){
                break;
            }
        }
        if(j==gameState.length&&gameActive!=false){
            TextView status = findViewById(R.id.status);
            status.setText("Game Tied");
            gameActive = false;

        }
    }
    // Check if anyone has won

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
