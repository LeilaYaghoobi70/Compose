package com.example.composesample.di

import com.example.composesample.BuildConfig
import com.example.composesample.data.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Named("HeaderLoggingInterceptor")
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Named("HeaderInterceptor")
    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader(
                "x-rapidapi-key",
                BuildConfig.API_KEY
            ).addHeader(
                "content-type", "application/json"
            )
            .build()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named("HeaderInterceptor")
        headerInterceptor: Interceptor,
        @Named("HeaderLoggingInterceptor")
        httpLoggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()


    @Provides
    @Singleton
    fun  provideApiService(
        retrofit: Retrofit
    ):ApiServices = retrofit.create(ApiServices::class.java)
}