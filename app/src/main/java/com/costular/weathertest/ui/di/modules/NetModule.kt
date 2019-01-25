package com.costular.weathertest.ui.di.modules

import com.costular.weathertest.BuildConfig
import com.costular.weathertest.data.network.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Singleton
    @Provides
    fun weatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

    @Singleton
    @Provides
    fun retrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE)
            .client(okHttpClient)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Singleton
    @Provides
    fun okhttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor {
                val url =
                        it.request()
                            .url()
                            .newBuilder()
                            .addQueryParameter("appid", BuildConfig.OPEN_WEATHER_API_KEY)
                            .addQueryParameter("units", "metric")
                            .build()

                val newRequest = it.request().newBuilder().url(url).build()
                it.proceed(newRequest)
            }
            .build()

    @Singleton
    @Provides
    fun callAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}