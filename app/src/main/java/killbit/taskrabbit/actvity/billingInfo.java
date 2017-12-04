package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.bookingConfirmation.bookingConfirmation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by kural on 10/10/17.
 */

public class billingInfo extends Activity {
        String email,cat_id,subcat_id,task_date,
                task_time,city,vehicle_id,task_description,
                credit_card_type,number,cvc,exp_month,exp_year,
                tasker_id,ProfilePic,RatePer,TaskerName;

    @BindView(R.id.iv_confirm_tasker_pic)
    ImageView iv_tasker_pic;

    @BindView(R.id.tv_confim_tasker_name)
    TextView tv_taskername;

    @BindView(R.id.tv_confim_tasker_cost)
    TextView tv_taskerCost;

    @BindView(R.id.tv_confim_tasker_payment_message)
    TextView tv_taskerPayment;

    @BindView(R.id.tv_confim_tasker_cancellation_fee)
    TextView tv_tasker_cancellation;

    @BindView(R.id.tv_confim_tasker_trust)
    TextView tv_tasker_trust;


    @BindView(R.id.et_confim_tasker_card_number)
    EditText et_card_number;

    @BindView(R.id.et_confim_tasker_card_cvv)
    EditText et_card_cvv;

    @BindView(R.id.et_confim_tasker_card_exp_date)
    EditText et_card_expMonth;

    @BindView(R.id.et_confim_tasker_card_exp_dateYear)
    EditText et_card_expYear;

    @BindView(R.id.btn_confim_tasker_payment)
    Button btn_confirm;

    ApiInterface mAPIService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.actvity_billing_info);
        ButterKnife.bind(this);

         mAPIService = ApiUtils.getAPIService();




    }

    @OnClick(R.id.btn_confim_tasker_payment)
    public void setBtn_confirm(){

        if(et_card_number.getText() != null  || et_card_number.getText().length() < 8 ){

            number = et_card_number.getText().toString();

            credit_card_type = "1";

        }else {
            et_card_number.setError("Required");
            return;
        }

        if(et_card_expMonth.getText() != null || et_card_expMonth.getText().length() < 1){

            exp_month =et_card_expMonth.getText().toString();

        }else {
            et_card_expMonth.setError("Required");
            return;
        }


        if(et_card_expYear.getText() != null || et_card_expYear.getText().length() < 1){

            exp_year = et_card_expYear.getText().toString();;

        }else {
            et_card_expYear.setError("Required");
            return;
        }


        if(et_card_cvv.getText() != null || et_card_cvv.getText().length() < 2){

            cvc = et_card_cvv.getText().toString();
        }else {
            et_card_cvv.setError("Required");
            return;
        }


        mAPIService.rf_booking(ApiInterface.header_value, email,cat_id,subcat_id,task_date,task_time,city,vehicle_id,
                tasker_id,task_description,credit_card_type,number,cvc,exp_month,exp_year)
                .enqueue(new Callback<bookingConfirmation>() {
                    @Override
                    public void onResponse(Call<bookingConfirmation> call, Response<bookingConfirmation> response) {

                        if(response.body().getStatus().equals(1)){
                            Toast.makeText(billingInfo.this, "Booking Successfull, Check Active screen for updates", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(billingInfo.this, "Booking Failed Retry with proper details.", Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onFailure(Call<bookingConfirmation> call, Throwable t) {
                        Toast.makeText(billingInfo.this, "Booking Failed Retry later.", Toast.LENGTH_SHORT).show();

                    }
                });


    }



    @Override
    protected void onStart() {
        super.onStart();

        Intent inGet = getIntent();
        Bundle b = inGet.getExtras();
        email = b.getString("email");
        cat_id =b.getString("cat_id");
        subcat_id =b.getString("subcat_id");
        task_date =b.getString("task_date");
        task_time = b.getString("task_time");
        city =b.getString("city");
        vehicle_id = b.getString("vehicle_id");
        task_description = b.getString("task_description");
        tasker_id =b.getString("tasker_id");
        ProfilePic = b.getString("ProfilePic");
        RatePer = b.getString("RatePer");
        TaskerName = b.getString("TaskerName");

        Glide.with(this).load(ProfilePic).apply(bitmapTransform(new CircleCrop())).into(iv_tasker_pic);




    }
}
