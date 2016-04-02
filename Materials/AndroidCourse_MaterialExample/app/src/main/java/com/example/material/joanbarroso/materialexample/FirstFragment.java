package com.example.material.joanbarroso.materialexample;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.Override;
import java.lang.RuntimeException;


public class FirstFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;
    TextView textView;
    EditText editText;
    Button b;

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_first, container, false);

        textView = (TextView) rootView.findViewById(R.id.textView1);
        editText = (EditText) rootView.findViewById(R.id.editText1);
        b = (Button) rootView.findViewById(R.id.button1);
        b.setOnClickListener(this);

        //Recuperamos los argumentos
        Bundle args = this.getArguments();
        if (args != null){
            textView.setText(args.getString("message"));
        }else{
            textView.setText("No args provided :( ");
        }
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //Importante! Aquí no se puede usar el método del "onClick" en xml
    // por que referencia a la Activity y no al fragment!!
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                if (mListener != null) {
                    //En este caso, si no hay nada escrito en el EditText,
                    //pondremos el String vació ""
                    mListener.onFragmentInteraction(editText.getText().toString(),1);
                }else {
                    Log.v("Fragment1", "No listener attached");
                }
                break;
            default:
                Log.v("Fragment1","Not a button");
        }
    }
}
