package killbit.taskrabbit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import killbit.taskrabbit.R;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.signup.signupStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kural mughil selvam on 07-10-2017.
 */

public class frag_1 extends Fragment {
    private String title;
    private int page;
    ApiInterface mAPIService;

    // newInstance constructor for creating fragment with arguments
    public static frag_1 newInstance(int page, String title) {
        frag_1 fragmentFirst = new frag_1();
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
        mAPIService = ApiUtils.getAPIService();

        try {
            mtd_calReg();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }

    private void mtd_calReg() {



       mAPIService.rf_signUp(ApiInterface.header_value,"first_name","last_name","email@33.cv","password","147258369","620019").enqueue(new Callback<signupStatus>() {
           @Override
           public void onResponse(Call<signupStatus> call, Response<signupStatus> response) {
             //  Toast.makeText(getActivity(), ""+response.body().getStatus(), Toast.LENGTH_SHORT).show();
               try {
               if(response.body().getStatus() == 1){
                   Toast.makeText(getActivity(), "Signup successfull", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(getActivity(), "ALready Exists", Toast.LENGTH_SHORT).show();
               }


               } catch (Exception e) {
                   e.printStackTrace();
               }


           }

           @Override
           public void onFailure(Call<signupStatus> call, Throwable t) {
               Log.i("Signup e", "post submitted to API." + t.toString());
           }
       });





    }

}
