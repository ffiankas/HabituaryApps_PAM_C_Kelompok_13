package ffs.mobile.habituaryapps.api;

import ffs.mobile.habituaryapps.model.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("goals.php")
    Call<ApiResponse> getGoals();
}
