package org.example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface BerriesService {


    @GET("/api/v1/supplier/incomes")

    Call<BerriesInfo> getSpaceInfo(@Header("Authorization:") String apiKey,@Query("dateFrom") String dateFrom);
}
