package com.example.alextarasyuk.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8, mButton9;
    private Button mButtonReset;
    private TextView mTextView;
    private CheckBox mCheckBox;

    private Button[] buttons = {
            mButton1, mButton2, mButton3, mButton4, mButton5, mButton6, mButton7, mButton8, mButton9
    };

    private int[][] playBoard = new int[3][3];
    private int numberOfSteps = 0;
    private boolean Player1 = true;

    private static int winsOfPlayerOne = 0;
    private static int winsOfPlayerTwo = 0;

    public final static String LOG_TAG = MainActivity.class.getSimpleName();
    private List<Button> mButtonList;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        initializeViews();
        setOnClickListener();
        startNewGame();

        if (state != null) {

            super.onRestoreInstanceState(state);

            mButton1.setEnabled(state.getBoolean("Button1 state"));
            mButton2.setEnabled(state.getBoolean("Button2 state"));
            mButton3.setEnabled(state.getBoolean("Button3 state"));
            mButton4.setEnabled(state.getBoolean("Button4 state"));
            mButton5.setEnabled(state.getBoolean("Button5 state"));
            mButton6.setEnabled(state.getBoolean("Button6 state"));
            mButton7.setEnabled(state.getBoolean("Button7 state"));
            mButton8.setEnabled(state.getBoolean("Button8 state"));
            mButton9.setEnabled(state.getBoolean("Button9 state"));

            mButton1.setText(state.getString("Button1 text"));
            mButton2.setText(state.getString("Button2 text"));
            mButton3.setText(state.getString("Button3 text"));
            mButton4.setText(state.getString("Button4 text"));
            mButton5.setText(state.getString("Button5 text"));
            mButton6.setText(state.getString("Button6 text"));
            mButton7.setText(state.getString("Button7 text"));
            mButton8.setText(state.getString("Button8 text"));
            mButton9.setText(state.getString("Button9 text"));

            mCheckBox.setChecked(state.getBoolean("Value of Checkbox"));
            winsOfPlayerOne = state.getInt("Wins of first player");
            winsOfPlayerTwo = state.getInt("Wins of second player");
            Player1 = state.getBoolean("Player1 turn");
            mTextView.setText(state.getString("Text view"));

            playBoard[0] = state.getIntArray("playboard0");
            playBoard[1] = state.getIntArray("playboard1");
            playBoard[2] = state.getIntArray("playboard2");
        }

    }


    @Override
    public void onClick(View v) {


        Log.d(LOG_TAG, "Button is clicked");
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
                    playBoard[2][2] = 0;
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

        checkWinner();

//        game with computer

        if (mCheckBox.isChecked() && !Player1 && hasButtonsEnabled(mButtonList)) {
            for (; ; ) {

                Button button = mButtonList.get(new Random().nextInt(mButtonList.size()));
                if (button.isEnabled()) {
                    button.performClick();
                    return;
                }
            }

        }
    }

    //checks presense of enabled buttons
    private boolean hasButtonsEnabled(List<Button> buttonList) {

        for (Button button : buttonList
                ) {
            if (button.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    private void checkWinner() {

        Log.d(LOG_TAG, "Checking a winner");

        //checking horizontal rows
        for (int i = 0; i < 3; i++) {
            if (playBoard[i][0] == playBoard[i][1] && playBoard[i][0] == playBoard[i][2]) {
                if (playBoard[i][0] == 1) {
                    winsOfPlayerOne++;
                    result(" Игрок 1 выиграл");
                    return;
                } else if (playBoard[i][0] == 0) {
                    winsOfPlayerTwo++;
                    result("Игрок 2 выиграл");
                    return;
                }
            }
        }

        //checking vertical rows
        for (int i = 0; i < 3; i++) {
            if (playBoard[0][i] == playBoard[1][i] && playBoard[0][i] == playBoard[2][i]) {
                if (playBoard[0][i] == 1) {
                    winsOfPlayerOne++;
                    result(" Игрок 1 выиграл");
                    return;
                } else if (playBoard[0][i] == 0) {
                    winsOfPlayerTwo++;
                    result("Игрок 2 выиграл");
                    return;
                }
            }
        }

        //first diagonal
        if (playBoard[0][0] == playBoard[1][1] && playBoard[0][0] == playBoard[2][2]) {
            if (playBoard[0][0] == 1) {
                winsOfPlayerOne++;
                result(" Игрок 1 выиграл");
                return;

            } else if (playBoard[0][0] == 0) {
                winsOfPlayerTwo++;
                result("Игрок 2 выиграл");
                return;
            }
        }

        //second diagonal
        if (playBoard[0][2] == playBoard[1][1] && playBoard[0][2] == playBoard[2][0]) {
            if (playBoard[0][2] == 1) {
                winsOfPlayerOne++;
                result(" Игрок 1 выиграл");
                return;

            } else if (playBoard[0][2] == 0) {
                winsOfPlayerTwo++;
                result("Игрок 2 выиграл");
                return;
            }
        }

        if (numberOfSteps == 9) {
            result("Ничья");
            return;
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
        mCheckBox = findViewById(R.id.checkbox);

        mButtonList = new ArrayList<>();
        mButtonList.add(mButton1);
        mButtonList.add(mButton2);
        mButtonList.add(mButton3);
        mButtonList.add(mButton4);
        mButtonList.add(mButton5);
        mButtonList.add(mButton6);
        mButtonList.add(mButton7);
        mButtonList.add(mButton8);
        mButtonList.add(mButton9);


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

    //
    private void resetBoard() {

        Log.d(LOG_TAG, "reset method");

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

    //the result of the game
    private void result(String result) {

        Log.d(LOG_TAG, "result method");
        setInfo(result + "\n НОВАЯ ИГРА" + "\n побед первого игрока =" + winsOfPlayerOne + "\n побед второго игрока:" + winsOfPlayerTwo);
        enableAllButtons(false);
        startNewGame();
        Toast.makeText(this, "Игра окончена", Toast.LENGTH_LONG).show();

    }

    //enables all buttons
    private void enableAllButtons(boolean value) {

        Log.d(LOG_TAG, "Enabling all buttons");

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

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        state.putBoolean("Button1 state", mButton1.isEnabled());
        state.putBoolean("Button2 state", mButton2.isEnabled());
        state.putBoolean("Button3 state", mButton3.isEnabled());
        state.putBoolean("Button4 state", mButton4.isEnabled());
        state.putBoolean("Button5 state", mButton5.isEnabled());
        state.putBoolean("Button6 state", mButton6.isEnabled());
        state.putBoolean("Button7 state", mButton7.isEnabled());
        state.putBoolean("Button8 state", mButton8.isEnabled());
        state.putBoolean("Button9 state", mButton9.isEnabled());

        state.putString("Button1 text", mButton1.getText().toString());
        state.putString("Button2 text", mButton2.getText().toString());
        state.putString("Button3 text", mButton3.getText().toString());
        state.putString("Button4 text", mButton4.getText().toString());
        state.putString("Button5 text", mButton5.getText().toString());
        state.putString("Button6 text", mButton6.getText().toString());
        state.putString("Button7 text", mButton7.getText().toString());
        state.putString("Button8 text", mButton8.getText().toString());
        state.putString("Button9 text", mButton9.getText().toString());

        state.putBoolean("Value of Checkbox", mCheckBox.isChecked());
        state.putInt("Wins of first player", winsOfPlayerOne);
        state.putInt("Wins of second player", winsOfPlayerTwo);
        state.putBoolean("Player1 turn", Player1);
        state.putString("Text view", mTextView.getText().toString());

        state.putIntArray("playboard0", playBoard[0]);
        state.putIntArray("playboard1", playBoard[1]);
        state.putIntArray("playboard2", playBoard[2]);


    }


//    @Override
//    protected void onRestoreInstanceState(Bundle state) {
//        super.onRestoreInstanceState(state);
//
//        mButton1.setEnabled(state.getBoolean("Button1 state", mButton1.isEnabled()));
//        mButton2.setEnabled(state.getBoolean("Button2 state", mButton2.isEnabled()));
//        mButton3.setEnabled(state.getBoolean("Button3 state", mButton3.isEnabled()));
//        mButton4.setEnabled(state.getBoolean("Button4 state", mButton4.isEnabled()));
//        mButton5.setEnabled(state.getBoolean("Button5 state", mButton5.isEnabled()));
//        mButton6.setEnabled(state.getBoolean("Button6 state", mButton6.isEnabled()));
//        mButton7.setEnabled(state.getBoolean("Button7 state", mButton7.isEnabled()));
//        mButton8.setEnabled(state.getBoolean("Button8 state", mButton8.isEnabled()));
//        mButton9.setEnabled(state.getBoolean("Button9 state", mButton9.isEnabled()));
//
//        mButton1.setText(state.getString("Button1 text"));
//        mButton2.setText(state.getString("Button2 text"));
//        mButton3.setText(state.getString("Button3 text"));
//        mButton4.setText(state.getString("Button4 text"));
//        mButton5.setText(state.getString("Button5 text"));
//        mButton6.setText(state.getString("Button6 text"));
//        mButton7.setText(state.getString("Button7 text"));
//        mButton8.setText(state.getString("Button8 text"));
//        mButton9.setText(state.getString("Button9 text"));
//
//        mCheckBox.setChecked(state.getBoolean("Value of Checkbox"));
//        winsOfPlayerOne = state.getInt("Wins of first player");
//        winsOfPlayerTwo = state.getInt("Wins of second player");
//        Player1 = state.getBoolean("Player1 turn");
//        mTextView.setText(state.getString("Text view"));
//
//        playBoard[0] = state.getIntArray("playboard0");
//        playBoard[1] = state.getIntArray("playboard1");
//        playBoard[2] = state.getIntArray("playboard2");
//
//    }
}
