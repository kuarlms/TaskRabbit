package killbit.taskrabbit.adapters;

/**
 * Created by kural mughil selvam on 15-10-2017.
 */

public class home_screen_adapter{}/* extends RecyclerView.Adapter<home_screen_adapter.MyViewHolder>{

  *//*  private List<AlertStreamModelList> ListData;
    Context context;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    OnRecyclerListener recyclerListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, user_name, time,date;
        ImageView iv_thumb,iv_playIcon,iv_mic_top_left;



        public MyViewHolder(View view, Context context) {
            super(view);
            iv_thumb = (ImageView) view.findViewById(R.id.ns_adp_iv_thumb);
            iv_playIcon = (ImageView) view.findViewById(R.id.ns_adp_iv_playicon);

        }
    }


    public home_screen_adapter(List<AlertStreamModelList> ListData, Context context, OnRecyclerListener recyclerListener) {
        this.ListData = ListData;
        this.context = context;
        this.recyclerListener =recyclerListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_notification_stream, parent, false);

        return new MyViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder,  int position) {
         AlertStreamModelList notificationStreamModelList = ListData.get(position);
        holder.title.setText(StringUtils.capitalize(notificationStreamModelList.getTitle()).trim());


    }

    public interface OnRecyclerListener {
        void onItemClicked(int position,String data);

    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }*//*

}
*/
