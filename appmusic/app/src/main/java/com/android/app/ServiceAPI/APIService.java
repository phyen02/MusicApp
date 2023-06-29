package com.android.app.ServiceAPI;

public class APIService {
    // Kết nối APIRetrofitClient với DataService
    private static String base_url = "https://phyen02.000webhostapp.com/Server/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
