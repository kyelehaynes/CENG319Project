package com.example.senzerroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SenzHome extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.senzmenu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senz_home);
        int numRooms = 10;

        //Main Homescreen layout
        LinearLayout mainLayout = findViewById(R.id.MainLayout);

        //Array of ImageButtons to be displayed on screen
        ImageButton[] rooms = new ImageButton[numRooms];
        ImageButton temp; //temp ImageButton used in for loop

        //Array of TextViews to be displayed on screen
        TextView[] roomText = new TextView[numRooms];
        TextView tempText; //temp ImageButton used in for loop

        //For loop to display the number of rooms equal to numRooms
        for (int i = 0; i < numRooms; i++)
        {
            temp = new ImageButton(this);
            tempText = new TextView(this);

            //Setting parameters for the ImageButton
            temp.setImageResource(R.drawable.room);
            temp.setId(i);
            temp.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            //Setting parameters for the TextView
            tempText.setText("Room " + (i+1));
            tempText.setTextColor(getResources().getColor(R.color.white));
            tempText.setTextSize(18);
            tempText.setId(i);
            tempText.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            ));

            //displaying the ImageButton and TextView
            mainLayout.addView(tempText);
            mainLayout.addView(temp);

            //adding the current temp ImageButton and TextView to their respective arrays
            rooms[i] = temp;
            roomText[i] = tempText;
        }
    }
}
