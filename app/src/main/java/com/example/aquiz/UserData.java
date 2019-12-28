package com.example.aquiz;

import android.util.Log;

import org.json.JSONObject;

public class UserData {

    private String username;
    private int score;

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public void parseJSON(JSONObject json){
        try {
            username = json.getString("username");
            score = json.getInt("score");
            Log.d("ParserLog", username);
        }
        catch (Exception ex){
            Log.d("ParserLog", ex.getMessage());
        }
    }

}
