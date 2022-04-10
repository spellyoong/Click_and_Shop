package com.example.clickandshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {

    private View backArrow;
    private View ques1Container;
    private View ques2Container;
    private View ques3Container;
    private View ques4Container;
    private View ques5Container;
    private View ans1;
    private View ans2;
    private View ans3;
    private View ans4;
    private View ans5;
    private View ques1Arrow;
    private View ques2Arrow;
    private View ques3Arrow;
    private View ques4Arrow;
    private View ques5Arrow;
    private ArrayList<View> quesArrowList = new ArrayList<>();
    private ArrayList<View> ansList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // FAQ navigation
        ques1Container = findViewById(R.id.ques1Container);
        ques2Container = findViewById(R.id.ques2Container);
        ques3Container = findViewById(R.id.ques3Container);
        ques4Container = findViewById(R.id.ques4Container);
        ques5Container = findViewById(R.id.ques5Container);
        ques1Arrow = findViewById (R.id.ques1Arrow);
        ques2Arrow = findViewById (R.id.ques2Arrow);
        ques3Arrow = findViewById (R.id.ques3Arrow);
        ques4Arrow = findViewById (R.id.ques4Arrow);
        ques5Arrow = findViewById (R.id.ques5Arrow);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        ans5 = findViewById(R.id.ans5);

        quesArrowList.add(ques1Arrow);
        quesArrowList.add(ques2Arrow);
        quesArrowList.add(ques3Arrow);
        quesArrowList.add(ques4Arrow);
        quesArrowList.add(ques5Arrow);
        ansList.add(ans1);
        ansList.add(ans2);
        ansList.add(ans3);
        ansList.add(ans4);
        ansList.add(ans5);


        ques1Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faqToggle(0);
            }
        });

        ques2Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faqToggle(1);
            }
        });

        ques3Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faqToggle(2);
            }
        });

        ques4Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faqToggle(3);
            }
        });

        ques5Container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faqToggle(4);
            }
        });

        // Back key navigation
        backArrow = findViewById(R.id.backArrow);

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void faqToggle(int position){
        if (ansList.get(position).getVisibility() == View.GONE){
            ansList.get(position).setVisibility(View.VISIBLE);
            quesArrowList.get(position).animate().rotationBy(90f).setDuration(100);
        }
        else {
            ansList.get(position).setVisibility(View.GONE);
            quesArrowList.get(position).animate().rotationBy(-90f).setDuration(100);
        }
    }
}