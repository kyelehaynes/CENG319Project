/*Team Diversity */

package com.example.senzerroom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FirstFragment extends Fragment {

    public FirstFragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_first, container, false);
        //String strText = getArguments().get("tempVal").toString();
        //TextView textView = (TextView) v.findViewById(R.id.tempText);
        //textView.setText(strText);
        return v;
    }

}