package com.example.lr1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class KeyboardFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keyboard, container, false);
        Button button1 = view.findViewById(R.id.button1);
        Button button2 = view.findViewById(R.id.button2);
        Button button3 = view.findViewById(R.id.button3);
        Button button4 = view.findViewById(R.id.button4);
        Button button5 = view.findViewById(R.id.button5);
        Button button6 = view.findViewById(R.id.button6);
        Button button7 = view.findViewById(R.id.button7);
        Button button8 = view.findViewById(R.id.button8);
        Button button9 = view.findViewById(R.id.button9);
        Button button0 = view.findViewById(R.id.button0);
        Button buttonDot = view.findViewById(R.id.buttonDot);
        Button buttonBackspace = view.findViewById(R.id.buttonBackspace);

        View.OnClickListener inputClickListener = new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), view.getTag().toString(), Toast.LENGTH_LONG);
                updateInput(view.getTag().toString());
            }
        };

        button1.setOnClickListener(inputClickListener);
        button2.setOnClickListener(inputClickListener);
        button3.setOnClickListener(inputClickListener);
        button4.setOnClickListener(inputClickListener);
        button5.setOnClickListener(inputClickListener);
        button6.setOnClickListener(inputClickListener);
        button7.setOnClickListener(inputClickListener);
        button8.setOnClickListener(inputClickListener);
        button9.setOnClickListener(inputClickListener);
        button0.setOnClickListener(inputClickListener);
        buttonDot.setOnClickListener(inputClickListener);
        buttonBackspace.setOnClickListener(inputClickListener);

        return view;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            mListener = (OnFragmentInteractionListener)context;

        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }

    }

    interface OnFragmentInteractionListener{
        void onFragmentInteraction(String symbol);

    }

    public void updateInput(String tag){
        if (tag.equals(".")) {
            mListener.onFragmentInteraction(".");
        } else if (tag.equals("Backspace")) {
            mListener.onFragmentInteraction("-1");
        } else {
            mListener.onFragmentInteraction(tag);
        }
    }
}