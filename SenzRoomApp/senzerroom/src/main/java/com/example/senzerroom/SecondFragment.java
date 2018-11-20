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


public class SecondFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        String room = getArguments().getString("numRoom");
        getTemp(room);
        return v;
    }

    public void getTemp(String room)
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
                            String aVal = documentSnapshot.getString(TEMP);
                            TextView textView = getView().findViewById(R.id.tempText);
                            int temp = Integer.parseInt(aVal);
                            if(temp > 35)
                            {
                                textView.setTextColor(Color.RED);
                            }
                            if(temp < 35)
                            {
                                textView.setTextColor(Color.BLUE);
                            }
                            if(temp == 35)
                            {
                                textView.setTextColor(Color.GREEN);
                            }
                            textView.setText("Room Temperature: " + aVal + " Degrees Celcius");
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
