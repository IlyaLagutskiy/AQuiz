package com.example.aquiz;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentQuiz extends Fragment implements View.OnClickListener {

    private final static String ARGUMENT_QUESTION = "arg_question";

    private ArrayList<Button> answerButtons;
    private TextView questionTextView;
    private LinearLayout answersLayout;
    private int answersCount;
    private Question question;

    public static FragmentQuiz newInstance(Question question) {
        FragmentQuiz fragmentQuiz = new FragmentQuiz();
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_QUESTION, question);
        fragmentQuiz.setArguments(args);
        return fragmentQuiz;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        question = getArguments().getParcelable(ARGUMENT_QUESTION);
//        Log.d(KEYS.LOGS_FRAGMENT, "OnCreate:" + question.getQuestion());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        try {
            view = inflater.inflate(R.layout.fragment_quiz, null);
            answerButtons = new ArrayList<>();
            questionTextView = view.findViewById(R.id.Question);
            answersLayout = view.findViewById(R.id.buttonLayout);
            questionTextView.setText(question.getQuestion());
//            Log.d(KEYS.LOGS_FRAGMENT, "TextView:" + questionTextView.getText());
            ArrayList<String> answers = question.getShuffleAnswers();
            Log.d(KEYS.LOGS_FRAGMENT, ""+ answers.size());
            for (int i = 0; i < answers.size(); i++) {
                Button btn = new Button(this.getContext(), null, R.style.AnsweButtonStyle);
                btn.setText(answers.get(i));
                btn.setTag("btn" + i);
                btn.setOnClickListener(this);
                answerButtons.add(btn);
                answersLayout.addView(btn);
            }
        } catch (Exception ex) {
            Log.d(KEYS.LOGS_FRAGMENT, ex.getMessage());
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getTag().toString()) {
            case "btn0":
                checkIfCorrectAnswer(0);
                break;
            case "btn1":
                checkIfCorrectAnswer(1);
                break;
            case "btn2":
                checkIfCorrectAnswer(2);
                break;
            case "btn3":
                checkIfCorrectAnswer(3);
                break;

        }
    }

    private void checkIfCorrectAnswer(int buttonNumber) {
        Button btn = answerButtons.get(buttonNumber);
        if (btn.getText().equals(question.getCorrectAnswer())) {
            btn.setBackgroundResource(R.color.colorCorrectAnswer);
            setQuestionResult(true);
        } else {
            btn.setBackgroundResource(R.color.colorWrongAnswer);
            setQuestionResult(false);
        }
        for (Button b : answerButtons) {
            b.setEnabled(false);
        }
    }

    private void setQuestionResult(boolean result) {

    }
}
