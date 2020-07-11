package wineapp.java.wineappproject.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GetDataService {

    @Headers("Content-Type: application/json")
    @GET("/food/wine/pairing")
    Call<ModelWine> getData(@Query("food") String food, @Query("apiKey") String apiKey);
}
