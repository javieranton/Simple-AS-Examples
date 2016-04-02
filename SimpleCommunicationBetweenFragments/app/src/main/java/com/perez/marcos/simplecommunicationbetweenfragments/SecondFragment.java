package com.perez.marcos.simplecommunicationbetweenfragments;

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


public class SecondFragment extends Fragment implements View.OnClickListener{
    TextView textView;
    EditText editText;
    Button b;
    private OnFragmentInteractionListener mListener;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        textView = (TextView) rootView.findViewById(R.id.textView2);
        editText = (EditText) rootView.findViewById(R.id.editText2);
        b = (Button) rootView.findViewById(R.id.button2);
        b.setOnClickListener(this);
       
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
            case R.id.button2:
                if (mListener != null) {
                    //En este caso, si no hay nada escrito en el EditText,
                    //pondremos el String vació ""
                    mListener.onFragmentInteraction(editText.getText().toString(),2);
                }else {
                    Log.v("Fragment1", "No listener attached");
                }
                break;
            default:
                Log.v("Fragment2","Not a button");
        }
    }
}
