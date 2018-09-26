package com.tfb.project.api;


import com.tfb.project.domain.dto.RandomValue;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by leish on 2017/11/4.
 */
public interface HelloClient {

    @GET("hello/{name}")
    String sayHi(@Path("name") String name);

    @GET("/random")
    RandomValue greeting();
}
