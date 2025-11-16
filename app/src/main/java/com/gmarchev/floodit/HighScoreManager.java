package com.gmarchev.floodit;

import android.content.Context;
import android.content.SharedPreferences;

public class HighScoreManager {

    private static final String PREF_NAME = "game_prefs";
    private static final String HIGH_SCORE_KEY = "high_score";

    private final SharedPreferences sharedPreferences;

    public HighScoreManager(Context context) {

        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveHighScore(int score) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(HIGH_SCORE_KEY, score);
        editor.apply();
    }

    public int getHighScore() {

        return sharedPreferences.getInt(HIGH_SCORE_KEY, 0);
    }

    public boolean highScoreIsSet() {

        return sharedPreferences.contains(HIGH_SCORE_KEY);
    }
}

