package com.example.stop_watch;

import android.os.Bundle;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity extends AppCompatActivity {

        TextView tvTimer;
        Button btnStart, btnPause, btnReset;

        Handler handler = new Handler();
        boolean isRunning = false;

        long startTime = 0L;
        long elapsedTime = 0L;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                elapsedTime = currentTime - startTime;

                int minutes = (int) (elapsedTime / 1000) / 60;
                int seconds = (int) (elapsedTime / 1000) % 60;
                int milliseconds = (int) (elapsedTime % 1000);

                tvTimer.setText(String.format("%02d:%02d:%03d",
                        minutes, seconds, milliseconds));

                handler.postDelayed(this, 10); // update every 10 ms
            }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            tvTimer = findViewById(R.id.tvTimer);
            btnStart = findViewById(R.id.btnStart);
            btnPause = findViewById(R.id.btnPause);
            btnReset = findViewById(R.id.btnReset);

            btnStart.setOnClickListener(v -> {
                if (!isRunning) {
                    startTime = System.currentTimeMillis() - elapsedTime;
                    handler.post(runnable);
                    isRunning = true;
                }
            });

            btnPause.setOnClickListener(v -> {
                if (isRunning) {
                    handler.removeCallbacks(runnable);
                    isRunning = false;
                }
            });

            btnReset.setOnClickListener(v -> {
                handler.removeCallbacks(runnable);
                isRunning = false;
                elapsedTime = 0L;
                tvTimer.setText("00:00:000");
            });
        }
    }