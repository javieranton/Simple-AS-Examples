package rabade.anton.javier.appdivisas;



import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView divisa1, divisa2, tv_result;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        divisa1 = (TextView) findViewById(R.id.et_divisa1);
        divisa2 = (TextView) findViewById(R.id.et_divisa2);
        tv_result = (TextView) findViewById(R.id.tv_result);
        et = (EditText) findViewById(R.id.editText);

        inicializeDB();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tv_1", divisa1.getText().toString());
        outState.putString("tv_2", divisa2.getText().toString());
        outState.putString("result", tv_result.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        divisa1.setText(savedInstanceState.getString("tv_1"));
        divisa2.setText(savedInstanceState.getString("tv_2"));
        tv_result.setText(savedInstanceState.getString("result"));
    }

    private void inicializeDB() {
        final DatabaseHelper dH = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dH.getWritableDatabase();
        ContentValues valuesToStore = new ContentValues();
        if (dH.getCurrency() == null){
            valuesToStore.put(DatabaseHelper.COLUMN_TABLE_CURRENCY, String.valueOf(1.1));
        }if (dH.getCommission() == null){
            valuesToStore.put(DatabaseHelper.COLUMN_TABLE_COMMISSION, String.valueOf(0.5));
        }if (valuesToStore.size() != 0){
            db.insert(DatabaseHelper.TABLE_NAME, null, valuesToStore);
        }
    }

    public void onClickChangeButton(View view) {
        if(divisa1.getText().equals("Euros")){
            divisa1.setText("Dollars");
            divisa2.setText("to Euros");
        }else{
            divisa1.setText("Euros");
            divisa2.setText("to Dollars");
        }
    }

    public void onClickGoButton(View view) {
        DatabaseHelper dH = new DatabaseHelper(getApplicationContext());
        float currency = Float.parseFloat(dH.getCurrency());
        float commission = Float.parseFloat(dH.getCommission());
        float aux;

        if (divisa1.getText().equals("Euros")){
            aux = Float.parseFloat(et.getText().toString())*currency;
            tv_result.setText((aux-aux*(commission/10))+" Dollars ("+aux+"-"+commission+"% of commission)");
        }else{
            aux = Float.parseFloat(et.getText().toString())*(1/currency);
            tv_result.setText((aux-aux*(commission/10))+" Euros ("+aux+"-"+commission+"% of commission)");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.m_currency:
                currency();
                return true;
            case R.id.m_commission:
                commission();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void currency() {
        DialogFragment dialog = new CurrencyDialogFragment();
        dialog.show(getSupportFragmentManager(), "currency");
    }

    private void commission() {
        DialogFragment dialog = new CommissionDialogFragment();
        dialog.show(getSupportFragmentManager(), "commission");
    }

}
