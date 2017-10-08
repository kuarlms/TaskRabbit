package killbit.taskrabbit.retrofit;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kural mughil selvam on 08-10-2017.
 */

public interface ApiInterface {

    String BASE_URL = "https://api.stackexchange.com";

   /* @GET("/2.2/questions?order=desc&sort=votes&site=stackoverflow&tagged=android&filter=withbody")
    Call<ListWrapper<Question>> getQuestions();

    @GET("/2.2/questions/{id}/answers?order=desc&sort=votes&site=stackoverflow")
    Call<ListWrapper<Answer>> getAnswersForQuestion(@Path("id") String questionId);*/

}
