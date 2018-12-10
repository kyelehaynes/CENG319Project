/*Team Diversity */

package com.example.senzerroom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.senzerroom.R;

import static com.example.senzerroom.SenzHome.TIME;
import static com.example.senzerroom.SenzHome.VACANT;
import static com.example.senzerroom.SenzHome.TEMP;
import static com.example.senzerroom.SenzHome.LIGHT;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ThirdFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_third, container, false);
        String room = getArguments().getString("numRoom");
        getLight(room);
        return v;
    }

    public void getLight(String room)
    {

        int myRoom = Integer.parseInt(room);
        DocumentReference noteRef = db.collection("Rooms").document("Room" + myRoom);
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                {

                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        if(documentSnapshot.exists())
                        {
                            String aVal = documentSnapshot.getString(LIGHT);
                            TextView textView = getView().findViewById(R.id.LightText);
                            final int light = Integer.parseInt(aVal);
                            if(light == 1)
                            {
                                textView.setTextColor(Color.GREEN);
                                textView.setText("Light is On Do you wish to turn it off?");


                            }
                            if(light == 0)
                            {
                                textView.setTextColor(Color.RED);
                                textView.setText("Light is Off Do you wish to turn it on?");

                            }


                            String timeVal = documentSnapshot.getString(TIME);
                            TextView timeText = getView().findViewById(R.id.timeText);
                            timeText.setTextColor(Color.YELLOW);
                            timeText.setText("Time: " + timeVal);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {

                    }
                });

    }
}