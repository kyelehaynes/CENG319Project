
/*Team Diversity */

package com.diversity.senzerroom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.Toast;

import com.example.senzerroom.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.diversity.senzerroom.SenzHome.LIGHT;



public class SenzRoomData extends AppCompatActivity
{

    private FirebaseAuth mFbAuth;
    private FirebaseUser mFbUsr;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senz_room_data);
        final int roomNum;
        final int finalRoomNum;

        mFbAuth = FirebaseAuth.getInstance();
        mFbUsr = mFbAuth.getCurrentUser();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
        {
             roomNum = bundle.getInt("senzRoom");
            //extView.setText(textId);
            this.setTitle("Senz Room " + roomNum );
            //textView.setTextColor(Color.RED);
        } else{roomNum = 1;}


        bundle.putString("numRoom", Integer.toString(roomNum));
        //bundle.putString("senzTime", TIME);
        //bundle.putString("vacancy", VACANT);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Vacancy"));
        tabLayout.addTab(tabLayout.newTab().setText("Temperature"));
        tabLayout.addTab(tabLayout.newTab().setText("Light"));

        if (mFbUsr == null){

        }else{
            tabLayout.addTab(tabLayout.newTab().setText("Admin"));
        }

        finalRoomNum = roomNum;
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
                getData(finalRoomNum);
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
                        if(documentSnapshot.exists())
                        {
                            String aVal = documentSnapshot.getString(LIGHT);
                            int lightVal = Integer.parseInt(aVal);
                            Intent intent = new Intent("my-event");
                            intent.putExtra("light", lightVal);

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
}
