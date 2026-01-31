package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvStatus;
    Button btnReset;
    ArrayList<Button> buttons = new ArrayList<>();

    boolean playerX = true;
    int moveCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        btnReset = findViewById(R.id.btnReset);

        buttons.add(findViewById(R.id.b1));
        buttons.add(findViewById(R.id.b2));
        buttons.add(findViewById(R.id.b3));
        buttons.add(findViewById(R.id.b4));
        buttons.add(findViewById(R.id.b5));
        buttons.add(findViewById(R.id.b6));
        buttons.add(findViewById(R.id.b7));
        buttons.add(findViewById(R.id.b8));
        buttons.add(findViewById(R.id.b9));

        for (Button b : buttons) {
            b.setOnClickListener(this::onButtonClick);
        }

        btnReset.setOnClickListener(v -> resetGame());
    }

    private void onButtonClick(View view) {
        Button b = (Button) view;

        if (!b.getText().toString().equals("")) return;

        b.setText(playerX ? "X" : "O");
        moveCount++;

        if (checkWinner()) {
            tvStatus.setText("Player " + (playerX ? "X" : "O") + " Wins!");
            disableButtons();
            return;
        }

        if (moveCount == 9) {
            tvStatus.setText("It's a Draw!");
            return;
        }

        playerX = !playerX;
        tvStatus.setText("Player " + (playerX ? "X" : "O") + "'s Turn");
    }

    private boolean checkWinner() {
        int[][] winPositions = {
                {0,1,2},{3,4,5},{6,7,8},
                {0,3,6},{1,4,7},{2,5,8},
                {0,4,8},{2,4,6}
        };

        for (int[] pos : winPositions) {
            String s1 = buttons.get(pos[0]).getText().toString();
            String s2 = buttons.get(pos[1]).getText().toString();
            String s3 = buttons.get(pos[2]).getText().toString();

            if (!s1.equals("") && s1.equals(s2) && s2.equals(s3)) {
                return true;
            }
        }
        return false;
    }

    private void disableButtons() {
        for (Button b : buttons) {
            b.setEnabled(false);
        }
    }

    private void resetGame() {
        for (Button b : buttons) {
            b.setText("");
            b.setEnabled(true);
        }
        playerX = true;
        moveCount = 0;
        tvStatus.setText("Player X's Turn");
    }
}