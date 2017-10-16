package killbit.taskrabbit.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.home_screen_adapter;
import killbit.taskrabbit.objects.data_sub_home;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.signup.signupStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kural mughil selvam on 07-10-2017.
 */

public class frag_home extends Fragment {
    private String title;
    private int page;
    ApiInterface mAPIService;
    RecyclerView recyclerView;
    home_screen_adapter recy_screen_adapter;
    List< data_sub_home> list_data = new ArrayList<>();

    // newInstance constructor for creating fragment with arguments
    public static frag_home newInstance(int page, List<data_sub_home> list_data) {
        frag_home fragmentFirst = new frag_home();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", list_data.get(0).getSubcat_name());
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", page);
        title = getArguments().getString("someTitle",title);



    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_frag_home);

        recy_screen_adapter = new home_screen_adapter(list_data,getActivity().getApplicationContext());

        for (int i = 0; i < list_data.size(); i++) {
            Toast.makeText(getActivity(), ""+list_data.get(i).getSubcat_name(), Toast.LENGTH_SHORT).show();

        }
        TextView tvLabel = (TextView) view.findViewById(R.id.tv_home_frag);
        tvLabel.setText(page + " -- " + list_data.size());
        mAPIService = ApiUtils.getAPIService();

        try {
          //  mtd_calReg();
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
