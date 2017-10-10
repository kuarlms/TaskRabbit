package killbit.taskrabbit.retrofit;

import android.content.Context;
import android.preference.PreferenceManager;

import killbit.taskrabbit.actvity.Launcher;
import killbit.taskrabbit.retrofit.signup.signupStatus;
import killbit.taskrabbit.utils.SharedPrefferences;
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
    SharedPrefferences sharedPrefferences = (SharedPrefferences) PreferenceManager.getDefaultSharedPreferences(applicationContext);

  static   String header = "app_id";
  static    String header_value = "pictus_service_rabbit_01";

/*
    @Multipart
    @POST("/commandService/videoFileUpload")
    void UploadVideo(@Header("authtoken") String authToken, @Part("fileupload") TypedFile file, @Part("from_email") String from_email,
                     @Part("to_email") String to_email, @Part("title") String title, @Part("tags") String tags,
                     @Part("privacy") String privacy, @Part("country") String country, @Part("language") String language, @Part("thumbnail") TypedFile thumbnail,
                     Callback<SVideoResp> callback);
*/

    @Multipart
   // @Headers(header+" : "+header_value)
    @POST("user_signup")
    Call<signupStatus> rx_signUp(@Header(header)String header_value,@Part("first_name") String first_name,
                        @Part("last_name") String last_name,
                        @Part("email") String email,
                        @Part("email") String password,
                        @Part("email") String phone);




}
