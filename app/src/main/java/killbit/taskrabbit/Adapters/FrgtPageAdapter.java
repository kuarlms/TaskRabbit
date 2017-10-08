package killbit.taskrabbit.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import killbit.taskrabbit.fragments.frag_home;

/**
 * Created by kural mughil selvam on 07-10-2017.
 */


public class FrgtPageAdapter extends FragmentPagerAdapter {

    int NUM_ITEMS = 3;
    public FrgtPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return frag_home.newInstance(0, "Page # 1");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return frag_home.newInstance(1, "Page # 2");
            case 2: // Fragment # 1 - This will show SecondFragment
                return frag_home.newInstance(2, "Page # 3");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }



}
