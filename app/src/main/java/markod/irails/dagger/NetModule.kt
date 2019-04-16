package markod.irails.dagger

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import markod.irails.AllStationsPresenter
import markod.irails.IRailApi
import markod.irails.stationdetails.IncomingTrainsToStationPresenter
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
class NetModule(var application: Application){
    //region application
    @Provides
    @Singleton
    fun providesApplication(): Application = application
    //endregion

    //region sharedPreference
    @Provides
    @Singleton
    internal fun providesSharedPreferences(application: Application):
            SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    //endregion

    //region communication with internet and gson
    @Provides
    @Singleton
    internal fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }


    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.cache(cache)
        return client.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit( okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.irishrail.ie")//we use google because base url should be valid and cannot be empty, it's replaced in app anyway
            .client(okHttpClient)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideIRailApi(retrofit: Retrofit): IRailApi {
        return retrofit.create(IRailApi::class.java)
    }
    //endregion

    @Provides
    @Singleton
    internal fun provideAllStationsPresenter(iRailApi: IRailApi): AllStationsPresenter {
        return AllStationsPresenter(iRailApi)
    }

    @Provides
    internal fun provideStationArrivingTrainsPresenter(iRailApi: IRailApi): IncomingTrainsToStationPresenter {
        return IncomingTrainsToStationPresenter(iRailApi)
    }
}