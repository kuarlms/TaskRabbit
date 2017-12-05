package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.transactionList.transactionsResp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kural on 10/10/17.
 */

public class transactionList extends Activity {
     String email;
     int page = 1;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;


    @BindView(R.id.tb_normal_title)
    TextView tv_title;

    @BindView(R.id.pb_active_tasks_loading)
    ProgressBar pb;
    @BindView(R.id.recycleView)
    RecyclerView rv_at_list;


    ApiInterface mAPIService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actvity_active_tasks);
        ButterKnife.bind(this);

        mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        tv_title.setText("Transactions");

        email = sp.getString(sp_task.Sp_email,"");


    }



    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAPIService.rf_transaction_list(ApiInterface.header_value, email,""+page)
                .enqueue(new Callback<transactionsResp>() {
                    @Override
                    public void onResponse(Call<transactionsResp> call, Response<transactionsResp> response) {
                        if(response.body().getStatus().equals(1)){
                           // Toast.makeText(transactionList.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                          //  finish();
                        }else {
                            //Toast.makeText(transactionList.this, ""+response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<transactionsResp> call, Throwable t) {

                        Toast.makeText(transactionList.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });
        /*(new Callback<StatusResp>() {
                    @Override
                    public void onResponse(Call<StatusResp> call, Response<StatusResp> response) {

                    }

                    @Override
                    public void onFailure(Call<StatusResp> call, Throwable t) {


                    }
                });


    */




    }
}
