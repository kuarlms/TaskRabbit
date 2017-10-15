package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.signIn.LoginResp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kural on 10/10/17.
 */


public class SignIn_email extends Activity implements Validator.ValidationListener{

    Validator validator;
    Button btn_login;
    SharedPreferences sp;
    SharedPreferences.Editor  editor ;

    @NotEmpty
    @Email
    @BindView(R.id.editText) EditText et_email;

    @NotEmpty
    @Password
    @BindView(R.id.editText2)EditText et_pas;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_with_email);
        ButterKnife.bind(this);
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);



        btn_login = findViewById(R.id.button_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignIn_email.this, "cc", Toast.LENGTH_LONG).show();
                validator.validate();
            }
        });

        validator = new Validator(this);
        validator.setValidationListener(this);


    }



    @OnClick(R.id.button_login)
    public void submit(){
        //Toast.makeText(SignIn_email.this, "cc", Toast.LENGTH_LONG).show();
        validator.validate();
    }
    @OnClick(R.id.textViews_ignup)
    public void sign_up_int(){
        Intent in = new Intent(SignIn_email.this, Signup_email.class);
        startActivity(in);
        finish();
    }


    @Override
    public void onValidationSucceeded() {
        ApiInterface mAPIService;
        mAPIService = ApiUtils.getAPIService();

          mAPIService.rf_signIn(ApiInterface.header_value, String.valueOf(et_email.getText()),et_pas.getText().toString()).enqueue(new Callback<LoginResp>() {

              @Override
              public void onResponse(Call<LoginResp> call, Response<LoginResp> response) {

                  if(response.body().getStatus().equals("1")){


                      editor.putBoolean(sp_task.Sp_IsLoggedIn,true);
                      editor.commit();
                      Intent i ;
                      i = new Intent(SignIn_email.this,MainActivity.class);
                      startActivity(i);
                      finish();


                  }else if(response.body().getStatus().equals("0")) {
                      Toast.makeText(SignIn_email.this, "Login Failed ,Invalid login detail.", Toast.LENGTH_SHORT).show();
                  }



              }

              @Override
              public void onFailure(Call<LoginResp> call, Throwable t) {
                  Toast.makeText(SignIn_email.this, "Login Failed....Check your connectivity.", Toast.LENGTH_SHORT).show();

              }
          });


    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
         //   Toast.makeText(this, "scsc", Toast.LENGTH_LONG).show();
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }



    }
}
