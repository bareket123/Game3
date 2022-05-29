package us.game3;

        import android.app.Activity;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.view.menu.ActionMenuItemView;

        import java.util.Random;


public class GameActivity extends Activity implements View.OnClickListener{

    int correctAnswer;
    Button buttonObjectChoice1;
    Button buttonObjectChoice2;
    Button buttonObjectChoice3;
    Button backToMain;
    TextView textObjectPartA;
    TextView textObjectPartB;
    TextView textObjectScore;

     int currentScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);
        textObjectPartA = findViewById(R.id.textPartA);
        textObjectPartB = findViewById(R.id.textPartB);
        textObjectScore = findViewById(R.id.textScore);
        buttonObjectChoice1 = findViewById(R.id.buttonChoice1);
        buttonObjectChoice2 = findViewById(R.id.buttonChoice2);
        buttonObjectChoice3 = findViewById(R.id.buttonChoice3);
        backToMain=findViewById(R.id.backToMain);
        backToMain.setOnClickListener(this);
        buttonObjectChoice1.setOnClickListener(this);
        buttonObjectChoice2.setOnClickListener(this);
        buttonObjectChoice3.setOnClickListener(this);

        setQuestion();

    }//onCreate ends here

    @Override
    public void onClick(View view) {
        int answerGiven=0;
        switch (view.getId()) {

            case R.id.buttonChoice1:
                //initialize a new int with the value contained in buttonObjectChoice1
                //Remember we put it there ourselves previously
                answerGiven = Integer.parseInt("" + buttonObjectChoice1.getText());

                break;

            case R.id.buttonChoice2:
                answerGiven = Integer.parseInt("" + buttonObjectChoice2.getText());
                break;

            case R.id.buttonChoice3:
                answerGiven = Integer.parseInt("" + buttonObjectChoice3.getText());
                break;
            case R.id.backToMain:
               Intent i;
                i =  new Intent(this, MainActivity.class);
                i.putExtra("score",currentScore);
                startActivityForResult(i,1);
                  finish();

                break;


        }
        updateScoreAndLevel(answerGiven);
        setQuestion();


    }

    void setQuestion(){
        int numberRange = 11;
        Random randInt = new Random();
        int partA,partB;
        String wrongAnswer1,wrongAnswer2;

        do {
            partA = randInt.nextInt(numberRange)+15;
            partA++;

             partB = randInt.nextInt(numberRange)+10;
            partB++;

            correctAnswer = partA * partB;

             wrongAnswer1 = String.valueOf(partA)+String.valueOf(correctAnswer%10) ;
             wrongAnswer2 = String.valueOf(partB)+String.valueOf(correctAnswer%10);


        }while (wrongAnswer1.equals(wrongAnswer2));


        textObjectPartA.setText(""+partA);
        textObjectPartB.setText(""+partB);

        //set the multi choice buttons
        //A number between 0 and 2
        int buttonLayout = randInt.nextInt(3);
        switch (buttonLayout){
            case 0:
                buttonObjectChoice1.setText(""+correctAnswer);
                buttonObjectChoice2.setText(""+wrongAnswer1);
                buttonObjectChoice3.setText(""+wrongAnswer2);
                break;

            case 1:
                buttonObjectChoice2.setText(""+correctAnswer);
                buttonObjectChoice3.setText(""+wrongAnswer1);
                buttonObjectChoice1.setText(""+wrongAnswer2);
                break;

            case 2:
                buttonObjectChoice3.setText(""+correctAnswer);
                buttonObjectChoice1.setText(""+wrongAnswer1);
                buttonObjectChoice2.setText(""+wrongAnswer2);
                break;
        }
    }


    void updateScoreAndLevel(int answerGiven){
        if (!backToMain.isPressed()){
            if(isCorrect(answerGiven) ){
                currentScore++;

            }else{
                currentScore = 0;


            }

        }

        textObjectScore.setText("Score: " + currentScore);


    }

    boolean isCorrect(int answerGiven){
        boolean correctTrueOrFalse;
        if(answerGiven == correctAnswer){
            Toast.makeText(getApplicationContext(), "Well done!", Toast.LENGTH_LONG).show();
            correctTrueOrFalse=true;
        }else{//Uh-oh!
            Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_LONG).show();
            correctTrueOrFalse=false;
        }

        return correctTrueOrFalse;
    }

}
