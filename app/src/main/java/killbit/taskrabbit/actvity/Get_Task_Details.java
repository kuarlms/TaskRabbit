package killbit.taskrabbit.actvity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import killbit.taskrabbit.adapters.vehicle_list_adp;
import killbit.taskrabbit.objects.date_obj;
import killbit.taskrabbit.objects.vehicle_list_data;
import killbit.taskrabbit.retrofit.ApiInterface;
import killbit.taskrabbit.retrofit.ApiUtils;
import killbit.taskrabbit.retrofit.bookingStep1.bookingStep1Resp;
import killbit.taskrabbit.utils.sp_task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kural on 10/10/17.
 */




public class Get_Task_Details extends Activity implements Validator.ValidationListener,date_selector_adapter.OnRecyclerListener {

    View BottomView;
    TextView tv_when;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView rv_vehice_req;

    ArrayList<String>sub_cat_list = new ArrayList<>();
    ArrayList<String>sub_cat_list_id = new ArrayList<>();

    List<vehicle_list_data> vehicle_list = new ArrayList<>();
    vehicle_list_data vehicle_data;

    ArrayList<String> time_lis = new ArrayList<>();
    ArrayList<String> time_lis_id = new ArrayList<>();

    vehicle_list_adp vehicle_adp;

    String main_cat,sub_cat_id;
    SGPickerView  pickerView;
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

    @BindView(R.id.tb_normal_title)
    TextView tv_title;

    @BindView(R.id.spinner_task)
    Spinner spin_sub_cat_list;





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

        rv_vehice_req = dialouge_vehicle.findViewById(R.id.rv_vehice_req);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_vehice_req.setLayoutManager(mLayoutManager);
        rv_vehice_req.setItemAnimator(new DefaultItemAnimator());
        vehicle_adp = new vehicle_list_adp(vehicle_list);
        rv_vehice_req.setAdapter(vehicle_adp);

        if(vehicle_list.size()== 0 || vehicle_list!=null){

            dialouge_vehicle.show();

        }else {

            Toast.makeText(this, "Vehicle not needed...", Toast.LENGTH_SHORT).show();

        }




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
        pickerView = dialouge_when.findViewById(R.id.pickerView);
        pickerView.setItems(time_lis);


        pickerView.setPickerListener(new SGPickerView.SGPickerViewListener() {
            @Override
            public void itemSelected(String item, int index) {

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

        rv_adapter = new date_selector_adapter(List_dates,getApplicationContext(),Get_Task_Details.this);
        rv_date.setAdapter(rv_adapter);
        dialouge_when.show();
        rv_adapter.notifyDataSetChanged();
    }
    //card_task_address


    @Override
    protected void onStart() {
        super.onStart();
        tv_title.setText(getIntent().getStringExtra("sub_cat"));
        sub_cat_list.addAll(getIntent().getStringArrayListExtra("list_cat"));
        sub_cat_list_id.addAll(getIntent().getStringArrayListExtra("list_cat_ids"));
      //  Toast.makeText(this, ""+sub_cat_list_id, Toast.LENGTH_SHORT).show();

        main_cat = getIntent().getStringExtra("main_cat");
      //  Toast.makeText(this, "xx--"+main_cat, Toast.LENGTH_SHORT).show();


        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sub_cat_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_sub_cat_list.setAdapter(adapter);


        spin_sub_cat_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sub_cat_id = sub_cat_list_id.get(i);
                mtd_booking_step1();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

       /* sub_cat_id = sub_cat_list_id.get(spin_sub_cat_list.getSelectedItemPosition());

        mtd_booking_step1();*/

    }

    private void mtd_booking_step1() {

        mAPIService.rf_booking_step1(ApiInterface.header_value,sp.getString(sp_task.Sp_email,""),main_cat,sub_cat_id)
                .enqueue(new Callback<bookingStep1Resp>() {


                    @Override
                    public void onResponse(Call<bookingStep1Resp> call, Response<bookingStep1Resp> response) {

                        for (int i = 0; i <response.body().getDropdownData().getTimingList().size() ; i++) {

                         time_lis.add(response.body().getDropdownData().getTimingList().get(i).getName());
                         time_lis_id.add(response.body().getDropdownData().getTimingList().get(i).getTimeId());

                        }
                 /*       Toast.makeText(Get_Task_Details.this, ""+time_lis, Toast.LENGTH_SHORT).show();
                     card_when();*/

                        for (int i = 0; i <response.body().getDropdownData().getVehicleList().size() ; i++) {

                            vehicle_data = new vehicle_list_data(response.body().getDropdownData().getVehicleList().get(i).getVehicleName(),
                                    response.body().getDropdownData().getVehicleList().get(i).getVehicleId());

                            vehicle_list.add(vehicle_data);



                        }


                    }

                    @Override
                    public void onFailure(Call<bookingStep1Resp> call, Throwable t) {
                        Toast.makeText(Get_Task_Details.this, ""+t, Toast.LENGTH_LONG).show();
                    }
                });

    }

    @Override
    public void onValidationSucceeded() {

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
