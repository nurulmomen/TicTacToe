package com.example.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean playerTurn = true; // True = X and False = O
    int turnCount = 0;
    Button[] buttonArray = null;
    Button a1, a2, a3, b1, b2, b3, c1, c2, c3;
    Button btnNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a1 = (Button) findViewById(R.id.a1);
        a2 = (Button) findViewById(R.id.a2);
        a3 = (Button) findViewById(R.id.a3);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        c1 = (Button) findViewById(R.id.c1);
        c2 = (Button) findViewById(R.id.c2);
        c3 = (Button) findViewById(R.id.c3);
        btnNewGame = (Button) findViewById(R.id.btnNewGame);

        buttonArray = new Button[]{a1, a2, a3, b1, b2, b3, c1, c2, c3};

        for(Button buttonCounter : buttonArray) buttonCounter.setOnClickListener(this);

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn = true;
                turnCount = 0;
                buttonEnableOrDisable(true);
            }
        });

    }

    @Override
    public void onClick(View v) {
        buttonClicked(v);
    }

    private void buttonClicked(View v){
        Button buttonLocal = (Button) v;

        if(playerTurn) buttonLocal.setText("X");
        else buttonLocal.setText("O");

        turnCount++;
        buttonLocal.setClickable(false);
        buttonLocal.setBackgroundColor(Color.LTGRAY);
        playerTurn = !playerTurn;

        checkForWinner();
    }

    private void checkForWinner(){
        //horizontal checking for winning condition
        boolean thereIsAwinner = false;
        if(a1.getText() == a2.getText() && a2.getText() == a3.getText() && !a1.isClickable())
            thereIsAwinner = true;
        else if(b1.getText() == b2.getText() && b2.getText() == b3.getText() && !b1.isClickable())
            thereIsAwinner = true;
        else if(c1.getText() == c2.getText() && c2.getText() == c3.getText() && !c1.isClickable())
            thereIsAwinner = true;

        //vertical checking
        else if(a1.getText() == b1.getText() && b1.getText() == c1.getText() && !a1.isClickable())
            thereIsAwinner = true;
        else if(a2.getText() == b2.getText() && b2.getText() == c2.getText() && !b2.isClickable())
            thereIsAwinner = true;
        else if(a3.getText() == b3.getText() && b3.getText() == c3.getText() && !c3.isClickable())
            thereIsAwinner = true;

        //diagonal checking
        else if (a1.getText() == b2.getText() && b2.getText() == c3.getText()
                && !a1.isClickable())
            thereIsAwinner = true;
        else if (a3.getText() == b2.getText() && b2.getText() == c1.getText()
                && !b2.isClickable())
            thereIsAwinner = true;

        // stopping the game if a player wins or, in case of a draw
        if(thereIsAwinner){
            if(!playerTurn) showMessage("X wins!");
            else showMessage("O wins!");
            gameRunning(false);
        } else if (turnCount==9) showMessage("Draw!");

    }

    private void gameRunning(boolean gameState) {
        for (Button buttonCounter: buttonArray) buttonCounter.setClickable(gameState);
    }

    private void buttonEnableOrDisable(boolean buttonEnable) {
        for (Button buttonCounter: buttonArray){
            buttonCounter.setText("");
            buttonCounter.setClickable(buttonEnable);
            buttonCounter.setBackgroundColor(Color.parseColor("#abd5ea"));
        }
    }

    private void showMessage(String messageText) {
        Toast.makeText(getApplicationContext(), messageText, Toast.LENGTH_LONG).show();
    }
}
