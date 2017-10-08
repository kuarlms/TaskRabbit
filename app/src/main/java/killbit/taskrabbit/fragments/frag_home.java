package killbit.taskrabbit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.signup.SignUpReq;
import killbit.taskrabbit.retrofit.signup.signupStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static killbit.taskrabbit.retrofit.ApiInterface.retrofit;

/**
 * Created by kural mughil selvam on 07-10-2017.
 */

public class frag_home extends Fragment {
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static frag_home newInstance(int page, String title) {
        frag_home fragmentFirst = new frag_home();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.tv_home_frag);
        tvLabel.setText(page + " -- " + title);
        SignUpReq signUpReq = null;

     //   RestClient.get(this).PostPhoneContacts(sharedpreferences.getString(SharedPrefUtils.SpToken, ""),new ContactsReq(Contact.getInstance().email_list),
        ApiInterface apiService =
                retrofit.create(ApiInterface.class);
        Call<signupStatus> call = apiService.rx_signUp(signUpReq);
        call.enqueue(new Callback<signupStatus>() {
            @Override
            public void onResponse(Call<signupStatus> call, Response<signupStatus> response) {

            }

            @Override
            public void onFailure(Call<signupStatus> call, Throwable t) {

            }
        });


        return view;
    }

}
