package sk.elct.hangman;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
