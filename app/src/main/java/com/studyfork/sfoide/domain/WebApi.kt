package com.studyfork.sfoide.domain

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.studyfork.sfoide.BuildConfig
import com.studyfork.sfoide.data.Result
import com.studyfork.sfoide.util.ContextHelper
import io.reactivex.Flowable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object WebApi {
    private val TAG = WebApi::class.java.simpleName
    private const val URL = "https://randomuser.me/"

    @Volatile
    private var instance: WebApi? = null

    private lateinit var okHttpClient: OkHttpClient
    private lateinit var webApiInterface: WebApiInterface
    init {
        updateHttpClient()
    }

    private fun getInstance() =
        instance ?: synchronized(this) {
            instance ?: WebApi.also { instance = it }
        }

    private fun updateHttpClient() {
        okHttpClient = getOkHttpClient()
        webApiInterface = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WebApiInterface::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }


        val httpCacheDirectory = File(ContextHelper.context?.cacheDir, "http")
        val cacheSize = 32 * 1024 * 1024L

        val client = OkHttpClient.Builder()
            .cache(Cache(httpCacheDirectory, cacheSize))
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(logInterceptor)
            .addNetworkInterceptor(FlipperOkhttpInterceptor(NetworkFlipperPlugin()))
            .build()

        client.dispatcher.maxRequests = 16

        return client
    }

    fun getPersonList(): Flowable<Result> {
        return webApiInterface.getPersonList()
    }
}