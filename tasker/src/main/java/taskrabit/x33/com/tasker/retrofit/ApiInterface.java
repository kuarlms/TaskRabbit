package taskrabit.x33.com.tasker.retrofit;

import android.content.Context;
import android.preference.PreferenceManager;

import taskrabit.x33.com.tasker.actvity.Launcher;
import taskrabit.x33.com.tasker.utils.sp_task;

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
