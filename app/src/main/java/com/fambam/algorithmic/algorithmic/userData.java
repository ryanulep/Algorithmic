package com.fambam.algorithmic.algorithmic;

/**
 * Created by Haz on 11/20/2017.
 */

public class userData {

    public String flags;
    public int quiz_score;

    public userData(){
        // default constructor req for DataSnapshot
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public int getQuiz_score() {
        return quiz_score;
    }

    public void setQuiz_score(int quiz_score) {
        this.quiz_score = quiz_score;
    }

}

