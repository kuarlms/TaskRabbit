package killbit.taskrabbit.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import killbit.taskrabbit.R;
import killbit.taskrabbit.objects.active_tasks_data;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by kural on 11/10/17.
 */

public class taskHistory_canceled_adapter extends RecyclerView.Adapter<taskHistory_canceled_adapter.MyViewHolder> {

private List<active_tasks_data> taskList;
Context context;
OnTaskDoneListner taskDoneListner;


public class MyViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.tv_thp_time)
    TextView tv_time;
    @BindView(R.id.textView2)
    TextView tv_description;
    @BindView(R.id.textView43)
    TextView tv_name;
    @BindView(R.id.textView44)
    TextView tv_task_name;
    @BindView(R.id.imageView10)
    ImageView iv_profile_pic;
    @BindView(R.id.et_active_task_hours)
    EditText et_active_hrs;
    @BindView(R.id.button13)
    Button btn_chat;
    @BindView(R.id.button10_taskDone)
    Button btn_taskDone;



//TextView tv;


    public MyViewHolder(View view) {
        super(view);
        // tv_name = view.findViewById(R.id.textView43);

        ButterKnife.bind(this, view);


    }
}


    public taskHistory_canceled_adapter(List<active_tasks_data> taskList, Context context, OnTaskDoneListner taskDoneListner) {
        this.taskList = taskList;
        this.context =context;
        this.taskDoneListner =taskDoneListner;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adap_task_history_canceled, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        active_tasks_data actvie_task = taskList.get(position);
        holder.tv_name.setText(actvie_task.getTasker_name());
        holder.tv_task_name.setText(actvie_task.getTask_name());
        Glide.with(context).load(actvie_task.getProfile_pic()).apply(bitmapTransform(new CircleCrop())).into(holder.iv_profile_pic);
        holder.tv_description.setText(actvie_task.getLocation());
        holder.tv_time.setText(actvie_task.getActive_time());
        holder.btn_chat.setText(actvie_task.getCost());

        holder.btn_taskDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "LongPress to confirm cancellation", Toast.LENGTH_SHORT).show();



            }
        });

        holder.btn_taskDone.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                taskDoneListner.onBtnTaskDone(actvie_task.getBooking_id(),holder.et_active_hrs.getText().toString());
                return false;
            }
        });


        holder.btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDoneListner.onBtnChat(actvie_task.getBooking_id(),position);
            }
        });

    }
    public interface OnTaskDoneListner{
    void onBtnTaskDone(String booking_id, String task_hour);
        void onBtnChat(String booking_id, int position);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}


