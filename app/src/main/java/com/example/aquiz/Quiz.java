package com.example.aquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class Quiz extends FragmentActivity {

    ViewPager pager;
    PagerAdapter adapter;
    private final static int PAGE_COUNT = 10;
    ArrayList<Question> questions;
    int answersCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiate();
    }

    private void initiate() {
        Intent intent = getIntent();
        answersCount = intent.getIntExtra(KEYS.NUMBER_OF_ANSWERS, 4);
        setContentView(R.layout.activity_quiz);
        questions = new ArrayList<>();
        questions = new AQuizApiProvider().getQuestions();
        pager = findViewById(R.id.viewPager);
        adapter = new QuizPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    protected class QuizPagerAdapter extends FragmentPagerAdapter {

        public QuizPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Question q = questions.get(position);
            q.setAnswersCount(answersCount);
            return FragmentQuiz.newInstance(q);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }

}
