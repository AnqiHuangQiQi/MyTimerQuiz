package com.example.huanganqi.myquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huanganqi.myquiz.data.QuizDBHelper;

/**
 * Created by huanganqi on 2016-10-13.
 */
public class deleteStat extends Activity {
    private EditText user_id;
    private Button delete;
    private String info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.deletestat);
        Intent intent = getIntent();

        user_id = (EditText) findViewById(R.id.user_id);
        delete = (Button) findViewById(R.id.deleteB);




        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                QuizDBHelper QDBH = new QuizDBHelper(deleteStat.this);
                boolean bl  = QDBH.deleteStat(user_id.getText().toString());

                if(bl == true){
                    info = "Delete Successfully!";
                }else{
                    info = "No Stattistics for this user!";
                }

                Intent backM = new Intent(deleteStat.this,QM_manage.class);

                backM.putExtra("info",info);
                startActivity(backM);



            }

        });
    }

}
