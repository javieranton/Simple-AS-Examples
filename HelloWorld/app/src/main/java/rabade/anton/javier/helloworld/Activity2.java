package rabade.anton.javier.helloworld;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class Activity2 extends AppCompatActivity {

    Button theButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

/*        theButton = (Button) findViewById(R.id.button_THE);
        theButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Random ran = new Random();
                ((Button) v).setText(ran.nextInt()+ "");

            }
        });*/
    }

    public void changeColor(View v){
        v.setBackgroundColor(Color.CYAN);
    }
}
