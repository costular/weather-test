package com.costular.weathertest.ui.di.modules

import com.costular.weathertest.BuildConfig
import com.costular.weathertest.data.network.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetModule {

    @Provides
    fun weatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)

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

    @Provides
    fun okhttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor {
                val url =
                        it.request()
                            .url()
                            .newBuilder()
                            .addQueryParameter("appid", BuildConfig.OPEN_WEATHER_API_KEY)
                            .build()

                val newRequest = it.request().newBuilder().url(url).build()
                it.proceed(newRequest)
            }
            .build()

    @Provides
    fun callAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

}