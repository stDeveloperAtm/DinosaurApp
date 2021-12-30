package com.example.dinosaurapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Random;

public class SlideDisplayActivity extends AppCompatActivity {
    static ArrayList<Integer> removeNumber = new ArrayList<Integer>();
    static int index;
    static int count;
    int borderCount;
    int textSize;
    ImageSwitcher imageSwitcher;
    Button okButton;
    Random random;
    TextView textView;
    TextView answeTextView;
    String[] dinosaur;
    String[] hira_dinosaur;
    private ConstraintLayout mainLayout;
    InputMethodManager inputMethodManager;

    ArrayList<Integer> aryImgResources = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        answeTextView = (TextView)findViewById(R.id.answerTextView);
        //answeTextView.setOnClickListener(clickItem);

        Log.d("TEST", "onCreate");
        // 初期化
        count = 0;
        borderCount = 0;
        removeNumber.clear();


        dinosaur = getResources().getStringArray(R.array.dinosaur);
        hira_dinosaur = getResources().getStringArray(R.array.hira_dinosaur);
        //画面を生成
        setContentView(R.layout.activity_slide_display);

        inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        mainLayout = (ConstraintLayout) findViewById(R.id.mainLayout);

        answeTextView = (TextView)findViewById(R.id.answerTextView);
        //answeTextView.setOnClickListener(clickItem);
        okButton = (Button)findViewById(R.id.okButton);
        okButton.setOnClickListener(clickItem);
        imageSwitcher = findViewById(R.id.imageSwitcher);
        imageSwitcher.setOnClickListener(clickItem);
        imageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        imageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);
        aryImgResources.add(R.drawable.ankylosaurus);
        aryImgResources.add(R.drawable.brachiosaurus);
        aryImgResources.add(R.drawable.giganotosaurse);
        aryImgResources.add(R.drawable.kaba);
        aryImgResources.add(R.drawable.mia);
        aryImgResources.add(R.drawable.mosasaurus);
        aryImgResources.add(R.drawable.pachycephalosaurus);
        aryImgResources.add(R.drawable.parasaurolophus);
        aryImgResources.add(R.drawable.pteranodon);
        aryImgResources.add(R.drawable.rinta);
        aryImgResources.add(R.drawable.spinosaurus);
        aryImgResources.add(R.drawable.stegosaurus);
        aryImgResources.add(R.drawable.triceratops);
        aryImgResources.add(R.drawable.tyrannosaurus);
        aryImgResources.add(R.drawable.velociraptor);


        textView = findViewById(R.id.inputtextView);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                //イメージビューの生成
                ImageView imageView = new ImageView(SlideDisplayActivity.this);
                return imageView;
            }
        });

        imageSwitcher.setImageResource(aryImgResources.get(getResourceNum()));

    }

    private View.OnClickListener clickItem = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.okButton:
                    Log.d("TEST", "push okButton");
                    if(showingResult()) {
                        Toast toast = Toast.makeText(SlideDisplayActivity.this,"正解！", Toast.LENGTH_SHORT);
                        toast.show();
                        imageSwitcher.setImageResource(aryImgResources.get(getResourceNum()));
                        textView.setText("");
                        answeTextView.setText("");

                        //キーボード非表示
                        inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        //背景にフォーカスを移す
                        mainLayout.requestFocus();

                    }else {
                        Toast toast = Toast.makeText(SlideDisplayActivity.this,"ちゃうよ", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    count = 0;
                    break;
                case R.id.imageSwitcher :

                    count++;
                    if(15 == count) {
                        textSize = 1;
                        answeTextView.setTextSize(textSize);
                        answeTextView.setText(dinosaur[index]);
                        borderCount = count + 8;
                    }
                    if(count == borderCount) {
                        textSize++;
                        answeTextView.setTextSize(textSize);
                        borderCount += 8;
                    }

                    break;
                default:
            }
        }
    };

    private int getResourceNum() {
        random = new Random();
        Log.d("TEST", "aryImgResource = " + aryImgResources.size());
        Log.d("TEST", "removeNumber   = " + removeNumber.size());

        if (aryImgResources.size() <= removeNumber.size()) {
            removeNumber.clear();
            index = random.nextInt(aryImgResources.size());
        } else {
            while(true) {
                index = random.nextInt(aryImgResources.size());
                Log.d("TEST", "index   = " + index);
                if (removeNumber.contains(index)) {
                    Log.d("TEST", "contains   = " + removeNumber.contains(index));
                    continue;
                } else {
                    break;
                }
            }
        }
        removeNumber.add(index);
        Log.d("TEST", "removeNumber add");

        return index;
    }

    private boolean showingResult() {
        String inputText = textView.getText().toString();

        if (dinosaur[index].equals(inputText) || hira_dinosaur[index].equals(inputText)) {
            return true;
        } else {
            return false;
        }
    }

    protected void onResume() {
        super.onResume();

    }

    protected void onPause() {
        super.onPause();
        Log.d("TEST", "onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.d("TEST", "onStop");
    }

    protected void onStart() {
        super.onStart();
        Log.d("TEST", "onStart");
    }

    protected void onRestart() {
        super.onRestart();
        Log.d("TEST", "onRestart");
    }

}