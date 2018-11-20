/*Team Diversity */

package com.example.senzerroom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.senzerroom.SenzHome.TIME;
import static com.example.senzerroom.SenzHome.VACANT;
import static com.example.senzerroom.SenzHome.TEMP;
import static com.example.senzerroom.SenzHome.LIGHT;

public class FirstFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FirstFragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_first, container, false);
       // TextView text = v.findViewById(R.id.vacancyText);
        //TextView timeText = v.findViewById(R.id.timeText);
        String room = getArguments().getString("numRoom");
        getVac(room);
        return v;
    }

    public void getVac(String room)
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
                           String aVal = documentSnapshot.getString(VACANT);
                           TextView textView = getView().findViewById(R.id.vacancyText);
                           int vacancy = Integer.parseInt(aVal);
                           if(vacancy == 1)
                           {
                               textView.setTextColor(Color.GREEN);
                               textView.setText("Vacancy: " + aVal + "(Room is Occupied)");
                           }
                           else
                           {
                               textView.setTextColor(Color.RED);
                               textView.setText("Vacancy: " + aVal + "(Room is Empty)");
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