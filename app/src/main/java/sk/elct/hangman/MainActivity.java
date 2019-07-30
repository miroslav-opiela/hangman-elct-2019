package sk.elct.hangman;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private static final String TAG = "Krava";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "on create");
        mediaPlayer = MediaPlayer.create(this,R.raw.cow);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "on start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "on resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "on pause");
        mediaPlayer.release();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "on stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "on destroy");
    }

    public void rollDice(View view) {
        Random r = new Random();
        int value = r.nextInt(6) + 1;

        TextView textView = findViewById(R.id.helloWorldTextView);
        textView.setText("hodil si " + value);
        if (value == 6) {
            textView.setTextColor(Color.RED);
        } else {
            textView.setTextColor(Color.GREEN);
        }

    }

    public void playSound(View view) {
        mediaPlayer.start();
    }
}
