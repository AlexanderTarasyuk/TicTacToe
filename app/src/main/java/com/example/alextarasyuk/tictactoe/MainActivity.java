package com.example.alextarasyuk.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8, mButton9;
    private Button mButtonReset;
    private TextView mTextView;

    int[][] playBoard = new int[3][3];
    int numberOfSteps = 0;
    private boolean Player1 = true;

    public final static String LOG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setOnClickListener();
        startNewGame();

    }


    @Override
    public void onClick(View v) {

        Log.d(LOG, "Bunnon is clicked");
        boolean resetButtonIsPressed = false;

        switch (v.getId()) {

            //first row
            case R.id.button11:
                if (Player1) {
                    mButton1.setText("X");
                    playBoard[0][0] = 1;
                } else {
                    mButton1.setText("O");
                    playBoard[0][0] = 0;
                }
                mButton1.setEnabled(false);
                break;

            case R.id.button12:
                if (Player1) {
                    mButton2.setText("X");
                    playBoard[0][1] = 1;
                } else {
                    mButton2.setText("O");
                    playBoard[0][1] = 0;
                }

                mButton2.setEnabled(false);
                break;


            case R.id.button13:
                if (Player1) {
                    mButton3.setText("X");
                    playBoard[0][2] = 1;
                } else {
                    mButton3.setText("O");
                    playBoard[0][2] = 0;
                }

                mButton3.setEnabled(false);
                break;

            // second row
            case R.id.button21:
                if (Player1) {
                    mButton4.setText("X");
                    playBoard[1][0] = 1;
                } else {
                    mButton4.setText("O");
                    playBoard[1][0] = 0;
                }
                mButton4.setEnabled(false);
                break;

            case R.id.button22:
                if (Player1) {
                    mButton5.setText("X");
                    playBoard[1][1] = 1;
                } else {
                    mButton5.setText("O");
                    playBoard[1][1] = 0;
                }
                mButton5.setEnabled(false);
                break;

            case R.id.button23:
                if (Player1) {
                    mButton6.setText("X");
                    playBoard[1][2] = 1;
                } else {
                    mButton6.setText("O");
                    playBoard[1][2] = 0;
                }
                mButton6.setEnabled(false);
                break;

            //third row
            case R.id.button31:
                if (Player1) {
                    mButton7.setText("X");
                    playBoard[2][0] = 1;
                } else {
                    mButton7.setText("O");
                    playBoard[2][0] = 0;
                }
                mButton7.setEnabled(false);
                break;

            case R.id.button32:
                if (Player1) {
                    mButton8.setText("X");
                    playBoard[2][1] = 1;
                } else {
                    mButton8.setText("O");
                    playBoard[2][1] = 0;
                }

                mButton8.setEnabled(false);
                break;

            case R.id.button33:
                if (Player1) {
                    mButton9.setText("X");
                    playBoard[2][2] = 1;
                } else {
                    mButton9.setText("O");
                    playBoard[2][2] = 1;
                }

                mButton9.setEnabled(false);
                break;

            case R.id.button_reset:
                resetButtonIsPressed = true;
                break;

            default:
                break;
        }

        if (resetButtonIsPressed) {
            resetBoard();
        } else {
            numberOfSteps++;
            Player1 = !Player1;
            if (Player1) {
                setInfo("Ход первого игрока");
            } else {
                setInfo("Ход второго игрока");
            }
        }
        if (numberOfSteps == 9) {
            result("Ничья");
        }

        checkWinner();
    }


    private void checkWinner() {

        Log.d(LOG, "Checking a winner");

        //checking horizontal rows
        for (int i = 0; i < 3; i++) {
            if (playBoard[i][0] == playBoard[i][1] && playBoard[i][0] == playBoard[i][2]) {
                if (playBoard[i][0] == 1) {
                    result(" Игрок 1 выиграл");
                    break;
                } else if (playBoard[i][0] == 0) {
                    result("Игрок 2 выиграл");
                    break;
                }
            }
        }

        //checking vertical rows
        for (int i = 0; i < 3; i++) {
            if (playBoard[0][i] == playBoard[1][i] && playBoard[0][i] == playBoard[2][i]) {
                if (playBoard[0][i] == 1) {
                    result(" Игрок 1 выиграл");
                    break;
                } else if (playBoard[0][i] == 0) {
                    result("Игрок 2 выиграл");
                    break;
                }
            }
        }

        //first diagonal
        if (playBoard[0][0] == playBoard[1][1] && playBoard[0][0] == playBoard[2][2]) {
            if (playBoard[0][0] == 1) {
                result(" Игрок 1 выиграл");

            } else if (playBoard[0][0] == 0) {
                result("Игрок 2 выиграл");
            }
        }

        //second diagonal
        if (playBoard[0][2] == playBoard[1][1] && playBoard[0][2] == playBoard[2][0]) {
            if (playBoard[0][2] == 1) {
                result(" Игрок 1 выиграл");

            } else if (playBoard[0][2] == 0) {
                result("Игрок 2 выиграл");
            }
        }


    }

    private void initializeViews() {

        mButton1 = findViewById(R.id.button11);
        mButton2 = findViewById(R.id.button12);
        mButton3 = findViewById(R.id.button13);
        mButton4 = findViewById(R.id.button21);
        mButton5 = findViewById(R.id.button22);
        mButton6 = findViewById(R.id.button23);
        mButton7 = findViewById(R.id.button31);
        mButton8 = findViewById(R.id.button32);
        mButton9 = findViewById(R.id.button33);
        mButtonReset = findViewById(R.id.button_reset);
        mTextView = findViewById(R.id.text_view);
    }

    private void setOnClickListener() {
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
        mButton7.setOnClickListener(this);
        mButton8.setOnClickListener(this);
        mButton9.setOnClickListener(this);
        mButtonReset.setOnClickListener(this);
    }

    private void startNewGame() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                playBoard[i][j] = -1;
            }

        }
    }

    private void resetBoard() {

        Log.d(LOG, "reset method");

        mButton1.setText("");
        mButton2.setText("");
        mButton3.setText("");
        mButton4.setText("");
        mButton5.setText("");
        mButton6.setText("");
        mButton7.setText("");
        mButton8.setText("");
        mButton9.setText("");

        enableAllButtons(true);
        Player1 = true;
        numberOfSteps = 0;
        startNewGame();

    }

    private void setInfo(String text) {
        mTextView.setText(text);
    }

    private void result(String result) {

        Log.d(LOG, "result method");
        setInfo(result + "\n НОВАЯ ИГРА");
        enableAllButtons(false);
        startNewGame();
        Toast.makeText(this, "Игра окончена", Toast.LENGTH_LONG).show();

    }

    private void enableAllButtons(boolean value) {

        Log.d(LOG, "Enabling all buttons");

        mButton1.setEnabled(value);
        mButton2.setEnabled(value);
        mButton3.setEnabled(value);
        mButton4.setEnabled(value);
        mButton5.setEnabled(value);
        mButton6.setEnabled(value);
        mButton7.setEnabled(value);
        mButton8.setEnabled(value);
        mButton9.setEnabled(value);
    }


}
