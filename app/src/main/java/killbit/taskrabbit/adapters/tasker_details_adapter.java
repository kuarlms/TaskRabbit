package killbit.taskrabbit.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
        TextView tv_adp_tasker_name, tv_adp_tasker_review,
                tv_adp_tasker_iv_verifed,tv_adp_tasker_tearms_txt_description
                ,tv_adp_tasker_since,tv_adp_tasker_number_of_tasks,
                tv_adp_tasker_quick_notes,tv_adp_tasker_details1,
                tv_adp_tasker_details2,tv_adp_tasker_details3,
                tv_adp_tasker_pleople_said;
        RecyclerView rv_tsker_reviews;
        ImageView iv_taker_pic;
        Button btn_tasker_adp_rate;
        LinearLayout item_parent;



        public MyViewHolder(View view, Context context) {
            super(view);

            iv_taker_pic = view.findViewById(R.id.iv_tasker_details_adp);
            tv_adp_tasker_name = view.findViewById(R.id.tv_adp_tasker_name);
            tv_adp_tasker_review = view.findViewById(R.id.tv_adp_tasker_review);
            tv_adp_tasker_iv_verifed = view.findViewById(R.id.tv_adp_tasker_iv_verifed);
            tv_adp_tasker_tearms_txt_description= view.findViewById(R.id.tv_adp_tasker_tearms_txt_description);
            tv_adp_tasker_since= view.findViewById(R.id.tv_adp_tasker_since);
            tv_adp_tasker_number_of_tasks= view.findViewById(R.id.tv_adp_tasker_number_of_tasks);
            tv_adp_tasker_quick_notes= view.findViewById(R.id.tv_adp_tasker_quick_notes);
            tv_adp_tasker_details1= view.findViewById(R.id.tv_adp_tasker_details1);
            tv_adp_tasker_details2= view.findViewById(R.id.tv_adp_tasker_details2);
            tv_adp_tasker_details3= view.findViewById(R.id.tv_adp_tasker_details3);
            tv_adp_tasker_pleople_said= view.findViewById(R.id.tv_adp_tasker_pleople_said);
            btn_tasker_adp_rate = view.findViewById(R.id.btn_tasker_adp_rate);


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
                .inflate(R.layout.viwepager_tasker_details, parent, false);

        return new MyViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder,  int position) {
        tasker_list_data listData = ListDatas.get(position);
        Glide.with(context).load(R.drawable.a).into(holder.iv_taker_pic);
        holder.tv_adp_tasker_name.setText(listData.getFirstName());
       /* iv_taker_pic = view.findViewById(R.id.iv_tasker_details_adp);

        tv_adp_tasker_iv_verifed = view.findViewById(R.id.tv_adp_tasker_iv_verifed);
        tv_adp_tasker_tearms_txt_description= view.findViewById(R.id.tv_adp_tasker_tearms_txt_description);
        tv_adp_tasker_since= view.findViewById(R.id.tv_adp_tasker_since);
        tv_adp_tasker_number_of_tasks= view.findViewById(R.id.tv_adp_tasker_number_of_tasks);
        tv_adp_tasker_quick_notes= view.findViewById(R.id.tv_adp_tasker_quick_notes);
        tv_adp_tasker_details1= view.findViewById(R.id.tv_adp_tasker_details1);
        tv_adp_tasker_details2= view.findViewById(R.id.tv_adp_tasker_details2);
        tv_adp_tasker_details3= view.findViewById(R.id.tv_adp_tasker_details3);
        tv_adp_tasker_pleople_said= view.findViewById(R.id.tv_adp_tasker_pleople_said);
        btn_tasker_adp_rate = view.findViewById(R.id.btn_tasker_adp_rate);*/
       holder.tv_adp_tasker_review.setText(listData.getReviewResponseRate());
       holder.tv_adp_tasker_iv_verifed.setText(listData.getIdVerified());
       holder.tv_adp_tasker_tearms_txt_description.setText(listData.getAbout());






    /*    holder.item_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  String Cda = listData.getMonth()+" , "+listData.getDay()+" , "+listData.getDate()+" ";
                recyclerListener.onTaskerSelected(position, listData.getFirstName());
            }
        });*/


    }

    public interface OnRecyclerListener {
        void onTaskerSelected(int position, String data);

    }

    @Override
    public int getItemCount() {
        return ListDatas.size();
    }



}
