
/*Team Diversity */

package com.example.senzerroom;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.senzerroom.SenzHome.TIME;
import static com.example.senzerroom.SenzHome.VACANT;
import static com.example.senzerroom.SenzHome.TEMP;
import static com.example.senzerroom.SenzHome.LIGHT;

public class PagerAdapter extends FragmentPagerAdapter {

    private final Bundle bundle;
    int numberOfTabs;
    String vacant;
    String myTime;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, Bundle bundle)
    {
        super(fm);
        this.numberOfTabs = NumOfTabs;
        this.bundle = bundle;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FirstFragment tab1 = new FirstFragment();
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                SecondFragment tab2 = new SecondFragment();
                tab2.setArguments(bundle);
                return tab2;
            case 2:
                ThirdFragment tab3 = new ThirdFragment();
                tab3.setArguments(bundle);
                return tab3;
            case 3:
                AdminFragment tab4 = new AdminFragment();
                tab4.setArguments(bundle);
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

   /*public void getData(Fragment fragment)
    {
        Bundle bundle = new Bundle();
        if(bundle != null)
        {
            //String vacant = bundle.getString("numRooms");

            fragment.setArguments(bundle);
        }
    }*/
}