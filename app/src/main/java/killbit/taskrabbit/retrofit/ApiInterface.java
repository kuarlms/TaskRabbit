package killbit.taskrabbit.retrofit;

import android.content.Context;
import android.database.Observable;
import android.preference.PreferenceManager;

import killbit.taskrabbit.actvity.Launcher;
import killbit.taskrabbit.retrofit.signIn.LoginResp;
import killbit.taskrabbit.retrofit.signup.signupStatus;
import killbit.taskrabbit.retrofit.uploadPpic.uplPpicStatus;
import killbit.taskrabbit.utils.sp_task;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
/**
 * Created by kural mughil selvam on 08-10-2017.
 */

public interface ApiInterface {
    Context applicationContext = Launcher.getContextOfApplication();
    sp_task sharedPrefferences = (sp_task) PreferenceManager.getDefaultSharedPreferences(applicationContext);

  static   String header = "app_id";
  static   String header_value = "pictus_service_rabbit_01";

 /* @POST("/commandService/addDevice")
  void Registration(@Body RegRequest body, Callback<RegResp> callback);*/

    @Multipart
    @POST("user_signup")
    Call<signupStatus> rf_signUp(@Header(header)String header_value,@Part("first_name") String first_name,
                        @Part("last_name") String last_name,
                        @Part("email") String email,
                        @Part("password") String password,
                        @Part("phone") String phone,
                        @Part("zipcode") String zipcode );

    @Multipart
    @POST("user_login_process")
    Call<LoginResp> rf_signIn(@Header(header)String header_value, @Part("login_email") String login_email,
                              @Part("login_password") String login_password);

    //https://stackoverflow.com/questions/39953457/how-to-upload-image-file-in-retrofit-2

    @Multipart
    @POST("user/updateprofile")
    Observable<uplPpicStatus> rf_updateProfile(@Part("email") RequestBody id, @Part MultipartBody.Part upload_profile_picture);

   /* //pass it like this
    File file = new File("/storage/emulated/0/Download/Corrections 6.jpg");
    RequestBody requestFile =
            RequestBody.create(MediaType.parse("multipart/form-data"), file);

    // MultipartBody.Part is used to send also the actual file name
    MultipartBody.Part body =
            MultipartBody.Part.createFormData("image", file.getName(), requestFile);

    // add another part within the multipart request
    RequestBody fullName =
            RequestBody.create(
                    MediaType.parse("multipart/form-data"), "Your Name");

service.updateProfile(id, fullName, body, other)*/

    @Multipart
    @POST("available_balance")
    Call<signupStatus> rf_avail_bal(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("change_user_password")
    Call<signupStatus> rf_password_change(@Header(header)String header_value,@Part("email") String email,
                                 @Part("old_password") String old_password,
                                 @Part("new_password") String new_password,
                                 @Part("confirm_password") String confirm_password);


    @Multipart
    @POST("deactivate_myaccount")
    Call<signupStatus> rf_Deact_account(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("save_notification")
    Call<signupStatus> rf_Notification(@Header(header)String header_value,@Part("email") String email,
                                       @Part("notify_type") String notify_type);


    @Multipart
    @POST("home_page")
    Call<signupStatus> rf_home_page(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("booking_step1")
    Call<signupStatus> rf_booking_step1(@Header(header)String header_value,@Part("email") String email,
                                       @Part("cat_id") String cat_id,@Part("subcat_id") String subcat_id);

    @Multipart
    @POST("find_tasker")
    Call<signupStatus> rf_find_tasker(@Header(header)String header_value,@Part("email") String email,
                                        @Part("cat_id") String cat_id,@Part("subcat_id") String subcat_id,
                                      @Part("task_date") String task_date,@Part("task_time") String task_time,
                                      @Part("city") String city,@Part("page") String page,@Part("vehicle_id") String vehicle_id);

/*    task_date   ex:2017-10-25
    task_time   ex: 0
    city        ex: chennai
    page        ex: 1  (each page get 5 tasker)
    vehicle_id  if exist send otherwise empty*/

    @Multipart
    @POST("booking_page")
    Call<signupStatus> rf_booking_page(@Header(header)String header_value,@Part("email") String email,
                                      @Part("cat_id") String cat_id,@Part("subcat_id") String subcat_id,
                                      @Part("task_date") String task_date,@Part("task_time") String task_time,
                                      @Part("city") String city,@Part("vehicle_id") String vehicle_id,
                                       @Part("tasker_id") String tasker_id);

    @Multipart
    @POST("Booking")
    Call<signupStatus> rf_booking(@Header(header)String header_value,@Part("email") String email,
                                       @Part("cat_id") String cat_id,@Part("subcat_id") String subcat_id,
                                       @Part("task_date") String task_date,@Part("task_time") String task_time,
                                       @Part("city") String city,@Part("vehicle_id") String vehicle_id,
                                       @Part("tasker_id") String tasker_id,@Part("task_description") String task_description,
                                  @Part("credit_card_type") String credit_card_type,@Part("number") String number,
                                  @Part("cvc") String cvc,@Part("exp_month") String exp_month,@Part("exp_year") String exp_year);

    @Multipart
    @POST("dashboard_user_task_history_pending")
    Call<signupStatus> rf_dashboard_user_task_history_pending(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("dashboard_user_task_history_approved")
    Call<signupStatus> rf_dashboard_user_task_history_approved(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("user_cancel_task")
    Call<signupStatus> rf_user_cancel_task(@Header(header)String header_value,@Part("email") String email,@Part("booking_id") String booking_id);

    @Multipart
    @POST("dashboard_user_task_history_completed")
    Call<signupStatus> rf_dashboard_user_task_history_completed(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("dashboard_user_task_history_pending")
    Call<signupStatus> rf_dashboard_user_task_history_Cancelled(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("user_active_task")
    Call<signupStatus> rf_user_active_task(@Header(header)String header_value,@Part("email") String email);

    @Multipart
    @POST("task_completed")
    Call<signupStatus> rf_task_completed(@Header(header)String header_value,@Part("email") String email,@Part("booking_id") String booking_id,
                                         @Part("task_hour") String task_hour);

    @Multipart
    @POST("transaction_list")
    Call<signupStatus> rf_transaction_list(@Header(header)String header_value,@Part("email") String email,@Part("page") String page);

    @Multipart
    @POST("billing_info")
    Call<signupStatus> rf_billing_info(@Header(header)String header_value,@Part("email") String email,@Part("number") String number,
                                         @Part("cvc") String cvc,@Part("exp_month") String exp_month,@Part("exp_year") String exp_year);


}

/*
  Tasker App:

        22.Tasker signup:

        url: json/tasker_signup
        params:
        first_name
        last_name
        email
        password
        phone

        23.signup tasker_about_us

        url: json/tasker_about_us
        params:
        email
        work_city
        home
        street
        city
        state
        zipcode
        dob
        distance
        detail1
        detail2
        detail3
        hear_about
        vehicle_types [pass value ex: 1,2,3]
        tasker_step1
        tasker_step2

        24.signup task main category and sub category information

        url: json/get_services_listing_inof
        params:
        email

        25.signup tasker Save task

        url: json/save_service_category
        params:
        email
        task_category_id
        subcat_id [ex: 4,5]
        price
        task [ex: 1,2][only cleaning otherwise give empty ]
        tasker_description
        experience


        26.signup tasker delete task

        url: json/delete_tasker_category
        params:
        email
        task_category_id

        27.signup tasker existing task

        url: json/get_existing_tasker_info
        params:
        email
        task_category_id

        28. Tasker final pay step
        url:json/tasker_signup_pay
        params:
        email
        number
        cvc
        exp_month
        exp_year

        29. Tasker active task
        url:json/tasker_active_task
        params:
        email

        30. Tasker pending task
        url:json/tasker_pending_task
        params:
        email

        31. Tasker approved task
        url:json/tasker_approved_task
        params:
        email

        32. Tasker completed task
        url:json/tasker_completed_task
        params:
        email

        32. Tasker cancel task
        url:json/tasker_enquires_load_cancel
        params:
        email

        33. Tasker booking status update
        url:json/tasker_task_respond
        params:
        email
        booking_id
        status  {Accept or Declined}


        34. Tasker edit task info     {it fetch info about particular task  and use update save_service_category}
        url:json/get_task_info
        params:
        email


        35. Tasker edit profile     { use update tasker_about_us}
        url:json/get_tasker_info
        params:
        email

        36. Tasker get document picture
        url:json/get_tasker_document_picture
        params:
        email

        37. Tasker save document picture
        url:json/upload_tasker_document_picture
        params:
        email
        upload_document_picture

        38. Tasker stripe setting
        url:json/stripe_setting
        params:
        email


        39. Tasker get already blocked dates setting
        url:json/get_blocked_dates
        params:
        email

        40. Tasker save block date
        url:json/save_block_date
        params:
        email
        task_date [dates in comma ex: 2017-10-23,2017-10-24]
        task_time

        41. Tasker delete block date
        url:json/delete_block_dates
        params:
        email
        block_id

        41. Forgot Password
        url:json/forgot_password
        params:
        email
		*/
