package com.example.amit.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswareTrue;

    public Question(int mResId, boolean mAnswareTrue) {
        this.mTextResId = mResId;
        this.mAnswareTrue = mAnswareTrue;
    }

    public int getmTextResId() {
        return mTextResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public boolean ismAnswareTrue() {
        return mAnswareTrue;
    }

    public void setmAnswareTrue(boolean mAnswareTrue) {
        this.mAnswareTrue = mAnswareTrue;
    }
}
