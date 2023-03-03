package org.example;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface BerriesService {


    @GET("/api/v1/supplier/stocks")

    Call<List<BerriesInfo>> getSpaceInfo(@Header("Authorization") String apiKey, @Query("dateFrom") String dateFrom);
}
