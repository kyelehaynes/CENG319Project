/*Team Diversity */

package com.diversity.senzerroom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.senzerroom.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AdminFragment extends Fragment {

    //FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.admin_fragment, container, false);
        String room = getArguments().getString("numRoom");
        getLight(room);
        return v;
    }

    public void getLight(String room)
    {
        final int myRoom = Integer.parseInt(room);
        FirebaseApp.initializeApp(getActivity());
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        final Map<String,Object> data = new HashMap<>();

        DocumentReference noteRef = db.collection("Rooms").document("Room" + myRoom);
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        final Button button = getView().findViewById(R.id.switchButton);
                        String aVal = documentSnapshot.getString(SenzHome.LIGHT);
                        final int light = Integer.parseInt(aVal);
                        if(light == 1)
                        {
                            button.setText("Turn off");
                        }
                        if(light == 0)
                        {
                            button.setText("Turn On");
                        }
                        button.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                if(light == 1)
                                {
                                    data.put(SenzHome.LIGHT,"0");

                                    db.collection("Rooms").document("Room" + myRoom).update(data)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getActivity(), getResources().getString(R.string.LightOff), Toast.LENGTH_SHORT).show();
                                                }
                                            });


                                    //button.setText("Turn off");
                                }
                                if(light == 0)
                                {
                                    data.put(SenzHome.LIGHT,"1");

                                    db.collection("Rooms").document("Room" + myRoom).update(data)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getActivity(), getResources().getString(R.string.LightOn), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    //button.setText("Turn On");
                                }
                            }
                        });
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
