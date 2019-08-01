package sk.elct.hangman;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
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

        game = new HangmanGame();

        updateWord();
    }


    public void guessLetter(View view) {
        if (game.getAttemptsLeft() == 0) {
            return;
        }

        String s = editTextLetter.getText().toString().toLowerCase();
        boolean isGuessed = game.guess(s.charAt(0));
        updateWord();

        if (isGuessed) {

        } else {
            updateGallows();
            if (game.getAttemptsLeft() == 0) {
                Toast.makeText(this, "Prehral si", Toast.LENGTH_SHORT).show();
            }


        }
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
