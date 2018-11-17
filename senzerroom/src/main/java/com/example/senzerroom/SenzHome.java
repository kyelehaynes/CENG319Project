
/*Team Diversity */

package com.example.senzerroom;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.widget.Toolbar;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SenzHome extends AppCompatActivity implements OnNavigationItemSelectedListener
{
    DBHelper mainDB;
    int i;
    int numRooms = 10;
    ImageButton[] rooms = new ImageButton[numRooms];
    ImageButton temp; //temp ImageButton used in for loop

    //Array of TextViews to be displayed on screen
    TextView[] roomText = new TextView[numRooms];
    TextView tempText; //temp ImageButton used in for loop

    ArrayList<ImageButton> imgButton = new ArrayList<>(Arrays.asList(rooms));
    ArrayList<TextView> textViewArrayList = new ArrayList<>(Arrays.asList(roomText));

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senz_home);

        mainDB = new DBHelper(this);

        mainDB.initDB(numRooms);

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
        myButton = new Button(SenzHome.this);
        myButton.setId(R.id.textRoom);
        myButton.setText("Generate 10 Rooms");
        myButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        mainLayout.addView(myButton);
        myButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                //For loop to display the number of rooms equal to numRooms
                for (i = 0; i < numRooms; i++)
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
                            for (i = 0; i < numRooms; i++)
                            {
                                if (v.getId() == i)
                                {
                                    Intent intent = new Intent(SenzHome.this, SenzRoomData.class);
                                    Random rnd = new Random();
                                    int n = 1 + rnd.nextInt(1000);
                                    intent.putExtra("senzRoom", "Senz Room " + (i + 1)).putExtra("numRooms", numRooms);

                                    startActivity(intent);
                                   /** if(i >= 1)
                                    {
                                        Bundle bundle = new Bundle();
                                        bundle.putString("tempVal", "Temperature: " + n + "Degree Celsisus");
                                        FirstFragment fragobj = new FirstFragment();
                                    }*/
                                }
                            }
                        }
                    });


                }
                myButton.setEnabled(false);
            }

        });


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
}