package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.sgiosviews.SGPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import killbit.taskrabbit.R;
import killbit.taskrabbit.adapters.date_selector_adapter;
import killbit.taskrabbit.objects.date_obj;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.utils.sp_task;

/**
 * Created by kural on 10/10/17.
 */




public class Get_Task_Details extends Activity implements Validator.ValidationListener,date_selector_adapter.OnRecyclerListener {

  View BottomView;
    TextView tv_when;

    //View view = LayoutInflater.from(context).inflate(R.layout.thing, null);

    @NotEmpty
    @BindView(R.id.textView1x6)
    EditText textView_task_address;

    @NotEmpty
    @BindView(R.id.textView1)
    TextView textView_task_when;

    @NotEmpty
    @BindView(R.id.textView16)
    TextView textView_task_details;

    @NotEmpty
    @BindView(R.id.textView1zx6)
    TextView textView_task_vehicle;



  Dialog dialouge_task,dialouge_when,dialouge_vehicle,dialog_address;
    Validator validator;
    ApiInterface mAPIService;
    SharedPreferences sp;
    SharedPreferences.Editor  editor ;
    EditText et_task_details,et_task_address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_task_deatils);



        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
        mAPIService = ApiUtils.getAPIService();
        sp =  getSharedPreferences(sp_task.MyPref, Context.MODE_PRIVATE);
        editor =sp.edit();
        textView_task_address.clearFocus();


    }





    @OnClick(R.id.button_done)
    public void btn_submit(){

        validator.validate();

    }



    @OnClick({R.id.textView16,R.id.card_task})
    public void on_click_task_details(){
        BottomView = getLayoutInflater().inflate(R.layout.dialouge_task_details,null);
        dialouge_task = new Dialog(Get_Task_Details.this, R.style.MaterialDialogSheet);
        dialouge_task.setContentView(BottomView);

        TextView tv_heading = dialouge_task.findViewById(R.id.tb_dialouge_heading);
        tv_heading.setText("Task Details");
        EditText et_task_details =  dialouge_task.findViewById(R.id.et_dia_task_details);


        dialouge_task.show();
    }




    @OnClick({R.id.card_task_address,R.id.textView1x6})
    public void card_task_address(){
        BottomView = getLayoutInflater().inflate(R.layout.dialouge_task_address_new,null);
        dialog_address = new Dialog(Get_Task_Details.this, R.style.MaterialDialogSheet);
        dialog_address.setContentView(BottomView);
        TextView tv_heading = dialog_address.findViewById(R.id.tb_dialouge_heading);
        tv_heading.setText("Task Address");
        //et_dia_task_address


        dialog_address.show();
    }

    @OnClick({R.id.card_vehice,R.id.textView1zx6})
    public void card_vehice(){
        BottomView = getLayoutInflater().inflate(R.layout.dailouge_vechicle_requirement,null);
        dialouge_vehicle = new Dialog(Get_Task_Details.this, R.style.MaterialDialogSheet);
        dialouge_vehicle.setContentView(BottomView);
        TextView tv_heading = dialouge_vehicle.findViewById(R.id.tb_dialouge_heading);
        tv_heading.setText("Vehicle Requirement");
        dialouge_vehicle.show();
    }


    @OnClick({R.id.card_when,R.id.textView1})
    public void card_when(){
        BottomView = getLayoutInflater().inflate(R.layout.dialouge_when_time,null);


        dialouge_when = new Dialog(Get_Task_Details.this, R.style.MaterialDialogSheet);
        dialouge_when.setContentView(BottomView);
        TextView tv_heading = dialouge_when.findViewById(R.id.tb_dialouge_heading);
        tv_heading.setText("When");
        date_selector_adapter rv_adapter;
        tv_when = dialouge_when.findViewById(R.id.tv_dialouge_when_day);
        RecyclerView rv_date = dialouge_when.findViewById(R.id.rv_task_when_grid);
        List<date_obj> List_dates = new ArrayList<>();
      //  date_obj dates_s;
        SGPickerView  pickerView = dialouge_when.findViewById(R.id.pickerView);

        ArrayList<String> items = new ArrayList<String>();
        items.add("I'am Flexible");
        items.add("Morning 8 to 12");
        items.add("Noon 12 to 5 pm");
        items.add("Evening 5 pm to 10pm");
        items.add("Night 10 pm to ...");
        pickerView.setItems(items);
        pickerView.setPickerListener(new SGPickerView.SGPickerViewListener() {
            @Override
            public void itemSelected(String item, int index) {
                Toast.makeText(Get_Task_Details.this, " Index = " + String.valueOf(index) + " Item name " + item, Toast.LENGTH_SHORT).show();
            /*    pickerView.getCurrentSelectedItemIndex();
                pickerView.getCurrentSelectedItem();*/
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM-EEEE-dd ");
        Calendar calendar_month = new GregorianCalendar();

            for (int i = 0; i < 7; i++) {

            calendar_month.add(Calendar.DAY_OF_WEEK, 1);
            String month = sdf.format(calendar_month.getTime());

            String[] sep = month.split("-");
            date_obj  dates_s = new date_obj(sep[0],sep[1],sep[2]);
            List_dates.add(dates_s);

        }

       /* dates_s = new date_obj("jan","23","wed");
        for (int i = 0; i < 12; i++) {
            List_dates.add(dates_s);
        }
        dates_s = new date_obj("janx","23s","wexd");
        List_dates.add(dates_s);*/
        rv_date.setHasFixedSize(true);
        rv_date.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
      //  rv_date.addItemDecoration(new SimpleDividerItemDecoration(context, R.drawable.divider));


        rv_adapter = new date_selector_adapter(List_dates,getApplicationContext(),Get_Task_Details.this);
        rv_date.setAdapter(rv_adapter);
        dialouge_when.show();
        rv_adapter.notifyDataSetChanged();
    }
    //card_task_address



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

    @Override
    public void onItemClicked(int position, String data) {

        tv_when.setText(data);

    }
}
