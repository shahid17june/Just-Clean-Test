package com.q8coders.justClean.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.q8coders.justClean.BuildConfig
import com.q8coders.justClean.application.MyApplication
import com.q8coders.justClean.network.RetrofitApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.CertificatePinner



/**
 * @Created by shahid on 8/26/2018.
 */
@Module
class ApplicationModule(val mContext: MyApplication) {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideGsonInstance(): Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    @Provides
    @Singleton
    fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideHttpInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(interceptor: HttpLoggingInterceptor): OkHttpClient {
        if(BuildConfig.DEBUG){
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }else{
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(converterFactory: GsonConverterFactory, adapterFactory: RxJava2CallAdapterFactory,okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.PRE_HOST.plus(BuildConfig.HOST))
                    .addConverterFactory(converterFactory)
                    .addCallAdapterFactory(adapterFactory)
                    .client(okHttpClient)
                    .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RetrofitApi = retrofit.create(RetrofitApi::class.java)

    @Provides
    @Singleton
    fun provideContext(): MyApplication = mContext

}