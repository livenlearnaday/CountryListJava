package io.github.livenlearnaday.countrylistjava.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient {


    private static APIClient instance = null;
    private APIInterface myApi;

    private APIClient() {

        /*
        Configure Timeout Settings

We can set timeouts settings on the underlying HTTP client. If we don’t specify a client, Retrofit will create one with default connect and read timeouts. By default, Retrofit uses the following timeouts:

    Connection timeout: 10 seconds
    Read timeout: 10 seconds
    Write timeout: 10 seconds

To customize the timeouts settings you need to configure OkHttp, Retrofit’s network layer. See the code below.

OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build();
         */

        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(3, TimeUnit.MINUTES).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        myApi = retrofit.create(APIInterface.class);
    }

    public static synchronized APIClient getInstance() {
        if (instance == null) {
            instance = new APIClient();
        }
        return instance;
    }

    public APIInterface getMyApi() {
        return myApi;
    }


}
