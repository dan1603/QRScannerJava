package com.kalashnyk.denys.qrscanner.di.module;

import com.kalashnyk.denys.qrscanner.BuildConfig;
import com.kalashnyk.denys.qrscanner.di.scope.ApiScope;
import com.kalashnyk.denys.qrscanner.repository.server.ApiService;
import com.kalashnyk.denys.qrscanner.repository.server.QRScannerServerCommunicator;

import java.util.concurrent.TimeUnit;


import dagger.Module;
import dagger.Provides;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    private static final String API_URL = "http://server.zz/";

    @Provides
    @ApiScope
    public QRScannerServerCommunicator provideQRScannerCommunicator(ApiService apiService) {
        return new QRScannerServerCommunicator(apiService);
    }

    @Provides
    @ApiScope
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @ApiScope
    public Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.baseUrl(API_URL).build();
    }

    @Provides
    @ApiScope
    public Retrofit.Builder provideRetrofitBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(5, 30, TimeUnit.SECONDS))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }

        return new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

}