package killbit.taskrabbit.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import killbit.taskrabbit.R;
import killbit.taskrabbit.fragments.frag_home;

/**
 * Created by kural mughil selvam on 07-10-2017.
 */


public class FrgtPageAdapter extends FragmentPagerAdapter {

    int NUM_ITEMS = 3;
    Context mContext;
    public FrgtPageAdapter(FragmentManager fragmentManager, Context applicationContext) {
        super(fragmentManager);
        this.mContext = applicationContext;
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

        SpannableStringBuilder sb = new SpannableStringBuilder(" Page " + (position + 1)); // space added before text for convenience

        Drawable drawable = mContext.getResources().getDrawable( R.drawable.ic_menu_slideshow );
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;

     //   return "Page " + position;
    }



}
