package killbit.taskrabbit.retrofit;

import android.content.Context;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import killbit.taskrabbit.actvity.Launcher;
import killbit.taskrabbit.retrofit.signup.SignUpReq;
import killbit.taskrabbit.retrofit.signup.signupStatus;
import killbit.taskrabbit.utils.SharedPrefferences;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.Callback;

/**
 * Created by kural mughil selvam on 08-10-2017.
 */

public interface ApiInterface {
    Context applicationContext = Launcher.getContextOfApplication();
    SharedPrefferences sharedPrefferences = (SharedPrefferences) PreferenceManager.getDefaultSharedPreferences(applicationContext);
    public static final String BASE_URL = "https://servicerabbit.pictuscode.com/json/";
    String header = "app_id";
    String header_value = "pictus_service_rabbit_01";


    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

   /* @GET("/2.2/questions?order=desc&sort=votes&site=stackoverflow&tagged=android&filter=withbody")
    Call<ListWrapper<Question>> getQuestions();

    @GET("/2.2/questions/{id}/answers?order=desc&sort=votes&site=stackoverflow")
    Call<ListWrapper<Answer>> getAnswersForQuestion(@Path("id") String questionId);*/

   /* @POST("users/new")
    void createUser(@Body User user, Callback<User> cb);*/

   @POST("user_signup")@FormUrlEncoded
   void rx_signUp(@Header(header) String header_value, @Body SignUpReq signUpReq, Callback<signupStatus> cn);


}
