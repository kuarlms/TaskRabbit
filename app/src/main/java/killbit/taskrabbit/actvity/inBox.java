package killbit.taskrabbit.actvity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.active_tasks_adp;
import killbit.taskrabbit.objects.active_tasks_data;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.utils.sp_task;

/**
 * Created by kural on 11/10/17.
 */

public class inBox extends FragmentActivity{

    active_tasks_data tasks_data;
    List<active_tasks_data> tasks_datas = new ArrayList<>();
    active_tasks_adp adapter_act_tsk;

    @BindView(R.id.recycleView_tasker_details)
    RecyclerView rv_at_list;


    @BindView(R.id.tb_normal_title)
    TextView tv_title;

    ApiInterface mAPIService;
    String email;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasker_recycler);
       ButterKnife.bind(this);
        mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();

        tv_title.setText("Inbox");
        email = sp.getString(sp_task.Sp_email,"");

/*
        for (int i = 0; i < 12; i++) {
            tasks_data = new active_tasks_data(""+i,
                    ""+i,
                    ""+i,
                    ""+i,
                    ""+i,
                    ""+i,
                    ""+i,
                    ""+i
                    );


            tasks_datas.add(tasks_data);
        }

        adapter_act_tsk = new active_tasks_adp(tasks_datas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_at_list.setLayoutManager(mLayoutManager);
        rv_at_list.setItemAnimator(new DefaultItemAnimator());
        rv_at_list.setAdapter(adapter_act_tsk);*/



    }

    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }
}
