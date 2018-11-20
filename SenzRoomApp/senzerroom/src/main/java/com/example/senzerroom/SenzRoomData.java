
/*Team Diversity */

package com.example.senzerroom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.senzerroom.SenzHome.TIME;
import static com.example.senzerroom.SenzHome.VACANT;
import static com.example.senzerroom.SenzHome.TEMP;
import static com.example.senzerroom.SenzHome.LIGHT;



public class SenzRoomData extends AppCompatActivity
{

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senz_room_data);
        int roomNum;

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
             roomNum = bundle.getInt("senzRoom");
            //extView.setText(textId);
            this.setTitle("Senz Room " + roomNum );
            //textView.setTextColor(Color.RED);
        } else{roomNum = 1;}

        getData(roomNum);
        bundle.putString("numRoom", Integer.toString(roomNum));
        //bundle.putString("senzTime", TIME);
        //bundle.putString("vacancy", VACANT);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Vacancy"));
        tabLayout.addTab(tabLayout.newTab().setText("Temperature"));
        tabLayout.addTab(tabLayout.newTab().setText("Light"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), bundle);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void getData(int roomNum)
    {
        DocumentReference noteRef = db.collection("Rooms").document("Room" + roomNum);

        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
                {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot)
                    {
                        String time;
                        String temp;
                        String vacancy;
                        String light;
                        if(documentSnapshot.exists())
                        {
                            time = documentSnapshot.getString(TIME);
                            vacancy = documentSnapshot.getString(VACANT);
                            light = documentSnapshot.getString(LIGHT);
                            temp = documentSnapshot.getString(TEMP);

                        }
                        else
                        {
                            Toast.makeText(SenzRoomData.this, "NO Data", Toast.LENGTH_SHORT).show();

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

    public void sendVac(String time, String vacancy)
    {
        Bundle bundle = getIntent().getExtras();
        bundle.putString("senzTime", time);
        bundle.putString("vacancy", vacancy);

    }

    public String myVal(String time, int numRooms)
    {
        DocumentSnapshot documentSnapshot = null;
        if(documentSnapshot.exists())
        {
            time = documentSnapshot.getString(TIME);
        }
        return time + Integer.toString(numRooms);
    }

}
