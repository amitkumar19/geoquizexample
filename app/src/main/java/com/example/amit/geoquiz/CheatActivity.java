package com.example.amit.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWERE_IS_TRUE = "com.example.amit.geoquiz.answere_is_true";
    private boolean mAnswereIsTrue;
    private TextView showAnswere;
    private Button mshowAnswereButton;
    private static final String EXTRA_ANSWARE_SHOW = "com.example.amit.geoquiz.answere_show";

    public static Intent newIntent(Context packageContext,boolean answereIsRight){
        Intent i = new Intent(packageContext,CheatActivity.class);
        i.putExtra(EXTRA_ANSWERE_IS_TRUE,answereIsRight);
        return i;
    }

    public static boolean wasAnswereShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWARE_SHOW,false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswereIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWERE_IS_TRUE,false);
        showAnswere = (TextView)findViewById(R.id.showAnswere);
        mshowAnswereButton = (Button)findViewById(R.id.showAnswereBtn);
        mshowAnswereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswereIsTrue)
                {
                    showAnswere.setText(R.string.correcttoast);
                }else{
                    showAnswere.setText(R.string.incorrecttoast);
                }
                setAnswereShowResult(true);
            }
        });
    }

    private void setAnswereShowResult(boolean mAnswereShow){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWARE_SHOW,mAnswereShow);
        setResult(RESULT_OK,data);
    }
}
