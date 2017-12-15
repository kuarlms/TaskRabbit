package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.StatusResp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by kural on 10/10/17.
 */

public class notificationOptions extends Activity {
        String email,
                str_cb_email,str_cb_sms;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;



    @BindView(R.id.checkbox_email)
    CheckBox cb_email;

    @BindView(R.id.checkbox_sms)
    CheckBox cb_sms;


    @BindView(R.id.tb_normal_title)
    TextView tv_title;



    ApiInterface mAPIService;

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notification_options);
        ButterKnife.bind(this);

         mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        tv_title.setText("Notification");

        email = sp.getString(sp_task.Sp_email,"");


    }

    @OnClick(R.id.button_change_noti)
    public void setBtn_confirm(){



        if(cb_email.isChecked() ){

            str_cb_email = "1";

        }else {

            str_cb_email = "0";

        }

        if(cb_sms.isChecked() ){

            str_cb_sms = "1";

        }else {

            str_cb_sms = "0";

        }


        mAPIService.rf_Notification(ApiInterface.header_value, email,"save",str_cb_email,str_cb_sms)
                .enqueue(new Callback<StatusResp>() {
                    @Override
                    public void onResponse(Call<StatusResp> call, Response<StatusResp> response) {
                        if(response.body().getStatus().equals(1)){
                            Toast.makeText(notificationOptions.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                            finish();
                        }else {
                            Toast.makeText(notificationOptions.this, ""+response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StatusResp> call, Throwable t) {

                        Toast.makeText(notificationOptions.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();





    }
}
