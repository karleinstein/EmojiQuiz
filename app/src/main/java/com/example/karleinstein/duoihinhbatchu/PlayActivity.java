package com.example.karleinstein.duoihinhbatchu;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import android.media.Image;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karleinstein.duoihinhbatchu.R;

import java.util.ArrayList;
import java.util.Random;

public class PlayActivity extends
        AppCompatActivity
        implements View.OnClickListener {
    private Button[] buttonsYellow;
    private ArrayList<Button> buttonsResult;
    private String typeListener;
    private ArrayList<Integer> images;
    private FrameLayout flImage;
    private ArrayList<String> textYellow;
    private ArrayList<String> answers;
    private Button btnNext;
    private ArrayList<Button> buttonsTemp;
    private String finalAnswer = "";
    private String trueAnswer;
    private int tempCount;
    private int score;
    private int life = 5;
    private TextView txtScore;
    private TextView txtLife;
    private boolean nextGame;
    private ArrayList<Integer> randomImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initCompnents();
        generatorGame();

    }

    private void randombtnHovers(int imageBackground) {

        for (int i = 0; i < images.size(); i++) {
            if (images.get(i).equals(imageBackground)) {

                String name = getResources().getResourceEntryName(imageBackground);
                Log.d("fuck", name);
                numberCharacters(name);
            }
        }

    }

    private void numberCharacters(String trueAnswer) {
        this.trueAnswer = trueAnswer;
        int numberCharacters = 0;
        for (int i = 0; i < trueAnswer.length(); i++) {
            String a = String.valueOf(trueAnswer.charAt(i));
            textYellow.add(a);
            numberCharacters++;
        }
        if (numberCharacters == 6) {
            buttonsResult.get(6).setVisibility(View.GONE);
            buttonsResult.get(7).setVisibility(View.GONE);
            buttonsResult.get(8).setVisibility(View.GONE);
            buttonsResult.get(9).setVisibility(View.GONE);
            buttonsResult.get(10).setVisibility(View.GONE);
        }
        if (numberCharacters == 5) {
            buttonsResult.get(5).setVisibility(View.GONE);
            buttonsResult.get(6).setVisibility(View.GONE);
            buttonsResult.get(7).setVisibility(View.GONE);
            buttonsResult.get(8).setVisibility(View.GONE);
            buttonsResult.get(9).setVisibility(View.GONE);
            buttonsResult.get(10).setVisibility(View.GONE);
        }
        if (numberCharacters == 7) {
            buttonsResult.get(7).setVisibility(View.GONE);
            buttonsResult.get(8).setVisibility(View.GONE);
            buttonsResult.get(9).setVisibility(View.GONE);
            buttonsResult.get(10).setVisibility(View.GONE);
        }
        if (numberCharacters == 3) {
            buttonsResult.get(3).setVisibility(View.GONE);
            buttonsResult.get(4).setVisibility(View.GONE);
            buttonsResult.get(5).setVisibility(View.GONE);
            buttonsResult.get(6).setVisibility(View.GONE);
            buttonsResult.get(7).setVisibility(View.GONE);
            buttonsResult.get(8).setVisibility(View.GONE);
            buttonsResult.get(9).setVisibility(View.GONE);
            buttonsResult.get(10).setVisibility(View.GONE);
        }
        if (numberCharacters == 9) {

            buttonsResult.get(9).setVisibility(View.GONE);
            buttonsResult.get(10).setVisibility(View.GONE);
        }
        if (numberCharacters == 8) {
            buttonsResult.get(8).setVisibility(View.GONE);
            buttonsResult.get(9).setVisibility(View.GONE);
            buttonsResult.get(10).setVisibility(View.GONE);
        }
        if (numberCharacters == 10) {
            buttonsResult.get(10).setVisibility(View.GONE);
        }
        while (textYellow.size() < 16) {
            int randomNumber = ThreadLocalRandom.current().nextInt(97, 122);

            char test = (char) randomNumber;
            String randomCharacters = String.valueOf(test);


            if (!textYellow.contains(randomCharacters)) {
                textYellow.add(randomCharacters);
            }

        }
        for (int i = 0; i < buttonsYellow.length; i++) {
            buttonsYellow[i].setText(textYellow.get(i));

        }

    }


    private void generatorGame() {
        int randomImage = 0;
        txtScore.setText(String.valueOf(score));
        txtLife.setText(String.valueOf(life));
        Random random = new Random();
        for (int i = 0; i < randomImages.size(); i++) {
            randomImage = random.nextInt(images.size());
            if (randomImages.get(i).equals(randomImage)) {
                randomImages.add(randomImage);
                if (nextGame) {
                    images.remove(randomImage);
                    randomImage = random.nextInt(images.size());
                    nextQuestions();
                }
                flImage.setBackgroundResource(images.get(randomImage));
            }
        }

        for (int i = images.size() - 1; i >= 0; i--) {
            if (getResources().getDrawable(images.get(i)).getConstantState() == flImage.getBackground().getConstantState()) {
                randombtnHovers(images.get(i));
            }
        }
        for (int i = 0; i < buttonsResult.size(); i++) {
            if (buttonsResult.get(i).getVisibility() == View.GONE) {
                buttonsTemp.add(buttonsResult.get(i));
            }
        }
    }

    private void nextQuestions() {
        btnNext.setVisibility(View.INVISIBLE);
        btnNext.setClickable(false);
        tempCount = 0;
        buttonsTemp.clear();
        buttonsResult.clear();
        addButtons();
        for (int j = 0; j < buttonsResult.size(); j++) {
            if (buttonsResult.get(j).getVisibility() == View.GONE) {
                buttonsResult.get(j).setVisibility(View.VISIBLE);
            }
            answers.clear();
            finalAnswer = "";
            buttonsResult.get(j).setText("");
            buttonsResult.get(j).setBackgroundResource(R.drawable.ic_result);
        }
        textYellow.clear();
        for (int i = 0; i < buttonsYellow.length; i++) {
            buttonsYellow[i].setClickable(true);
            if (buttonsYellow[i].getVisibility() == View.INVISIBLE) {
                buttonsYellow[i].setVisibility(View.VISIBLE);
            }
        }
        Log.d("fuck", "remove successfully");

        nextGame = false;
    }

    private void initCompnents() {
        randomImages = new ArrayList<>();
        txtLife = findViewById(R.id.txtLife);
        txtScore = findViewById(R.id.txtScore);
        buttonsTemp = new ArrayList<>();
        textYellow = new ArrayList<>();
        answers = new ArrayList<>();
        buttonsYellow = new Button[]{
                findViewById(R.id.btnHoverD)
                , findViewById(R.id.btnHoverA)
                , findViewById(R.id.btnHoverE)
                , findViewById(R.id.btnHoverG)
                , findViewById(R.id.btnHoverH)
                , findViewById(R.id.btnHoverI)
                , findViewById(R.id.btnHoverY)
                , findViewById(R.id.btnHoverS2)
                , findViewById(R.id.btnHoverR)
                , findViewById(R.id.btnHoverO2)
                , findViewById(R.id.btnHoverN)
                , findViewById(R.id.btnHoverM)
                , findViewById(R.id.btnHoverK)
                , findViewById(R.id.btnHoverI2)
                , findViewById(R.id.btnHoverO)
                , findViewById(R.id.btnHoverS)
        };
        addButtons();

        images = new ArrayList<>();
        images.add(R.drawable.cattuong);
        images.add(R.drawable.aomua);
        images.add(R.drawable.baocao);
        images.add(R.drawable.canthiep);
        images.add(R.drawable.xedapdien);
        images.add(R.drawable.xaphong);
        images.add(R.drawable.xakep);
        images.add(R.drawable.vuonbachthu);
        images.add(R.drawable.vuaphaluoi);
        images.add(R.drawable.tranhthu);
        images.add(R.drawable.totien);
        images.add(R.drawable.tohoai);
        images.add(R.drawable.tichphan);
        images.add(R.drawable.thothe);
        images.add(R.drawable.thattinh);
        images.add(R.drawable.songsong);
        images.add(R.drawable.quyhang);
        images.add(R.drawable.oto);
        images.add(R.drawable.nambancau);
        images.add(R.drawable.masat);
        images.add(R.drawable.lancan);
        images.add(R.drawable.kiemchuyen);
        images.add(R.drawable.khoailang);
        images.add(R.drawable.hongtam);
        images.add(R.drawable.hoidong);
        images.add(R.drawable.giangmai);
        images.add(R.drawable.giandiep);
        images.add(R.drawable.danong);
        images.add(R.drawable.danhlua);
        images.add(R.drawable.chieutre);
        btnNext = findViewById(R.id.btnNext);
        for (int i = 0; i < buttonsYellow.length; i++) {
            buttonsYellow[i].setOnClickListener(this);
        }
        flImage = findViewById(R.id.flImage);
    }

    private void addButtons() {
        buttonsResult = new ArrayList<>();
        buttonsResult.add((Button) findViewById(R.id.btnResult1));
        buttonsResult.add((Button) findViewById(R.id.btnResult2));
        buttonsResult.add((Button) findViewById(R.id.btnResult3));
        buttonsResult.add((Button) findViewById(R.id.btnResult4));
        buttonsResult.add((Button) findViewById(R.id.btnResult5));
        buttonsResult.add((Button) findViewById(R.id.btnResult6));
        buttonsResult.add((Button) findViewById(R.id.btnResult7));
        buttonsResult.add((Button) findViewById(R.id.btnResult8));
        buttonsResult.add((Button) findViewById(R.id.btnResult9));
        buttonsResult.add((Button) findViewById(R.id.btnResult10));
        buttonsResult.add((Button) findViewById(R.id.btnResult11));
    }

    @Override
    public void onClick(View view) {
        for (int i = 0; i < buttonsYellow.length; i++) {
            if (view.getId() == buttonsYellow[i].getId()) {
                typeListener = buttonsYellow[i].getText().toString();
                buttonsYellow[i].setVisibility(View.INVISIBLE);
                for (int j = 0; j < buttonsResult.size(); j++) {
                    if (j == 0) {
                        buttonsResult.get(tempCount).setText(typeListener);
                        String textAll = buttonsResult.get(tempCount).getText().toString();

                        answers.add(textAll);
                        tempCount++;

                    }


                }


            }
        }
        if (tempCount == buttonsResult.size() - buttonsTemp.size()) {
            for (int i = 0; i < buttonsYellow.length; i++) {
                buttonsYellow[i].setClickable(false);
            }
            for (int i = 0; i < answers.size(); i++) {
                finalAnswer += answers.get(i);
            }
            checkAnswer(finalAnswer);
        }
        switch (view.getId()) {
            case R.id.btnNext:
                nextGame = true;
                generatorGame();

                break;
            default:
                break;
        }
    }

    private void checkAnswer(String finalAnswer) {
        if (finalAnswer.equals(trueAnswer)) {
            score += 100;
            for (int i = 0; i < buttonsResult.size(); i++) {
                if (buttonsResult.get(i).getVisibility() == View.VISIBLE) {
                    buttonsResult.get(i).setBackgroundResource(R.drawable.ic_tile_true);
                }
            }
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setOnClickListener(this);
        } else {
            for (int i = 0; i < buttonsResult.size(); i++) {
                if (buttonsResult.get(i).getVisibility() == View.VISIBLE) {
                    buttonsResult.get(i).setBackgroundResource(R.drawable.ic_tile_false);
                }
            }
            if (finalAnswer.length() == trueAnswer.length()) {
                life--;
                nextGame = true;
                generatorGame();
                if (life == 0) {
                    Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show();
                }

            }

        }
    }


}
