package com.ways2u.net;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.istack.internal.NotNull;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface JsonService {
    @GET("{url}")
    Call<JsonElement> getData(
            @Path(value = "url",encoded=true) String url,
            @NotNull @QueryMap Map<String, String> map
    );

    @FormUrlEncoded
    @POST("{url}")
    Call<JsonElement> postData(
            @Path(value = "url",encoded=true) String url,
            @NotNull @FieldMap Map<String, String> map
    );

    @Multipart
    @POST("{url}")
    Call<JsonElement> uploadFile(
            @Path(value = "url",encoded=true) String url,
            @NotNull @PartMap Map<String, RequestBody> map
    );
}