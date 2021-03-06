package killbit.taskrabbit.actvity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.nex3z.notificationbadge.NotificationBadge;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.FrgtPageAdapter;
import killbit.taskrabbit.objects.data_main_home;
import killbit.taskrabbit.objects.data_sub_home;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.home.Home_Resp;
import killbit.taskrabbit.retrofit.inbox.InboxResp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uk.co.chrisjenx.calligraphy.CalligraphyUtils;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentPagerAdapter adapter_view;
    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    ViewPager vpPager;
    data_main_home main_cat;
    TabLayout tabLayout;
    List<data_main_home> list_main_cat = new ArrayList<>();
    ProgressBar pb;
    DrawerLayout drawer;
    TextView tvNbActviteTask,tvNbAccount,tvNbSignout,tvProfileName,tvToolBarTitle,tvInbox;
    ImageView ivNbProfilePic;
    ImageButton iv_tool_nav_icon;
    NotificationBadge notificationBadgeNb;
    ApiInterface mAPIService;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public static void changeTabsFont(TabLayout tabLayout, String fontName) {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    CalligraphyUtils.applyFontToTextView(tabLayout.getContext(), (TextView) tabViewChild, fontName);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vpPager = (ViewPager) findViewById(R.id.vpPager_home);
        pb = findViewById(R.id.pb_home_loading);
  /*      PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_header_home);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(Color.WHITE);
        pagerTabStrip.setTextColor(Color.WHITE);*/
        tabLayout = (TabLayout) findViewById(R.id.tab_home);
        changeTabsFont(tabLayout,"fonts/Montserrat-SemiBold.ttf");
        tabLayout.setupWithViewPager(vpPager);

        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();

        mAPIService = ApiUtils.getAPIService();
        api_home();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        tvNbActviteTask = drawer.findViewById(R.id.textView_nb_active_task);
        tvNbAccount =  drawer.findViewById(R.id.textView_nb_my_acc);
        tvNbSignout  = drawer.findViewById(R.id.textView_nb_sign_out);
        tvProfileName = drawer.findViewById(R.id.textView_nb_profile_name);
        tvToolBarTitle = drawer.findViewById(R.id.tb_normal_title);
        iv_tool_nav_icon = drawer.findViewById(R.id.tb_normal_back);
        ivNbProfilePic  = drawer.findViewById(R.id.imageView_nb_pro_pic);
        notificationBadgeNb = drawer.findViewById(R.id.notification_nb_inbox);
        tvInbox = drawer.findViewById(R.id.tv_nb_inbox);
        ImageButton ivToolBar = drawer.findViewById(R.id.tb_normal_back);

        Glide.with(this).load(R.drawable.button_background).into(ivToolBar);
        //    ivToolBar.setBackground(getResources().getDrawable(R.drawable.button_signup));

        tvToolBarTitle.setText("How can we help ?");
        //iv_tool_nav_icon.setImageDrawable(R.drawable.ic_menu_camera);
        tvProfileName.setText(sp.getString(sp_task.Sp_name,"Guest"));
        Glide.with(this).load(sp.getString(sp_task.Sp_profile_pic, "")).apply(bitmapTransform(new CircleCrop())).into(ivNbProfilePic);


        tvNbActviteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in_at = new Intent(MainActivity.this,active_tasks.class);
                startActivity(in_at);


              //  Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });


        tvNbAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                Intent in_myacc = new Intent(MainActivity.this,myAccount.class);
                startActivity(in_myacc);

            }
        });

        tvNbSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               sp.edit().clear().commit();
               Intent inLogin = new Intent(MainActivity.this,Login.class);
               startActivity(inLogin);
               finish();


            }
        });

        ivNbProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inACc = new Intent(MainActivity.this,Account.class);
                startActivity(inACc);
            }
        });


        tvInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mtd_inbox();
            }
        });
      drawer.addDrawerListener(new DrawerLayout.DrawerListener() {

          @Override
          public void onDrawerSlide(View drawerView, float slideOffset) {
              //Called when a drawer's position changes.
          }

          @Override
          public void onDrawerOpened(View drawerView) {
              mtd_inboxCount();
          }

          @Override
          public void onDrawerClosed(View drawerView) {
              // Called when a drawer has settled in a completely closed state.
          }

          @Override
          public void onDrawerStateChanged(int newState) {
              // Called when the drawer motion state changes. The new state will be one of STATE_IDLE, STATE_DRAGGING or STATE_SETTLING.
          }
      });


    }
    void changeFontInViewGroup(ViewGroup viewGroup, String fontPath) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (TextView.class.isAssignableFrom(child.getClass())) {
                CalligraphyUtils.applyFontToTextView(child.getContext(), (TextView) child, fontPath);
            } else if (ViewGroup.class.isAssignableFrom(child.getClass())) {
                changeFontInViewGroup((ViewGroup) viewGroup.getChildAt(i), fontPath);
            }
        }
    }
    private void mtd_inboxCount() {

        mAPIService.rf_unreadmessage_count(ApiInterface.header_value, sp.getString(sp_task.Sp_email,"")).enqueue(new Callback<InboxResp>() {


            @Override
            public void onResponse(Call<InboxResp> call, Response<InboxResp> response) {
                if(response.body().getStatus().equals(1)){
                    notificationBadgeNb.setText(response.body().getUnreadmessageCount());
                }else {
                    notificationBadgeNb.setText("0");
                }

            }

            @Override
            public void onFailure(Call<InboxResp> call, Throwable t) {

            }  });
        }

    private void mtd_inbox() {

        Intent inInbox = new Intent(MainActivity.this,inBox.class);
        startActivity(inInbox);


    }


    private void setupTabIcons() {


        for (int i = 0; i < list_main_cat.size() ; i++) {

     /*       ImageView tabOne = (ImageView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            Glide.with(getApplicationContext()).load(list_main_cat.get(i).getCat_icon()).into(tabOne);
          *//*  try {
                Drawable db = drawableFromUrl(list_main_cat.get(i).getCat_icon());
                tabOne.setCompoundDrawablesWithIntrinsicBounds(0, db, 0, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }*//*
            tabLayout.getTabAt(0).setCustomView(tabOne);*/
            TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tabOne.setText(list_main_cat.get(i).getCat_title());
         //   tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_tab_favourite, 0, 0);
            tabLayout.getTabAt(i).setCustomView(tabOne);
            tabOne.clearComposingText();


        }



    }
    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }
    private void api_home() {



        mAPIService.rf_home_page(ApiInterface.header_value, sp.getString(sp_task.Sp_email,"")).enqueue(new Callback<Home_Resp>() {
            @Override
            public void onResponse(Call<Home_Resp> call, Response<Home_Resp> response) {




                for (int i = 0; i < response.body().getMainCatList().size(); i++) {
                    List<data_sub_home> sub_list = new ArrayList<data_sub_home>();


                    for (int j = 0; j < response.body().getMainCatList().get(i).getSubcatList().size() ; j++) {
                        data_sub_home sub_cat = null;
                        sub_cat= new data_sub_home(response.body().getMainCatList().get(i).getSubcatList().get(j).getSubcatId(),
                                response.body().getMainCatList().get(i).getSubcatList().get(j).getSubcatName(),
                                response.body().getMainCatList().get(i).getSubcatList().get(j).getSubcatImage(),
                                response.body().getMainCatList().get(i).getSubcatList().get(j).getAvgPrice());
                        sub_list.add(sub_cat);
                     }



                 main_cat = new data_main_home(response.body().getMainCatList().get(i).getCatTitle(),
                            response.body().getMainCatList().get(i).getCatName(),
                            response.body().getMainCatList().get(i).getCatIcon(),
                            response.body().getMainCatList().get(i).getCatId(),
                            sub_list);

                list_main_cat.add(main_cat);


                }


                adapter_view = new FrgtPageAdapter(getSupportFragmentManager(),getApplicationContext(),list_main_cat);
                vpPager.setOffscreenPageLimit(list_main_cat.size());
                vpPager.setAdapter(adapter_view);
                pb.setVisibility(View.GONE);
              //  setupTabIcons();

            }

            @Override
            public void onFailure(Call<Home_Resp> call, Throwable t) {

            }

    });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
