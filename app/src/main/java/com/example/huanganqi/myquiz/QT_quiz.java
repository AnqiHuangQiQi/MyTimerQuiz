package com.example.huanganqi.myquiz;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.huanganqi.myquiz.data.QuizDBHelper;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by huanganqi on 2016-10-03.
 */
public class QT_quiz extends Activity {

    private String[] questions = new String[5];
    private String[][] options = new String[5][4];
    //private String[] rightA = new String[5];
    private int[] IdForQ = new int[5];

    private TextView time;
    private TimerTask Task =null;
    private Timer timer =null;
    private int LeftTime=0;
    private int CurrentTime=0;



    int f=0;
    private Runnable rn;
    int QTime=0;

    private RadioGroup RG;
    private RadioButton[] op = new RadioButton[5];

    private String[] currentQ = new String[7];

    private String answers[] = new String[5];

    private int firstTime=0;

    private int mark=0;

    private Animation anim = new AlphaAnimation(0.2f, 1.5f);

    private TextView markshow;

    private int j=0;

    private int[] guoguo;

    private String user_id;

    String Uanswer=" ";

    private QuizDBHelper QDBH;


    TextView questNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qt_quiz);
        Intent intent = getIntent();
        user_id = intent.getStringExtra("user_id");
        guoguo = intent.getIntArrayExtra("guoguo");
        j=intent.getIntExtra("dijige",0);
        mark = intent.getIntExtra("mark",0);

        int Count=0;
        QDBH = new QuizDBHelper(QT_quiz.this);

        TextView user_name = (TextView) findViewById(R.id.user_id);
        user_name.setText(user_id+" : ");

        TextView questtext = (TextView) findViewById(R.id.questtext);
        questNo = (TextView) findViewById(R.id.questNo);
        op[1] = (RadioButton) findViewById(R.id.option1);
        op[2] = (RadioButton) findViewById(R.id.option2);
        op[3] = (RadioButton) findViewById(R.id.option3);
        op[4] = (RadioButton) findViewById(R.id.option4);

        time = (TextView) findViewById(R.id.quiz_time);
        markshow  = (TextView) findViewById(R.id.mark);

        RG = (RadioGroup) findViewById(R.id.optionGroup);


        Count = QDBH.getCount();

        String allId[] = QDBH.getQuesId();

        for(int i=0;i<Count;i++){
            Log.v("allId",allId[i]);
        }


        int start = Integer.parseInt(allId[0]);


        int QID = generateRan(start,Count,guoguo,j);
        //final String QuestionId=allId[QID];
        final String QuestionId=String.valueOf(QID);

        currentQ = getQuestions(String.valueOf(QuestionId));

        //String abc[] = QDBH.getQuesId();
        //int start = Integer.parseInt(abc[0]);
        questtext = (TextView) findViewById(R.id.questtext);
        op[1] = (RadioButton) findViewById(R.id.option1);
        op[2] = (RadioButton) findViewById(R.id.option2);
        op[3] = (RadioButton) findViewById(R.id.option3);
        op[4] = (RadioButton) findViewById(R.id.option4);
        questtext.setText(currentQ[0]);
        //Log.v("Here is", currentQ[0]);
        op[1].setText(currentQ[1]);
        op[2].setText(currentQ[2]);
        op[3].setText(currentQ[3]);
        op[4].setText(currentQ[4]);
        markshow.setText(String.valueOf(mark)+"/"+String.valueOf(j));
        op[1].setEnabled(true);
        op[2].setEnabled(true);
        op[3].setEnabled(true);
        op[4].setEnabled(true);

        for(int i=1;i<=4;i++){
            op[i].setTextColor(Color.BLACK);
        }
        RG.clearCheck();
        questNo.setText("Q"+String.valueOf(j+1));




        rn = new Runnable() {
           @Override
           public void run() {






               if (j <= 4) {         //0,1,2,3,4
                   j++;


                   //Log.v("Current Time",String.valueOf(CurrentTime));
                   QTime =Integer.parseInt(currentQ[5]);
                   Show(QTime);
                   Log.v("QTime ",currentQ[5]);

                   RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                       @Override
                       public void onCheckedChanged(RadioGroup group, int checkedId) {
                           if (CurrentTime>0){
                               if (op[1].isChecked()) {
                                   Uanswer = currentQ[1];
                                   op[2].setEnabled(false);
                                   op[3].setEnabled(false);
                                   op[4].setEnabled(false);
                                   markshow.setText(String.valueOf(mark)+"/"+String.valueOf(j));
                               } else if (op[2].isChecked()) {
                                   Uanswer = currentQ[2];
                                   op[1].setEnabled(false);
                                   op[3].setEnabled(false);
                                   op[4].setEnabled(false);
                                   markshow.setText(String.valueOf(mark)+"/"+String.valueOf(j));
                               } else if (op[3].isChecked()) {
                                   Uanswer = currentQ[3];
                                   op[2].setEnabled(false);
                                   op[1].setEnabled(false);
                                   op[4].setEnabled(false);
                                   markshow.setText(String.valueOf(mark)+"/"+String.valueOf(j));
                               } else if (op[4].isChecked()) {
                                   Uanswer = currentQ[4];
                                   op[2].setEnabled(false);
                                   op[3].setEnabled(false);
                                   op[1].setEnabled(false);
                                   markshow.setText(String.valueOf(mark)+"/"+String.valueOf(j));
                               }
                           }else Uanswer=" ";

                           if(Uanswer.equals(currentQ[6])){

                               mark++;
                               markshow.setText(String.valueOf(mark)+"/"+String.valueOf(j));
                               QTime=0;

                               if(j<=4){
                                   Intent intent = new Intent(QT_quiz.this,QT_quiz.class);
                                   intent.putExtra("user_id",user_id);
                                   intent.putExtra("guoguo",guoguo);
                                   intent.putExtra("dijige",j);
                                   intent.putExtra("mark",mark);
                                   mHandler.removeCallbacks(rn);
                                   mHandler.removeCallbacks(r1);

                                   startActivity(intent);
                               }
                               if(j==5){
                                   j++;
                                   Log.v("flag",String.valueOf(f));

                                   //mHandler.removeCallbacks(this);

                                   Intent intent = new Intent(QT_quiz.this, transfer.class);
                                   intent.putExtra("user_id",user_id);
                                   intent.putExtra("Mark",String.valueOf(mark)+"/5");
                                   Log.v("finalMark",String.valueOf(mark)+"/5");
                                   //intent.putExtra("userName",userName);

                                   //mHandler.removeCallbacks(this);
                                   mHandler.removeCallbacks(rn);
                                   mHandler.removeCallbacks(r1);

                                   QDBH.close();
                                   startActivity(intent);

                                   Log.v("why not jump", "a");
                               }




                           }else if(answers[f] != " "){
                               if (op[1].isChecked()) {
                                   op[1].setTextColor(Color.RED);


                               } else if (op[2].isChecked()) {
                                   op[2].setTextColor(Color.RED);

                               } else if (op[3].isChecked()) {
                                   op[3].setTextColor(Color.RED);
                               } else if (op[4].isChecked()) {
                                   op[4].setTextColor(Color.RED);
                               }

                                   for(int i=1;i<=4;i++){
                                       if(currentQ[i].equals(currentQ[6])){
                                           op[i].setTextColor(Color.GREEN);

                                            setupAnim();
                                           //Log.v("blink",String.valueOf(CurrentTime));
                                           op[i].startAnimation(anim);
                                           //Log.v("blink-",String.valueOf(CurrentTime));


                                       }
                                   }


                           }
                       }

                   });



               }



           }
       };

        mHandler.postDelayed(rn, (QTime+1)*1000);





    }

    public String[] getQuestions(String seq){
       // int Count=0;
        QuizDBHelper QDBH = new QuizDBHelper(QT_quiz.this);

        String[] bcd = new String[7];
        bcd = QDBH.getQuesDetail(seq);


        return bcd;

    }
/////////////////////////////////////////////////////////////////
    //Limit the time of one question

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {

            time.setText(msg.arg1);



        };

    };


    Runnable r1;
    //do this update every second
    void Show(int num) {

        CurrentTime = num;
        time.setText(String.valueOf(CurrentTime));


        r1 = new Runnable() {
            @Override
            public void run() {
                if (CurrentTime > 0) {
                    Log.v("CurrentTime-Show", String.valueOf(CurrentTime));
                    time.setText(String.valueOf(CurrentTime--)+" sec");
                    mHandler.postDelayed(this, 1000);



                } else if (CurrentTime == 0) {


                    time.setText("TimeOut!!!");
                    mHandler.removeCallbacks(this);
                        //Log.v("CurrentTime","=1");
                        if(Uanswer.equals(" ")) {
                            for (int i = 1; i <= 4; i++) {


                                if (currentQ[i].equals(currentQ[6])) {
                                    op[i].setTextColor(Color.GREEN);
                                    markshow.setText(String.valueOf(mark)+"/"+String.valueOf(j));
                                    if(j<=4) {
                                        Intent intent = new Intent(QT_quiz.this, QT_quiz.class);
                                        intent.putExtra("user_id", user_id);
                                        intent.putExtra("guoguo", guoguo);
                                        intent.putExtra("dijige", j);
                                        intent.putExtra("mark", mark);
                                        mHandler.removeCallbacks(this);
                                        mHandler.removeCallbacks(rn);
                                        startActivity(intent);
                                    }

                                    if(j==5){
                                            j++;
                                            Log.v("flag",String.valueOf(f));

                                            //mHandler.removeCallbacks(this);

                                            Intent intent = new Intent(QT_quiz.this, transfer.class);
                                            intent.putExtra("user_id",user_id);
                                            intent.putExtra("Mark",String.valueOf(mark)+"/5");
                                            Log.v("finalMark",String.valueOf(mark)+"/5");
                                            //intent.putExtra("userName",userName);

                                            //mHandler.removeCallbacks(this);
                                        mHandler.removeCallbacks(this);
                                        mHandler.removeCallbacks(rn);

                                            QDBH.close();
                                            startActivity(intent);

                                            Log.v("why not jump", "a");
                                    }

                                    break;
                                }

                            }
                        }else{
                            if(j<=4) {
                                Intent intent = new Intent(QT_quiz.this, QT_quiz.class);
                                intent.putExtra("user_id", user_id);
                                intent.putExtra("guoguo", guoguo);
                                intent.putExtra("dijige", j);
                                intent.putExtra("mark", mark);
                                mHandler.removeCallbacks(this);
                                startActivity(intent);
                            }

                            if(j==5){
                                    j++;
                                    Log.v("flag",String.valueOf(f));

                                    //mHandler.removeCallbacks(this);

                                    Intent intent = new Intent(QT_quiz.this, transfer.class);
                                    intent.putExtra("user_id",user_id);
                                    intent.putExtra("Mark",String.valueOf(mark)+"/5");
                                    Log.v("finalMark",String.valueOf(mark)+"/5");
                                    //intent.putExtra("userName",userName);

                                    //mHandler.removeCallbacks(this);this
                                    mHandler.removeCallbacks(this);

                                    QDBH.close();
                                    startActivity(intent);

                                    Log.v("why not jump", "a");
                            }

                        }




                }
            }
        };
        //mHandler.postDelayed(rn, 5 * 1000);
        mHandler.postDelayed(r1, 0);



    }

    @Override
    protected void onDestroy() {

        QuizDBHelper QDBH = new QuizDBHelper(QT_quiz.this);
        QDBH.close();
        mHandler.removeCallbacks(r1);
        mHandler.removeCallbacks(rn);

        super.onDestroy();
    }

    public int generateRan(int start,int Count,int[] guoguo,int j){
        double k=0;
        int kk=0;
        int count=0;
        k = Math.random() * Count + start;
        kk = (int)k;
        for(int i=0;i<5;i++){
            if(kk!=guoguo[i]){
                count++;
            }
        }



        if(count==5){
            guoguo[j]=kk;
            return kk;
        }else{
            kk=generateRan(start,Count,guoguo,j);
            guoguo[j]=kk;
            return kk;
        }


    }

    public void setupAnim() {

        anim.setDuration(33);
        //anim.setStartOffset(33);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(2);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }





}
