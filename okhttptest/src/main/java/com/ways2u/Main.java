package com.ways2u;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by huanglong on 2016/10/18.
 */
public class Main {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    public static void main(String args[]) throws IOException
    {

        Main m= new Main();

        //String res = m.run("http://127.0.0.1:8888");
        //System.out.println(res);
        String res = m.post("http://127.0.0.1:8888/json","{\"test\":\"ttt\"}");
        System.out.println(res);
        res = m.post("http://127.0.0.1:8888/json1","{\"test\":\"ttt2\"}");
        System.out.println(res);

    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
