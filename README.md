# MyTimedQuiz

This is an Android native application, which is used to arrange tests for target groups.     
Two kinds of users: Quiz Manager & Quiz Taker.   
     
<img src="https://github.com/AntheaHuang/MyTimerQuiz/blob/master/screenshots/login.png" width="20%" height="20%">        

Quiz Taker:          
  1)Each question has a predetermined time and the timer is on the top of the screen;      
  2)The quiz taker must choose an option within this time; 
        
<img src="https://github.com/AntheaHuang/MyTimerQuiz/blob/master/screenshots/quiz.png" width="20%" height="20%">          
  3)Each round has 5 questions in total;     
  4)The app will randomly choose 5 questions from the questions' database;     
  5)If the quiz taker chooses the correct answer, it will automatically jump to the next question;     
  6)Otherwise, if a wrong answer is chosen or the time is up, the correct answer will flash green;     
  7)At the end of one round, the score of this round will be stored in the SQLite database;   
       
<img src="https://github.com/AntheaHuang/MyTimerQuiz/blob/master/screenshots/end.png" width="20%" height="20%">     
  8)Meanwhile, the user can choose to exit or to continue doing another round of quiz.     
         
Quiz Manager:      
<img src="https://github.com/AntheaHuang/MyTimerQuiz/blob/master/screenshots/manager.png" width="20%" height="20%">  
     
  1)A manager has a right to add new questions and decide the time for each question;   
<img src="https://github.com/AntheaHuang/MyTimerQuiz/blob/master/screenshots/add_question.png" width="20%" height="20%">  
     
  2)A manager can create a new account for the new user;   
<img src="https://github.com/AntheaHuang/MyTimerQuiz/blob/master/screenshots/add_user.png" width="20%" height="20%">  
     
  3)A manager can check the performance of all quiz takers and delete their records;     
<img src="https://github.com/AntheaHuang/MyTimerQuiz/blob/master/screenshots/viewstat.png" width="20%" height="20%">   
    
Techniques:     
Android Studio, SQLite, Java, XML, Runnable, Handler.     
      
This project is written by Anqi Huang in Oct 2016.     
