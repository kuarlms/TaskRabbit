package killbit.taskrabbit.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import killbit.taskrabbit.R;
import killbit.taskrabbit.objects.tasker_list_data;

/**
 * Created by kural mughil selvam on 15-10-2017.
 */

public class tasker_details_adapter extends RecyclerView.Adapter<tasker_details_adapter.MyViewHolder>{

    private List<tasker_list_data> ListDatas;
    Context context;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    OnRecyclerListener recyclerListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_month, tv_day,tv_date;
        LinearLayout item_parent;



        public MyViewHolder(View view, Context context) {
            super(view);

            tv_month =view.findViewById(R.id.txt_adp_month);
            tv_day =view.findViewById(R.id.txt_adp_day);
            tv_date = view.findViewById(R.id.txt_adp_date);
            item_parent = view.findViewById(R.id.parent_item_adp_home);


        }
    }


    public tasker_details_adapter(List<tasker_list_data> ListData, Context context, OnRecyclerListener recyclerListener) {

        this.ListDatas = ListData;
        this.context = context;
        this.recyclerListener= recyclerListener;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_date, parent, false);

        return new MyViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder,  int position) {
        tasker_list_data listData = ListDatas.get(position);
      /*  holder.tv_month.setText(listData.getMonth());
        holder.tv_day.setText(listData.getDay());
        holder.tv_date.setText(listData.getDate());*/


        holder.item_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  String Cda = listData.getMonth()+" , "+listData.getDay()+" , "+listData.getDate()+" ";
                //recyclerListener.onItemClicked(position, Cda);
            }
        });


    }

    public interface OnRecyclerListener {
        void onItemClicked(int position, String data);

    }

    @Override
    public int getItemCount() {
        return ListDatas.size();
    }



}
