package com.romanik.footballcompetitions.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.romanik.footballcompetitions.BuildConfig
import com.romanik.footballcompetitions.data.rest.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { providesHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApi(get()) }
}

fun providesHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
    }

    val client = OkHttpClient.Builder()
    client.readTimeout(60, TimeUnit.SECONDS)
    client.writeTimeout(60, TimeUnit.SECONDS)
    client.connectTimeout(60, TimeUnit.SECONDS)
    client.addInterceptor(loggingInterceptor)
    client.addInterceptor { chain ->
        chain.proceed(
            chain.request()
                .newBuilder()
                .header("X-Auth-Token", "e588a0564a7a40d89a9f46b525dc2f68")
                .method(chain.request().method(), chain.request().body())
                .build()
        )
    }

    return client.build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd").setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()
}

fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
