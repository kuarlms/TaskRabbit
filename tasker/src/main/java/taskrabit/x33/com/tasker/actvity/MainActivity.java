package taskrabit.x33.com.tasker.actvity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import taskrabit.x33.com.tasker.R;
import taskrabit.x33.com.tasker.fragments.cancelled;
import taskrabit.x33.com.tasker.retrofit.ApiInterface;
import taskrabit.x33.com.tasker.retrofit.ApiUtils;
import taskrabit.x33.com.tasker.utils.sp_task;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.pb_taskhistory)
    ProgressBar pb_taskhistory;

    @BindView(R.id.tb_normal_title)
    TextView tv_title;

    @BindView(R.id.viewpager_task_history)
    ViewPager mViewPager;

    @BindView(R.id.tabLayout_task_history)
    TabLayout tabLayout;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    ApiInterface mAPIService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskhistory);
        ButterKnife.bind(this);
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        mAPIService = ApiUtils.getAPIService();
        tv_title.setText("Task History");


        setUpViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(1);


        tabLayout.setupWithViewPager(mViewPager);

        pb_taskhistory.setVisibility(View.GONE);

    }


    private void setUpViewPager(ViewPager mViewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
       /* adapter.addFragment(new pending(), "pending");
        adapter.addFragment(new approved(), "approved");
        adapter.addFragment(new completed(), "completed");*/
        adapter.addFragment(new cancelled(), "cancelled");


        mViewPager.setAdapter(adapter);

    }
    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager){
            super(manager);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
