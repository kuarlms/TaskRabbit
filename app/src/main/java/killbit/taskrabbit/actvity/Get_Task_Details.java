package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.utils.sp_task;

/**
 * Created by kural on 10/10/17.
 */




public class Get_Task_Details extends Activity implements Validator.ValidationListener {

  View BottomView;

    //View view = LayoutInflater.from(context).inflate(R.layout.thing, null);

 /*   @NotEmpty
    @BindView(R.id.editText_password)
    TextView textView16;

    @NotEmpty
    @BindView(R.id.textView21)
    TextView et_phone;
*/  Dialog mBottomSheetDialog;
    Validator validator;
    ApiInterface mAPIService;
    SharedPreferences sp;
    SharedPreferences.Editor  editor ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_task_deatils);

       BottomView = getLayoutInflater().inflate(R.layout.dialouge_task_details,null);
        mBottomSheetDialog = new Dialog(Get_Task_Details.this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(BottomView);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();



    }
    @OnClick(R.id.button_done)
    public void btn_submit(){

        validator.validate();

    }
    @OnClick(R.id.card_task)
    public void on_click_task_details(){
        mBottomSheetDialog.show();
    }



    @Override
    public void onValidationSucceeded() {
       /* mAPIService.rf_signUp(ApiInterface.header_value,et_fname.getText().toString()
                ,et_lname.getText().toString(),et_email.getText().toString()
                ,et_pass.getText().toString(),et_phone.getText().toString(),
                et_zip.getText().toString()).enqueue(new Callback<signupStatus>() {
            @Override
            public void onResponse(Call<signupStatus> call, Response<signupStatus> response) {

                try {
                    if(response.body().getStatus() == 1){
                        Toast.makeText(getApplicationContext(), "Sign-up successful, signIn to proceed.", Toast.LENGTH_SHORT).show();

                        Intent i ;
                        i = new Intent(Get_Task_Details.this,SignIn_email.class);
                        startActivity(i);
                        finish();



                    }else {
                        Toast.makeText(getApplicationContext(), "Already exists, Try forgot Password or using another email address.", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<signupStatus> call, Throwable t) {
                Toast.makeText(Get_Task_Details.this, "Unable to reach server, Check connectivity. ", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
