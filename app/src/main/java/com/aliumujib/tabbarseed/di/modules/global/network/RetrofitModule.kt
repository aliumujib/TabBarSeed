package com.aliumujib.tabbarseed.di.modules.global.network

import com.aliumujib.tabbarseed.BuildConfig
import com.aliumujib.tabbarseed.data.retrofit.ApiClient
import com.aliumujib.tabbarseed.data.retrofit.RedirectInterceptor
import com.aliumujib.tabbarseed.di.scopes.TabBarSeedApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ayokunlepaul on 27/11/2018.
 */
@Module(includes = [FactoryModule::class, InterceptorsModule::class])
class RetrofitModule {

    @TabBarSeedApplicationScope @Provides
    internal fun provideRetrofit(client: OkHttpClient, callAdapterFactory: RxJava2CallAdapterFactory, converterFactory: GsonConverterFactory): Retrofit {
        val builder = Retrofit.Builder()
        builder.baseUrl(BuildConfig.API_URL)
        builder.addCallAdapterFactory(callAdapterFactory)
        builder.addConverterFactory(converterFactory)

        return builder.build()
    }

    @TabBarSeedApplicationScope @Provides
    internal fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor, redirectInterceptor: RedirectInterceptor): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()

        httpClientBuilder.addInterceptor(loggingInterceptor)
        httpClientBuilder.addInterceptor(redirectInterceptor)
        httpClientBuilder.followRedirects(false)
        httpClientBuilder.followSslRedirects(false)

        return httpClientBuilder.build()
    }
}