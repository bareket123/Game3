package us.game3;


        import android.app.Activity;
        import android.content.Intent;
        import android.media.Image;
        import android.os.Bundle;
        import android.text.Layout;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener{
    TextView finalScoreText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button buttonPlay = (Button)findViewById(R.id.buttonPlay);
        final Button finalScoreButton = (Button)findViewById(R.id.button2);
        finalScoreText=findViewById(R.id.finalScore);
        finalScoreButton.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);

        // add quit listener
        Button button3 =(Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.buttonPlay:
                 Intent i = new Intent(this, GameActivity.class);
                startActivity(i);

                break;
                case R.id.button2:
                   Intent intent=getIntent();
                    int finalScore=intent.getIntExtra("score",0);
                    finalScoreText.setText("score: " + finalScore);



                break;
                case R.id.button3:
                finish();
                System.exit(0);
                break;




        }


    }

}






