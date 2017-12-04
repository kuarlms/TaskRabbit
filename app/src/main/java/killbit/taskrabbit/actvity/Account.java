package killbit.taskrabbit.actvity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.vehicle_list_adp;
import killbit.taskrabbit.objects.vehicle_list_data;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.utils.sp_task;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by kural on 11/10/17.
 */

public class Account extends FragmentActivity {

    vehicle_list_data tasks_data;
    List<vehicle_list_data> tasks_datas = new ArrayList<>();
    vehicle_list_adp adapter_my_account;
      //  RecyclerView rv_at_list;

    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    ApiInterface mAPIService;
    @BindView(R.id.pb_account_loading)
    ProgressBar pb;
    @BindView(R.id.tb_normal_title)
    TextView tv_title;
    @BindView(R.id.iv_account_pic)
    ImageView iv_pic;

    @BindView(R.id.et_account_name)
    EditText et_name;

    @BindView(R.id.et_account_email)
    EditText et_email;
    @BindView(R.id.et_account_mobile)
    EditText et_mobile;
    @BindView(R.id.et_account_zipcode)
    EditText et_zip;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.actvity_account);
       ButterKnife.bind(this);




       tv_title.setText("Account");
       pb.setVisibility(View.GONE);
       sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
       editor =sp.edit();
       mAPIService = ApiUtils.getAPIService();

        Glide.with(this).load(sp.getString(sp_task.Sp_profile_pic, "")).apply(bitmapTransform(new CircleCrop())).into(iv_pic);
        et_name.setText(sp.getString(sp_task.Sp_name,""));
        et_mobile.setText(sp.getString(sp_task.Sp_mobile,""));
        et_email.setText(sp.getString(sp_task.Sp_email,""));
        et_zip.setText(sp.getString(sp_task.Sp_zip,""));

    iv_pic.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    });


    }

    @OnClick({R.id.tb_normal_back})
    public void tb_back(){

        finish();

    }
    @OnClick({R.id.btn_account})
    public void btn_save(){
        if(! (et_name.getText().toString().length() >= 3)){
            et_name.setText("Required");
            return;
        }

        if(! (et_email.getText().toString().length() >= 3)){
            et_name.setText("Required");
            return;
        }

        if(! (et_mobile.getText().toString().length() >= 3)){
            et_name.setText("Required");
            return;
        }

        if(! (et_zip.getText().toString().length() >= 3)){
            et_name.setText("Required");
            return;
        }
      //  mAPIService.rf_updateProfile(ApiInterface.header_value, email,cat_id,subcat_id,task_date,task_time,city,vehicle_id,

    }





}
