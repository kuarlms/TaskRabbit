package taskrabit.x33.com.tasker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import taskrabit.x33.com.tasker.R;
import taskrabit.x33.com.tasker.retrofit.ApiInterface;
import taskrabit.x33.com.tasker.retrofit.ApiUtils;

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
        setContentView(R.layout.activity_main);
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
}
