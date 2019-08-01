package sk.elct.hangman;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private HangmanGame game;

    private TextView textViewWord;
    private EditText editTextLetter;
    private ImageView imageViewGallows;
    private Button buttonOk;

    private int[] gallowsImages = {
            R.drawable.gallows0,
            R.drawable.gallows1,
            R.drawable.gallows2,
            R.drawable.gallows3,
            R.drawable.gallows4,
            R.drawable.gallows5,
            R.drawable.gallows6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewWord = findViewById(R.id.textViewWord);
        editTextLetter = findViewById(R.id.editTextLetter);
        imageViewGallows = findViewById(R.id.imageViewGallows);
        buttonOk = findViewById(R.id.buttonOk);

        restart();
    }


    public void guessLetter(View view) {
        // hra skoncila, mam button RESTART
        if (game.getAttemptsLeft() == 0 || game.isWon()) {
            restart();
            return;
        }

        // hra bezi, mam button OK
        String s = editTextLetter.getText().toString().toLowerCase();
        if (s.length() == 0) {
            Toast.makeText(this, "Vloz pismeno", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean isGuessed = game.guess(s.charAt(0));
        updateWord();

        if (isGuessed) {
            if (game.isWon()) {
                // vyhra
                gameOver("Vyhral si", Color.GREEN);
            }
        } else {
            updateGallows();
            if (game.getAttemptsLeft() == 0) {
                // prehra
                gameOver("Prehral si", Color.RED);
            }
        }
    }

    private void restart() {
        imageViewGallows.setImageResource(R.drawable.gallows0);
        imageViewGallows.clearColorFilter();
        buttonOk.setText("OK");
        game = new HangmanGame();
        updateWord();
    }

    private void gameOver(String message, int color) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        buttonOk.setText("Restart");
        //new Color(1.0, 0.3, 0.2)
        ColorFilter filter = new LightingColorFilter(color, Color.BLACK);
        imageViewGallows.setColorFilter(filter);
    }



    private void updateWord() {
        textViewWord.setText(game.getGuessedCharacters());
        editTextLetter.setText("");
    }

    private void updateGallows() {
        int id = gallowsImages.length - game.getAttemptsLeft() - 1;
        imageViewGallows.setImageResource(gallowsImages[id]);

    }
}
