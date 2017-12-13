package killbit.taskrabbit.actvity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.chat_adapter;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.Chattingreceive.ChatMessage;
import killbit.taskrabbit.retrofit.Chattingreceive.ChatResp;
import killbit.taskrabbit.retrofit.Chattingreceive.ChatUserInfo;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 11/10/17.
 */

public class chat extends FragmentActivity implements  chat_adapter.OnRecyclerListener{

    ChatMessage data;
    List<ChatMessage> Listdata = new ArrayList<>();
    chat_adapter adapter;
    String bookingId;
    ChatUserInfo chatUserInfo;
    String  chatUserInfoz;

    @BindView(R.id.recycleViewChat)
    RecyclerView rv_at_list;

    @BindView(R.id.swiperereshChat)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.tb_normal_title)
    TextView tv_title;

    ApiInterface mAPIService;
    String email;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();

        tv_title.setText("Chat");
        email = sp.getString(sp_task.Sp_email,"");

        adapter = new chat_adapter(Listdata,this,chat.this,chatUserInfoz);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_at_list.setLayoutManager(mLayoutManager);
        rv_at_list.setItemAnimator(new DefaultItemAnimator());
        rv_at_list.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });


    }



    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }



    @OnClick(R.id.imageViewChatSend)
    public void sendMsg(){

    }


    @OnClick(R.id.imageViewPickChat)
    public void attachImg(){

    }

    @Override
    protected void onStart() {
        super.onStart();

        bookingId = getIntent().getStringExtra("taskId");
        tv_title.setText(getIntent().getStringExtra("TaskerName"));


        mAPIService.rf_chat_inner(ApiInterface.header_value, sp.getString(sp_task.Sp_email,""),bookingId).enqueue(new Callback<ChatResp>() {



            @Override
            public void onResponse(Call<ChatResp> call, Response<ChatResp> response) {
                if(response.body().getStatus().equals(1)){

                    Listdata.clear();
                    chatUserInfo = null;

                    for (int i = 0; i <response.body().getChatMessages().size() ; i++) {
                    data = new ChatMessage(response.body().getChatMessages().get(i).getMessage(),
                            response.body().getChatMessages().get(i).getPosition(),
                            response.body().getChatMessages().get(i).getBookingId(),
                           response.body().getChatMessages().get(i).getCreatedTime());


                    Listdata.add(data);
                    }

                   chatUserInfo = new ChatUserInfo(response.body().getChatUserInfo().getName(),
                           response.body().getChatUserInfo().getProfileImage(),
                           response.body().getChatUserInfo().getCity());

                   chatUserInfoz =  response.body().getChatUserInfo().getProfileImage();
                    Log.d("ppic",chatUserInfoz);
                    adapter.notifyDataSetChanged();

                }else {


                }


            }

            @Override
            public void onFailure(Call<ChatResp> call, Throwable t) {

            }
        });

    }





    @Override
    public void onChatItemSelected(int position, String Booking_id, String TaskerName) {

    }
}
