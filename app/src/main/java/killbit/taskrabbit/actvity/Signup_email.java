package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import killbit.taskrabbit.R;

/**
 * Created by kural on 10/10/17.
 */

public class Signup_email extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_with_email);
        ButterKnife.bind(this);

    }

    
}
