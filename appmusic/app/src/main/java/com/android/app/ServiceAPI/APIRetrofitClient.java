package com.android.app.ServiceAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRetrofitClient {
    // Khởi tạo Retrofit để tương tác với phía Server
    private static Retrofit retrofit = null;

    /*
    * OkHttpClient: Các giao thức, tương tác với phía server thông qua OkHttpClient
    * readTimeout: Thời gian ngắt khi server trả dữ liệu quá lâu
    * connectTimeout: Thời gian ngắt khi kết nối quá lâu
    *
    * Gson: Convert Json -> Java
    * addConverterFactory: Convert dữ liệu API -> biến Java*/
    public static Retrofit getClient(String base_url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();

        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
}
