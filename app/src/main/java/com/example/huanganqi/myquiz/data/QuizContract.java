package com.example.huanganqi.myquiz.data;

import android.provider.BaseColumns;

/**
 * Created by huanganqi on 2016-09-29.
 */
public class QuizContract {
    public static final class userId implements BaseColumns {
        public static final String TABLE_NAME = "QT_User";

        public static final String userId = "user_Id";
        public static final String Password="password";


    }
    public static final class user_statistic implements BaseColumns {
        public static final String TABLE_NAME = "User_stat";

        public static final String userId = "user_Id";
        public static final String RoundNo = "RoundNo";
        public static final String Performance="Performance";


    }
    public static final class Questions implements BaseColumns {
        public static final String TABLE_NAME = "Questions";

        public static final String questionId = "question_Id";
        public static final String quest_text="quest_text";
        public static final String option1 = "option1";
        public static final String option2 = "option2";
        public static final String option3 = "option3";
        public static final String option4 = "option4";
        public static final String correct = "correct";
        public static final String time = "time";



    }
}
