package com.example.huanganqi.myquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huanganqi.myquiz.data.QuizDBHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainPage extends Fragment {

    public MainPage() {
    }

    private String user_id;
    private String password;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.mainpage, container, false);
        final Button login = (Button) view.findViewById(R.id.login);
        final EditText user = (EditText) view.findViewById(R.id.Id);
        final EditText pass = (EditText) view.findViewById(R.id.password);
        final TextView info = (TextView) view.findViewById(R.id.information);





        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                QuizDBHelper QDBH = new QuizDBHelper(getContext());

                user_id = user.getText().toString();
                password = pass.getText().toString();




                if(user_id.equals("huang")){
                    if(password.equals("anqi")){
                        Log.v("huang1",user_id);
                        Log.v("anqi1",password);
                        Intent intent = new Intent(getActivity(),QM_manage.class);
                        intent.putExtra("user_id",user_id);
                        startActivity(intent);
                    }
                }

                boolean flag = QDBH.checkUser(user_id,password);

                if(flag){
                    int[] guoguo=new int[5];
                    for(int i=0;i<5;i++){
                        guoguo[i]=-1;
                    }
                    Intent intent = new Intent(getActivity(),QT_quiz.class);
                    intent.putExtra("user_id",user_id);
                    intent.putExtra("guoguo",guoguo);
                    intent.putExtra("dijige",0);
                    intent.putExtra("mark",0);
                    startActivity(intent);
                }

                if(user_id.equals("jueji")){
                    if(password.equals("yinchen")){

                        Intent intent = new Intent(getActivity(),QT_quiz.class);
                        intent.putExtra("user_id",user_id);
                        int[] guoguo=new int[5];
                        for(int i=0;i<5;i++){
                            guoguo[i]=-1;
                        }
                        intent.putExtra("guoguo",guoguo);
                        intent.putExtra("dijige",0);
                        intent.putExtra("mark",0);
                        startActivity(intent);
                    }
                }

            }

        });
        return view;
    }




}
