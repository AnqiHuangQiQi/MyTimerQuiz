package com.example.huanganqi.myquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by huanganqi on 2016-09-27.
 */
public class QM_manage extends Activity {

    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.qm_main);

        Intent intent = getIntent();

        final String user_id = intent.getStringExtra("user_id");
        final String info = intent.getStringExtra("info");

        //Log.v("user_id",user_id);

        TextView User = (TextView) findViewById(R.id.user_id);
        User.setText(user_id);

        if(info != null){
        TextView Info = (TextView) findViewById(R.id.info);
        Info.setText(info);
        }else{
            TextView Info = (TextView) findViewById(R.id.info);
            Info.setText("Information Column");
        }


        Button addU = (Button) findViewById(R.id.add_user);
        addU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QM_manage.this,AddUser.class);
                //intent.putExtra("user_id",user_id);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }

        });

        Button addQ = (Button) findViewById(R.id.add_question);
        addQ.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QM_manage.this,AddQuest.class);

                intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
        Button returntoMain = (Button) findViewById(R.id.returnmain);
        returntoMain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QM_manage.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });
        Button viewstat = (Button) findViewById(R.id.view_statistics);
        viewstat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QM_manage.this,viewStat.class);

                //intent.putExtra("user_id",user_id);
                startActivity(intent);
            }
        });


        Button delete = (Button) findViewById(R.id.delete_statistics);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(QM_manage.this,deleteStat.class);

                //intent.putExtra("user_id",user_id);
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
