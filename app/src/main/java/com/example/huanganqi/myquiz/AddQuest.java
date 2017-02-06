package com.example.huanganqi.myquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huanganqi.myquiz.data.QuizDBHelper;

/**
 * Created by huanganqi on 2016-09-30.
 */
public class AddQuest extends Activity {

    private String Questi;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String time;
    private String answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.addquest);
        Intent intent = getIntent();
        final String user_id_glo = intent.getStringExtra("user_id");
        Button Add = (Button) findViewById(R.id.add_Ques);

        Add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                boolean checkDisk;

                final EditText Ques = (EditText) findViewById(R.id.questions);
                EditText O1 = (EditText) findViewById(R.id.option1);
                EditText O2 = (EditText) findViewById(R.id.option2);
                EditText O3 = (EditText) findViewById(R.id.option3);
                EditText O4 = (EditText) findViewById(R.id.option4);
                EditText Time = (EditText) findViewById(R.id.times);
                EditText Ans = (EditText) findViewById(R.id.correct);
                QuizDBHelper QDBH = new QuizDBHelper(AddQuest.this);
                ////////////////////////////此行为测试用,最后需删除
                //QDBH.dropAllQuest();
                Questi = Ques.getText().toString();
                option1 = O1.getText().toString();
                option2 = O2.getText().toString();
                option3 = O3.getText().toString();
                option4 = O4.getText().toString();
                time = Time.getText().toString();
                answer = Ans.getText().toString();
                checkDisk=QDBH.addQuest(Questi,option1,option2,option3,option4,time,answer);

                Intent backM = new Intent(AddQuest.this,QM_manage.class);
                backM.putExtra("user_id",user_id_glo);
                if(checkDisk) {
                    backM.putExtra("info", "Add A New Question Successfully");
                }
                else{
                    backM.putExtra("info", "Could not add this question.");
                }
                startActivity(backM);


            }

        });

    }
}
