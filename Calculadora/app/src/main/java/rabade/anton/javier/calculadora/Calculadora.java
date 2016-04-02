package rabade.anton.javier.calculadora;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Classes.Operation;

public class Calculadora extends AppCompatActivity {

    private Float ans;
    private TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        tx = ((TextView)findViewById(R.id.textView));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("result", tx.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tx.setText(savedInstanceState.getString("result"));
    }

    public void onClickSimbol(View v) {
        CharSequence simbol = ((Button) v).getText();
        if (Character.isDigit(tx.getText().charAt(tx.getText().length() - 1)) || (tx.getText().charAt(tx.getText().length() - 1) == ')') ) {
            tx.append(simbol);
        }else if(!Character.isDigit(tx.getText().charAt(0)) && (tx.getText().charAt(0) != '-')){
            tx.setText(simbol);
        }else{
            CharSequence sequence = tx.getText().toString().substring(0, tx.getText().length()-1);
            sequence = sequence.toString() + simbol;
            tx.setText(sequence);
        }
    }

    public void onClickRestar(View v){
        String s = tx.getText().toString();
        if(s.equals("RESULT")){
            tx.setText("-");
        }else if(s.endsWith(".")){
            s = s.substring(0, s.length()-1) + "-";
        }else{
            tx.append("-");
        }
    }

    public void onClickNumber(View view) {
        CharSequence s= ((Button) view).getText();
        if(s.equals(" ")){
            tx.setText(s);
        }else if ( (!Character.isDigit(tx.getText().charAt(0))) && !(tx.getText().charAt(0) == '-') && !(tx.getText().charAt(0) == '(') ){
            tx.setText(s);
        }else{
            tx.append(s);
        }
    }

    public void onClickDot(View view) {
        if(!tx.getText().toString().contains(".")){
            onClickSimbol(view);
        }
    }

    public void onClickClean(View view) {
        tx.setText(" ");
    }

    public void onClickEqual(View view) {
        operate();
    }

    public void onClickCall(View view) {
        String str= (String) ( "tel:"+tx.getText());
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(str));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.calculadora_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_toast:
                toast();
                break;
            case R.id.action_snakcbar:
                snackbar();
                break;
            case R.id.action_statusbar:
                statusbar();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void statusbar() {

    }


    private void snackbar() {
        //Entero que nos permite identificar la notificaci�n
        int mId = 1;
        //Instanciamos Notification Manager
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        // Para la notificaciones, en lugar de crearlas directamente, lo hacemos mediante
        // un Builder/contructor.
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Título")
                        .setContentText("Texto de contenido");

        // mId nos permite actualizar las notificaciones en un futuro
        // Notificamos
        mNotificationManager.notify(mId, mBuilder.build());
    }

    private void toast() {
        //Creamos una notificaci�n toast
        //Tenemos que llamar a getApplication context ya que "this" -->
        Toast toast= Toast.makeText(getApplicationContext(), "Soy un toast y sabes implementarme", Toast.LENGTH_LONG);
        toast.show();
    }

    public void operate(){
        Operation op = new Operation();
        String solution = op.solution(tx.getText().toString());

        tx.setText(solution);
    }


    public void onClickAns(View view) {
    }
}

