package rabade.anton.javier.appdivisas;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


/**
 * Created by Javier on 23/02/2016.
 */
public class CommissionDialogFragment extends DialogFragment {
    DatabaseHelper dH;
    EditText et;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //The dataBase
        dH = new DatabaseHelper(getActivity().getApplicationContext());
        //The layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_layout, null);
        et = ((EditText) v.findViewById(R.id.et_newValor));
        //Use the builder to create a new AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Actual commission: "+ dH.getCommission())
        .setView(v)
        .setPositiveButton("Cambiar", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CharSequence ch = et.getText();
                SQLiteDatabase db = dH.getWritableDatabase();
                try{
                    float newValor = 0;
                    newValor = Float.parseFloat(ch.toString());
                    db.execSQL("UPDATE "+DatabaseHelper.TABLE_NAME+" SET "+DatabaseHelper.COLUMN_TABLE_COMMISSION+"="+newValor+" WHERE "+
                            DatabaseHelper.COLUMN_TABLE_CURRENCY+"='"+dH.getCurrency()+"'");
                }catch (Exception e){}
                //Aceptar
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cancelar
            }
        });
        return builder.create();
    }
}
