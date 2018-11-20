
/*Team Diversity */

package com.example.senzerroom;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class SenzHome extends AppCompatActivity implements OnNavigationItemSelectedListener
{

            private static final String TAG = "SenzHome";
            public static final String TIME = "Time";
            public static final String VACANT = "Vacant";
            public static final String TEMP = "Temperature";
            public static final String LIGHT = "Lights";



    DrawerLayout drawer;
    int numRooms;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_senz_home);

        ImageButton temp; //temp ImageButton used in for loop

        //Array of TextViews to be displayed on screen


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        setNavigationViewListener();
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //Main Homescreen layout
        final LinearLayout mainLayout = findViewById(R.id.MainLayout);
        final Button myButton;
        final Button aButton;
        final Button secondButton;
        aButton = new Button(SenzHome.this);
        aButton.setId(R.id.secondButton);
        aButton.setText("Add Rooms");
        aButton.setLayoutParams(new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mainLayout.addView(aButton);
        Intent intent = getIntent();
        numRooms = intent.getIntExtra("roomAmount", 0);
        aButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent newIntent = new Intent(SenzHome.this, SenzSettings.class);
                startActivity(newIntent);
            }
        });
            if(numRooms != 0)
            {
                TextView view = new TextView(SenzHome.this);
                view.setText("Number of Rooms: " + numRooms);
                view.setTextColor(getResources().getColor(R.color.white));
                view.setTextSize(18);
                view.setVisibility(View.VISIBLE);
                view.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                ));
                mainLayout.addView(view);
                ImageButton[] rooms = new ImageButton[numRooms];
                //temp ImageButton used in for loop

                //Array of TextViews to be displayed on screen
                TextView[] roomText = new TextView[numRooms];
                TextView tempText; //temp ImageButton used in for loop

                ArrayList<ImageButton> imgButton = new ArrayList<>(Arrays.asList(rooms));
                ArrayList<TextView> textViewArrayList = new ArrayList<>(Arrays.asList(roomText));
                for (int i = 0; i < numRooms; i++)
                {
                    temp = new ImageButton(SenzHome.this);
                    tempText = new TextView(SenzHome.this);

                    //Setting parameters for the ImageButton
                    temp.setImageResource(R.drawable.room);
                    temp.setId(i);
                    temp.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    ));

                    //Setting parameters for the TextView
                    tempText.setText("Room " + (i + 1));
                    tempText.setTextColor(getResources().getColor(R.color.white));
                    tempText.setTextSize(18);
                    tempText.setId(i + 10);
                    tempText.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                    ));

                    //adding the current temp ImageButton and TextView to their respective arrays
                    rooms[i] = temp;
                    roomText[i] = tempText;
                    mainLayout.addView(roomText[i]);
                    mainLayout.addView(rooms[i]);

                    rooms[i].setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            generateData(numRooms);
                            for (int i = 0; i < numRooms; i++)
                            {
                                if (v.getId() == i)
                                {
                                    Intent intent = new Intent(SenzHome.this, SenzRoomData.class);
                                    Random rnd = new Random();
                                    int n = 1 + rnd.nextInt(1000);
                                    intent.putExtra("senzRoom", (i + 1)).putExtra("numRooms", numRooms);

                                    startActivity(intent);

                                }
                            }
                        }
                    });

                }

            }

    }





    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        switch(menuItem.getItemId())
        {
            case R.id.admin:
                                Intent intent = new Intent(SenzHome.this, LoginActivity.class);
                                startActivity(intent);
                                break;
            case R.id.settings:
                                Intent intent1 = new Intent(SenzHome.this, SenzSettings.class);
                                startActivity(intent1);
                                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setNavigationViewListener()
    {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(false);
    }

    public void deleteRoom()
    {

    }

    public void generateData(int numRooms){
            FirebaseApp.initializeApp(SenzHome.this);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Map<String,Object> data = new HashMap<>();

            for (int i=1;i<=numRooms;i++){

                data.put(TIME,randomTime());
                data.put(VACANT,OneorZero());
                data.put(TEMP,randomTemp());
                data.put(LIGHT,OneorZero());

                db.collection("Rooms").document("Room" + i).set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                               // Toast.makeText(SenzHome.this, "Success",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Toast.makeText(SenzHome.this, "Failure",Toast.LENGTH_SHORT).show();
                            }
                        });
            }


    }

    public String randomTime(){
        final Random rand = new Random();
        final int millisDay = 24*60*60*1000;
        Time time = new Time((long)rand.nextInt(millisDay));
        return time.toString();
    }

    public int OneorZero(){
        int max = 1;
        int min = 0;
        Random rand = new Random();
        return rand.nextInt((max-min)+max)+min;
    }

    public int randomTemp(){
        int max = 30;
        int min = 10;
        Random rand = new Random();
        return rand.nextInt((max-min)+max)+min;
    }

}


