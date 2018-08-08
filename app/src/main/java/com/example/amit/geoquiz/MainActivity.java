package com.example.amit.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button truebtn,falsebtn,cheatBtn;
    private ImageButton mNextBtn,mPreviousBtn;
    private TextView questionText;
    private static final String TAG ="life";
    private static final String Key = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private boolean mIsCheater;

    private Question[] mQuestion= new Question[]{
            new Question(R.string.first,true),
            new Question(R.string.second,false),
            new Question(R.string.third,false),
            new Question(R.string.fourth,true),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Key,mCurrentIndex);
    }
    private void updateQuestion(){
        int question =mQuestion[mCurrentIndex].getmTextResId();
        questionText.setText(question);
    }

    private void checkAnswere(boolean userPresserTrue){
        boolean answareOfQuestion = mQuestion[mCurrentIndex].ismAnswareTrue();
        int messageResId = 0;

        if (mIsCheater){
            messageResId=R.string.judgment;
        }
        else {
            if (userPresserTrue == answareOfQuestion) {
                messageResId = R.string.correcttoast;
            } else {
                messageResId = R.string.incorrecttoast;
            }
        }

        Toast.makeText(getApplicationContext(),messageResId,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        truebtn = (Button)findViewById(R.id.true_button);
        falsebtn = (Button)findViewById(R.id.false_button);
        mNextBtn = (ImageButton)findViewById(R.id.next_btn);
        cheatBtn = (Button)findViewById(R.id.cheatBtn);
        mPreviousBtn = (ImageButton)findViewById(R.id.previous_btn);
        questionText = (TextView)findViewById(R.id.question);
        truebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //buttonclick action
                checkAnswere(true);
            }   
        });

        questionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestion.length;
                updateQuestion();
            }
        });

        falsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //button click action
                checkAnswere(false);
            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex+1)%mQuestion.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mPreviousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentIndex!=0) {
                    mCurrentIndex = (mCurrentIndex-1)%mQuestion.length;
                    updateQuestion();
                }else {
                    Toast.makeText(getApplicationContext(),"No Previous Quesiton",Toast.LENGTH_LONG).show();
                }
            }
        });

        cheatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answere = mQuestion[mCurrentIndex].ismAnswareTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this,answere);
                startActivityForResult(intent,REQUEST_CODE_CHEAT);
            }
        });

        if (savedInstanceState !=null){
            mCurrentIndex =savedInstanceState.getInt(Key,0);
        }
        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!=Activity.RESULT_OK)
        {
            return;
        }
        if(resultCode==REQUEST_CODE_CHEAT){
            if (data==null){
                return;
            }
            mIsCheater =CheatActivity.wasAnswereShown(data);

        }

    }

}
