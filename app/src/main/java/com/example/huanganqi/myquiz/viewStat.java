package com.example.huanganqi.myquiz;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.huanganqi.myquiz.data.QuizDBHelper;

/**
 * Created by huanganqi on 2016-10-11.
 */
public class viewStat extends ActionBarActivity {

    ScrollView SV;
    LinearLayout LL;
    TextView User;
    TextView Perf;

    private ArrayAdapter<String> StatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewstat);

        SV = (ScrollView) findViewById(R.id.scroll);

        QuizDBHelper QDBH = new QuizDBHelper(viewStat.this);

        String AllStat[][] = QDBH.getAllStat();
        int len = AllStat.length;

        String[] eachview = new String[len];

        //View container = LayoutInflater.from(viewStat.this).inflate(R.layout.container, null);


        LinearLayout container = (LinearLayout) this.findViewById(R.id.container);
        View view = LayoutInflater.from(viewStat.this).inflate(R.layout.singlestat, null);

        //LL = (LinearLayout) findViewById(R.id.singlestat);
        User = (TextView) view.findViewById(R.id.user_id);
        Perf = (TextView) view.findViewById(R.id.user_performance);

        User.setText("User ID");
        Perf.setText("Performance");
        container.addView(view);


        for(int i=0;i<len;i++) {
            View view1 = LayoutInflater.from(viewStat.this).inflate(R.layout.singlestat, null);

            //LL = (LinearLayout) findViewById(R.id.singlestat);
            User = (TextView) view1.findViewById(R.id.user_id);
            Perf = (TextView) view1.findViewById(R.id.user_performance);

            User.setText(AllStat[i][0]);
            Perf.setText(AllStat[i][1]);

            container.addView(view1);






        }
        View view2 = LayoutInflater.from(viewStat.this).inflate(R.layout.singlestat, null);

        User = (TextView) view2.findViewById(R.id.user_id);
        Perf = (TextView) view2.findViewById(R.id.user_performance);

        User.setText("User ID");
        Perf.setText("Rounds");
        container.addView(view2);

       String Stat[][]=QDBH.getRoundNo();
        int lenth = Stat.length;

        for(int i=0;i<lenth;i++){
            View view3 = LayoutInflater.from(viewStat.this).inflate(R.layout.singlestat, null);


            User = (TextView) view3.findViewById(R.id.user_id);
            Perf = (TextView) view3.findViewById(R.id.user_performance);
            User.setText(Stat[i][0]);
            Perf.setText(Stat[i][1]);
            container.addView(view3);



        }


    QDBH.close();



    }

}
