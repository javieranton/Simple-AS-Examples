package rabade.anton.javier.mylogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = (TextView) findViewById(R.id.newTextShared);
        tv.setText("Hola " + getSharedPreferencesText());
    }

    public String getSharedPreferencesText(){
        //Instanciamos el Shared Preferences
        SharedPreferences settings = getSharedPreferences("usersXML", Context.MODE_PRIVATE);
        //Consultamos
        String str = settings.getString("user", "USELESS STRING");

        return str;
    }



}
