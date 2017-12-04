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
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import java.util.List;

import killbit.taskrabbit.R;
import killbit.taskrabbit.objects.tasker_list_data;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by kural mughil selvam on 15-10-2017.
 */

public class inbox_adapter extends RecyclerView.Adapter<inbox_adapter.MyViewHolder>{

    private List<tasker_list_data> ListDatas;
    Context context;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    OnRecyclerListener recyclerListener;
    tasker_list_data listData;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_adp_rew_name,tv_adp_rew_description
                ,tv_adp_rew_date;

        ImageView iv_review_pic1;
        Button btn_tasker_adp_rate;
        LinearLayout ll_rev1;
        RatingBar rbr1;



        public MyViewHolder(View view, Context context) {
            super(view);


            iv_review_pic1=view.findViewById(R.id.iv_adp_rew_pic);

            ll_rev1=view.findViewById(R.id.ll_rev1);

            tv_adp_rew_name=view.findViewById(R.id.tv_adp_rew_name);

            rbr1=view.findViewById(R.id.rattingbar);

            tv_adp_rew_description=view.findViewById(R.id.tv_adp_rew_description);

            tv_adp_rew_date=view.findViewById(R.id.tv_adp_rew_date);

        }
    }


    public inbox_adapter(List<tasker_list_data> ListData, Context context, OnRecyclerListener recyclerListener) {

        this.ListDatas = ListData;
        this.context = context;
        this.recyclerListener= recyclerListener;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adp_reviews, parent, false);

        return new MyViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder,  int position) {
         listData = ListDatas.get(position);

        holder.btn_tasker_adp_rate.setText(listData.getPrice()+" "+listData.getCurrencySymbol());

        holder.btn_tasker_adp_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerListener.onTaskerSelected(position,listData.getTaskerId(),listData.getProPic(),
                        listData.getCurrencySymbol()+" "+listData.getPrice(),listData.getFirstName());
            }
        });


        if(listData.getReviewArray().size()== 2){

        mtd_reviews(holder);}
        else {
            holder.ll_rev1.setVisibility(View.GONE);

        }






    }

    private void mtd_reviews(MyViewHolder holder) {

        String pic1 = null,pic2 = null;

        if(listData.getReviewArray().get(0).getProPic()!=null){
            pic1 =listData.getReviewArray().get(0).getProPic();
        }else {
            holder.ll_rev1.setVisibility(View.GONE);
        }


        if(listData.getReviewArray().get(1).getProPic()!=null){
            pic2 =listData.getReviewArray().get(1).getProPic();
        }else {

        }

        Glide.with(context).load(pic1).apply(bitmapTransform(new CircleCrop())).into(holder.iv_review_pic1);


        int rat1 = 0,rat2 =0 ;
        try {
            rat1  = Integer.parseInt(listData.getReviewArray().get(0).getReviewStar());

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        holder.rbr1.setNumStars(rat1);


        holder.tv_adp_rew_name.setText(listData.getReviewArray().get(0).getFirstName());


        holder.tv_adp_rew_description.setText(listData.getReviewArray().get(0).getReviewMessage());


        holder.tv_adp_rew_date.setText(listData.getReviewArray().get(0).getDate());


    }

    public interface OnRecyclerListener {
        void onTaskerSelected(int position, String tasker_id, String Profile_pic, String RatePerHr, String TaskerName);

    }

    @Override
    public int getItemCount() {
        return ListDatas.size();
    }



}
