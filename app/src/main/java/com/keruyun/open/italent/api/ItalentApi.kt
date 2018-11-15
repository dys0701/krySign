package com.keruyun.open.italent.api

import android.util.Log
import com.keruyun.open.italent.utils.HttpsUtils
import io.reactivex.Flowable
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import javax.net.ssl.HostnameVerifier

interface ItalentApi {

    @Headers("User-Agent:Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Mobile/14E304 NativeApp BeisenApp ITalent/2.0.1"
            ,"Accept-Language:zh-Hans-CN;q=1, zh-Hant-CN;q=0.9, en-US;q=0.8"
    )
    @POST("/OAuth/Token")
    @FormUrlEncoded
    fun getToken(@Field("app_id")app_id:String, @Field("grant_type")grant_type:String
                 , @Field("is_show_valcode")is_show_valcode:Boolean
                 , @Field("password")password:String
                 , @Field("secret")secret:String
                 , @Field("username")username:String
                )
            : Flowable<Token>

    @Headers("User-Agent:Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Mobile/14E304 NativeApp BeisenApp ITalent/2.0.1"
            ,"Accept-Language:zh-Hans-CN;q=1, zh-Hant-CN;q=0.9, en-US;q=0.8"
    )
    @POST("/api/v1/{tenant_id}/{user_id}/Signin/AddV4")
    @FormUrlEncoded
    fun sigin(@Header("Authorization") authorization: String, @Path("tenant_id") tenant_id:String
              , @Path("user_id") user_id:String, @Field("device_code")device_code:String, @Field("gcj_coordate")gcj_coordate:String
              , @Field("wgs_coordate")wgs_coordate:String
              , @Field("wifi_macaddress")wifi_macaddress:String
    )
            : Flowable<Sigin>

    companion object {
        private const val BASE_URL = "https://tm.tita.com/"
        fun create(): ItalentApi = create(HttpUrl.parse(BASE_URL)!!)
        fun create(httpUrl: HttpUrl): ItalentApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .hostnameVerifier(HostnameVerifier { hostname, session ->  true})
                    .sslSocketFactory(HttpsUtils.getSslSocketFactory(null, null, null))
                    .build()
            return Retrofit.Builder()
                    .baseUrl(httpUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(ItalentApi::class.java)
        }
    }
}