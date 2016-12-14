package com.ways2u.net;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ways2u.test.MyRespone;
import com.ways2u.test.Person;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import java.util.List;
import java.util.Map;

public interface PostJsonService {
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("json.do")
    Call<JsonObject> postJson(@Body RequestBody route);

    @Headers("Content-Type: application/json" )
    @POST("json.do")
    Call<JsonElement> postJson2(@Body JsonElement body);

    @Headers("Content-Type: application/json" )
    @POST("json.do") //body自动调用gson解析
    Call<JsonElement> postJson3(@Body Map<String,String> body);

    @Headers("Content-Type: application/json" )
    @POST("json.do") //body自动调用gson解析
    Call<Person> postJson4(@Body Object body);

    //@Headers("Content-Type: application/json" )
    @GET("persons.do") //body自动调用gson解析
    Call<List<Person>> getPersons();

    @Headers("Content-Type: application/json" )
    @POST("json.do") //body自动调用gson解析
    Call<MyRespone<Person>> postJson5(@Body Object body);

}