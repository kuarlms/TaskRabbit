package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;

/**
 * Created by kural on 10/10/17.
 */

public class Login extends Activity {

    Intent in_signup;


    @BindView(R.id.button8)
    Button btn_signUp;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.button8)
    public void submit() {
        in_signup = new Intent(Login.this,Signup.class);
        startActivity(in_signup);

    }

}
