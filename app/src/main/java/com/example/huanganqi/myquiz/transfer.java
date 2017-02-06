package com.example.huanganqi.myquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.huanganqi.myquiz.data.QuizDBHelper;

/**
 * Created by huanganqi on 2016-10-11.
 */
public class transfer extends Activity {

    private String user_id;
    private TextView userName;
    private Button saveclose;
    private Button anotherro;
    QuizDBHelper QDBH = new QuizDBHelper(transfer.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.transfer);

        Intent intent = getIntent();

        final String user_id = intent.getStringExtra("user_id");
        final String mark = intent.getStringExtra("Mark");



        userName = (TextView) findViewById(R.id.user_id);

        userName.setText(user_id);

        saveclose = (Button) findViewById(R.id.saveandclose);
        anotherro = (Button) findViewById(R.id.startanotherround);

        saveclose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                QDBH.addStatistics(user_id,mark);
                Intent intent = new Intent(transfer.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
        anotherro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                int[] guoguo = new int[5];
                for(int i=0;i<5;i++){
                    guoguo[i]=-1;
                }

                QDBH.addStatistics(user_id,mark);
                Intent intent = new Intent(transfer.this,QT_quiz.class);

                intent.putExtra("user_id",user_id);
                intent.putExtra("guoguo",guoguo);
                intent.putExtra("dijige",0);
                intent.putExtra("mark",0);
                startActivity(intent);
            }
        });





    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
