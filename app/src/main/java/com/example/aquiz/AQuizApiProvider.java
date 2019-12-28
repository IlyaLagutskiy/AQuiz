package com.example.aquiz;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AQuizApiProvider {

    private static final String AQUIZ_API_QUESTIONS = "http://aquiz.azurewebsites.net/api/questions";
    private static final String AQUIZ_API_SCORES = "http://aquiz.azurewebsites.net/api/scores";
    private static final String AQUIZ_API_UPDATE_USERDATA = "http://aquiz.azurewebsites.net/api/updateUserData";
    private static final String AQUIZ_API_ABOUT = "http://aquiz.azurewebsites.net/about";

    private class ApiRequest extends AsyncTask<String, String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection =
                        (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                StringBuffer json = new StringBuffer(2048);
                String tmp = "";
                while ((tmp = reader.readLine()) != null)
                    json.append(tmp).append("\n");
                reader.close();

                JSONObject data = new JSONObject(json.toString());
                Log.d("JSON", json.toString());
                return data;
            } catch (Exception e) {
                Log.d("AsyncLog", e.getMessage());
                return null;
            }
        }
    }


    public void RunApi() {
        getQuestions();
    }

    public ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            JSONObject jsonObject = new ApiRequest().execute(AQUIZ_API_QUESTIONS).get();
            int questionsCount = jsonObject.getInt("questions_count");
            int answersCount = jsonObject.getInt("answers_count");
            Log.d("ParserLog", "" + answersCount);
            JSONArray qsjson = jsonObject.getJSONArray("questions");
            JSONObject qjson;
            for (int i = 0; i < questionsCount; i++) {
                qjson = qsjson.getJSONObject(i);
                Question question = new Question(answersCount);
                question.parseJSON(qjson);
                questions.add(question);
            }
        } catch (Exception ex) {
            Log.d("AsyncLog", ex.getMessage());
        }
        return questions;
    }

}
