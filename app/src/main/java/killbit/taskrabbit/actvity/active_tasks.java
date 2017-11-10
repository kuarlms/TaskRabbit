package killbit.taskrabbit.actvity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.active_tasks_adp;
import killbit.taskrabbit.objects.active_tasks_data;

/**
 * Created by kural on 11/10/17.
 */

public class active_tasks extends FragmentActivity{

    active_tasks_data tasks_data;
    List<active_tasks_data> tasks_datas = new ArrayList<>();
    active_tasks_adp adapter_act_tsk;
      //  RecyclerView rv_at_list;
    @BindView(R.id.recycleView)
    RecyclerView rv_at_list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_active_tasks);
       ButterKnife.bind(this);
        //rv_at_list = findViewById(R.id.recycleView);


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
        rv_at_list.setAdapter(adapter_act_tsk);



    }
}
