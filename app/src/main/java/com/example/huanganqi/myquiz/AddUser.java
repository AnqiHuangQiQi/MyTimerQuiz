package com.example.huanganqi.myquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huanganqi.myquiz.data.QuizDBHelper;

/**
 * Created by huanganqi on 2016-09-29.
 */
public class AddUser extends ActionBarActivity {

    private String user_id;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.adduser);
        Intent intent = getIntent();
        final String user_id_glo = intent.getStringExtra("user_id");

        final EditText UserId = (EditText)findViewById(R.id.user_id);
        final EditText Passwo = (EditText)findViewById(R.id.user_pass);
        Button Add = (Button) findViewById(R.id.add_New);

        Add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                QuizDBHelper QDBH = new QuizDBHelper(AddUser.this);
                ////////////////////////////此行为测试用,最后需删除
                //QDBH.dropAllUser();
                user_id = UserId.getText().toString();
                password = Passwo.getText().toString();
                QDBH.addUser(user_id,password);

                Intent backM = new Intent(AddUser.this,QM_manage.class);
                backM.putExtra("user_id",user_id_glo);
                backM.putExtra("info","Add A New User Successfully");
                startActivity(backM);


            }

        });
    }
}
