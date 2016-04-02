package perez.marcos.com.notificationexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    Button b,b1,b2,b3;

    View layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.button);
        b1 = (Button) findViewById(R.id.button1);
        b2 =(Button) findViewById(R.id.button2);
        b3 =(Button) findViewById(R.id.button3);

        layout = findViewById(R.id.layout);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        .setContentTitle("T�tulo")
                        .setContentText("Texto de contenido");


                // Creamos un intent explicito, para abrir la app desde nuestra notificaci�n
                Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);

                //El objeto stack builder contiene una pila artificial para la Acitivty empezada.
                //De esta manera, aseguramos que al navegar hacia atr�s
                //desde la Activity nos lleve a la home screen.

                //Desde donde la creamos
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                // A�ade la pila para el Intent,pero no el intent en s�
                stackBuilder.addParentStack(ResultActivity.class);
                // A�adimos el intent que empieza la activity que est� en el top de la pila
                stackBuilder.addNextIntent(resultIntent);

                //El pending intent ser� el que se ejecute cuando la notificaci�n sea pulsada
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);

                // mId nos permite actualizar las notificaciones en un futuro
                // Notificamos
                mNotificationManager.notify(mId, mBuilder.build());
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos una notificaci�n toast
                //Tenemos que llamar a getApplication context ya que "this" -->
                Toast.makeText(getApplicationContext(), "Soy un toast y sabes implementarme",Toast.LENGTH_LONG).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos una notificaci�n snackbar
                // parentLayout: ViewGroup donde lo queremos mostrar
                // R.string.snackbar_text texto a mostrar definido en strings.xml
                View.OnClickListener myOnClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(),ResultActivity.class);
                        startActivity(i);
                    }
                };

                Snackbar.make(layout, R.string.snackbar_text, Snackbar.LENGTH_LONG)
                        .setAction(R.string.snackbar_action, myOnClickListener)
                        .show(); // Importante!!! No olvidar mostrar la Snackbar.

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Entero que nos permite identificar la notificaci�n
                int mId = 2;
                //Instanciamos Notification Manager
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


                // Para la notificaciones, en lugar de crearlas directamente, lo hacemos mediante
                // un Builder/contructor.
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.ic_launcher)
                                .setContentTitle("T�tulo")
                                .setContentText("Texto de contenido");

                // Creamos un intent explicito, para abrir la app desde nuestra notificaci�n
                Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);

                //El objeto stack builder contiene una pila artificial para la Acitivty empezada.
                //De esta manera, aseguramos que al navegar hacia atr�s
                //desde la Activity nos lleve a la home screen.

                TaskStackBuilder stackBuilder2 = TaskStackBuilder.create(getApplicationContext());
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder2.addParentStack(ResultActivity.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder2.addNextIntent(resultIntent);

                //El pending intent ser� el que se ejecute cuando la notificaci�n sea pulsada
                PendingIntent resultPendingIntent =
                        stackBuilder2.getPendingIntent(
                                1,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);

                // mId nos permite actualizar las notificaciones en un futuro
                // Notificamos
                Notification noti = mBuilder.build();
                noti.flags |= Notification.FLAG_INSISTENT;
                noti.flags |= Notification.FLAG_NO_CLEAR;
                noti.flags |= Notification.FLAG_SHOW_LIGHTS;
                noti.flags |= Notification.FLAG_NO_CLEAR;
                mNotificationManager.notify(mId, noti);
            }
        });









    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
