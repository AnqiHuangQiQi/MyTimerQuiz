package com.example.huanganqi.myquiz.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.huanganqi.myquiz.data.QuizContract.Questions;
import com.example.huanganqi.myquiz.data.QuizContract.userId;
import com.example.huanganqi.myquiz.data.QuizContract.user_statistic;
/**
 * Created by huanganqi on 2016-09-29.
 */
public class QuizDBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "quiz.db";

    private static final int DATABASE_VERSION = 2;

    private SQLiteDatabase DataB;

    public QuizDBHelper(Context context) {

        //initialize the database helper
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        DataB = sqLiteDatabase;

        final String SQL_USERID_CREATE = "CREATE TABLE "+userId.TABLE_NAME+"(" +
                userId._ID+" INTEGER PRIMARY KEY,"+
                userId.userId+" VARCHAR(20) NOT NULL,"+
                userId.Password+" VARCHAR(20) NOT NULL"+
                " );";
        final String SQL_USERSTAT_CREATE = "CREATE TABLE "+user_statistic.TABLE_NAME+"(" +
                user_statistic._ID+" INTEGER PRIMARY KEY,"+
                user_statistic.userId+" VARCHAR(20) NOT NULL,"+

                user_statistic.Performance+" VARCHAR(20)"+
                " );";
        final String SQL_QUESTION_CREATE = "CREATE TABLE "+Questions.TABLE_NAME+"("+

                Questions.questionId+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Questions.quest_text+" TEXT NOT NULL,"+
                Questions.option1+" TEXT NOT NULL,"+
                Questions.option2+" TEXT NOT NULL,"+
                Questions.option3+" TEXT NOT NULL,"+
                Questions.option4+" TEXT NOT NULL,"+
                Questions.correct+" TEXT NOT NULL,"+
                Questions.time+" INTEGER NOT NULL default 30"+
                " )";

        sqLiteDatabase.execSQL(SQL_USERID_CREATE);
        sqLiteDatabase.execSQL(SQL_USERSTAT_CREATE);
        sqLiteDatabase.execSQL(SQL_QUESTION_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + userId.TABLE_NAME);
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + user_statistic.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Questions.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addUser(String user_id,String passwo){
        DataB = this.getWritableDatabase();
        String ADD_USER = "INSERT INTO "+userId.TABLE_NAME+" ( "+
                userId.userId+", "+userId.Password+") "+
                "VALUES ('"+user_id+"','"+passwo+"')";
        DataB.execSQL(ADD_USER);
        DataB.close();

    }
    public void dropAllUser(){
        DataB = this.getWritableDatabase();
        String DROP_ALL_USER = "DELETE FROM "+userId.TABLE_NAME+";";
        DataB.execSQL(DROP_ALL_USER);
        DataB.close();
    }

    public boolean addQuest(String questions,String option1,String option2,String option3,
                        String option4,String time,String answer){
        DataB = this.getWritableDatabase();
        String ADD_QUES = "INSERT INTO "+Questions.TABLE_NAME+" ( "+
                Questions.quest_text+", "+Questions.option1+", "+
                Questions.option2+", "+Questions.option3+", "+
                Questions.option4+", "+Questions.correct+","+Questions.time+") "+
                "VALUES ('"+questions+"','"+option1+"',"+
                "'"+option2+"','"+option3+"',"+

                "'"+option4+"','"+time+"',"+
                "'"+answer+
                "');";

        try {
            DataB.execSQL(ADD_QUES);
        } catch (SQLException e) {
            DataB.close();
            return false;
        }
        //DataB.execSQL(ADD_QUES);
        DataB.close();
        return true;


    }
    public void dropAllQuest(){
        DataB = this.getWritableDatabase();
        String DROP_ALL_USER = "DELETE FROM "+Questions.TABLE_NAME+";";
        DataB.execSQL(DROP_ALL_USER);
        DataB.close();
    }


    //get total number of questions
    public int getCount(){

        DataB = this.getWritableDatabase();
        String GET_COUNT_QUES = "SELECT COUNT(*) FROM "+Questions.TABLE_NAME+" ;";
        Cursor c = DataB.rawQuery(GET_COUNT_QUES,null);
        c.moveToFirst();
        String num = c.getString(0);
        int count = Integer.parseInt(num);

        c.close();
        //DataB.close();

        return count;


    }

    public String[] getQuesDetail(String id){
        DataB = this.getWritableDatabase();
        String GET_QUES = "SELECT "+Questions.quest_text+" FROM "+Questions.TABLE_NAME+
                " WHERE "+Questions.questionId+"="+id+" ;";
        String GET_O1 = "SELECT "+Questions.option1+" FROM "+Questions.TABLE_NAME+
                " WHERE "+Questions.questionId+"="+id+" ;";
        String GET_O2 = "SELECT "+Questions.option2+" FROM "+Questions.TABLE_NAME+
                " WHERE "+Questions.questionId+"="+id+" ;";
        String GET_O3 = "SELECT "+Questions.option3+" FROM "+Questions.TABLE_NAME+
                " WHERE "+Questions.questionId+"="+id+" ;";
        String GET_O4 = "SELECT "+Questions.option4+" FROM "+Questions.TABLE_NAME+
                " WHERE "+Questions.questionId+"="+id+" ;";
        String GET_AN = "SELECT "+Questions.correct+" FROM "+Questions.TABLE_NAME+
                " WHERE "+Questions.questionId+"="+id+" ;";
        String Time = "SELECT "+Questions.time+" FROM "+Questions.TABLE_NAME+
                " WHERE "+Questions.questionId+"="+id+" ;";

        String[] quests = new String[7];

        Cursor c1 = DataB.rawQuery(GET_QUES,null);
        c1.moveToFirst();
        quests[0] = c1.getString(0);
        Cursor c2 = DataB.rawQuery(GET_O1,null);
        c2.moveToFirst();
        quests[1] = c2.getString(0);
        Cursor c3 = DataB.rawQuery(GET_O2,null);
        c3.moveToFirst();
        quests[2] = c3.getString(0);
        Cursor c4 = DataB.rawQuery(GET_O3,null);
        c4.moveToFirst();
        quests[3] = c4.getString(0);
        Cursor c5 = DataB.rawQuery(GET_O4,null);
        c5.moveToFirst();
        quests[4] = c5.getString(0);
        Cursor c6 = DataB.rawQuery(GET_AN,null);
        c6.moveToFirst();
        quests[5] = c6.getString(0);
        Cursor c7 = DataB.rawQuery(Time,null);
        c7.moveToFirst();
        quests[6] = c7.getString(0);

        DataB.close();
        c1.close();
        c2.close();
        c3.close();c4.close();c5.close();c6.close();c7.close();

        return quests;

    }

    public String[] getQuesId(){
        DataB = this.getWritableDatabase();
        Cursor c = DataB.rawQuery("select "+Questions.questionId+" from "
                +Questions.TABLE_NAME+" ;",null);
        int len = this.getCount();
        c.moveToFirst();
        String num = c.getString(0);
        String Id[] = new String[len];
      for(int i=0;i<len;i++){
          Id[i]=c.getString(0);
          c.moveToNext();
      }

        DataB.close();

        c.close();
        return Id;

    }

    public void addStatistics(String user_id,String Performance){
        DataB = this.getWritableDatabase();
        String ADD_STA = "INSERT INTO "+user_statistic.TABLE_NAME+" ( "+
                user_statistic.userId+", "+user_statistic.Performance+") "+
                "VALUES ('"+user_id+"','"+Performance+

                "');";
        DataB.execSQL(ADD_STA);
        DataB.close();


    }

    public String[][] getAllStat(){
        String[][] AllStat;
        DataB = this.getWritableDatabase();
        String GET_COUNT_STAT = "SELECT COUNT(*) FROM "+user_statistic.TABLE_NAME+" ;";
        Cursor c = DataB.rawQuery(GET_COUNT_STAT,null);
        c.moveToFirst();
        String num = c.getString(0);
        AllStat = new String[Integer.parseInt(num)][2];
        String GET_ALL_STAT = "SELECT "+user_statistic.userId+","+
                user_statistic.Performance+" FROM "+user_statistic.TABLE_NAME+" ;";
        Cursor cc = DataB.rawQuery(GET_ALL_STAT,null);
        cc.moveToFirst();
        for(int i=0;i<Integer.parseInt(num);i++){
            AllStat[i][0]=cc.getString(0);
            AllStat[i][1]=cc.getString(1);
            //Log.v(AllStat[i][0]+"-",AllStat[i][1]);
            cc.moveToNext();
        }

        //DataB.close();
        return AllStat;

    }

    public boolean deleteStat(String user_id){
        boolean bl = false;

        DataB = this.getWritableDatabase();
        String CHECK_HAS = "SELECT COUNT(*) FROM "+user_statistic.TABLE_NAME+
                " WHERE "
                +user_statistic.userId+" = '"+user_id+"' ;";

        String DELETE_STAT = "DELETE FROM "+user_statistic.TABLE_NAME+" WHERE "
                +user_statistic.userId+" = '"+user_id+"' ;";

        Cursor c = DataB.rawQuery(CHECK_HAS,null);
        c.moveToFirst();
        if(c !=null){
            if(Integer.parseInt(c.getString(0))>0){
                DataB.execSQL(DELETE_STAT);
                bl = true;
            }
        }



        DataB.close();

        return bl;
    }

    public boolean checkUser(String user_id,String password){
        boolean flag=false;
        DataB = this.getWritableDatabase();
        String pass;
        String GET_USER_PASS="SELECT "+userId.userId+","+userId.Password+" FROM "+
                userId.TABLE_NAME+" WHERE "+userId.userId+"= '"+user_id+"';";
        Cursor c = DataB.rawQuery(GET_USER_PASS,null);
        c.moveToFirst();
        if(c.getCount()==0 ){

            Log.v("no stat",user_id);
            return false;
        }else{
            pass = c.getString(1);
            if(pass.equals(password)){
                flag = true;
            }else{
                return false;
            }
        }
        DataB.close();
        return flag;
    }

    public String[][] getRoundNo(){
        String[][] Stat;
        int num=0;
        String GET_COUNT_STAT = "SELECT COUNT(*) FROM(SELECT COUNT(*) FROM "+user_statistic.TABLE_NAME
                +" GROUP BY "+user_statistic.userId+") ;";
        Cursor c1 = DataB.rawQuery(GET_COUNT_STAT,null);
        c1.moveToFirst();
        num = Integer.parseInt(c1.getString(0));
        Stat = new String[num][2];



        String GET_ROUND_STAT = "SELECT "+user_statistic.userId+",COUNT(*) FROM "
                +user_statistic.TABLE_NAME+
                " GROUP BY "+user_statistic.userId+" ;";
        Cursor c2 = DataB.rawQuery(GET_ROUND_STAT,null);
        c2.moveToFirst();

        for(int i=0;i<num;i++){
            Stat[i][0]=c2.getString(0);
            Stat[i][1]=c2.getString(1);
            //Log.v(AllStat[i][0]+"-",AllStat[i][1]);
            c2.moveToNext();
        }
        DataB.close();

        return Stat;

    }



}
