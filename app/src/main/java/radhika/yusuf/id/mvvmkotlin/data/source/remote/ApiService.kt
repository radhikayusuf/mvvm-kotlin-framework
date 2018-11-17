package radhika.yusuf.id.mvvmkotlin.data.source.remote

import io.reactivex.Observable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import radhika.yusuf.id.mvvmkotlin.BuildConfig
import radhika.yusuf.id.mvvmkotlin.MyApplication
import radhika.yusuf.id.mvvmkotlin.data.model.HeroesModel
import radhika.yusuf.id.mvvmkotlin.utils.base.BaseApiModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
 * Created by radhikayusuf on 17/11/2018.
 */

interface ApiService {


    @GET("master/superheroes.json")
    fun getListHeroes(): Observable<BaseApiModel<List<HeroesModel>?>>


    companion object Factory {

        val getApiService: ApiService by lazy {
            val mLoggingInterceptor = HttpLoggingInterceptor()
            mLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val cacheSize = (5 * 1024 * 1024).toLong()
            val appCache = Cache(MyApplication.getContext().cacheDir, cacheSize)
            val mClient = if (BuildConfig.DEBUG) {
                OkHttpClient.Builder()
                        .cache(appCache)
                        .addInterceptor { chain ->
                            val request = chain.request().apply {
                                newBuilder().header("Cache-Control",
                                    "public, max-age=" + 5).build()
                            }
                            chain.proceed(request)
                        }
                        .addInterceptor(mLoggingInterceptor)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .build()
            } else {
                OkHttpClient.Builder()
                        .cache(appCache)
                        .addInterceptor { chain ->
                            val request = chain.request().apply {
                                newBuilder().header("Cache-Control",
                                    "public, max-age=" + 5).build()
                            }
                            chain.proceed(request)
                        }
                        .readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .build()
            }

            val mRetrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mClient)
                    .build()

            mRetrofit.create(ApiService::class.java)
        }
    }
}