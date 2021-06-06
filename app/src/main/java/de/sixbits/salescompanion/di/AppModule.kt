package de.sixbits.salescompanion.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import de.sixbits.salescompanion.config.Consts
import de.sixbits.salescompanion.network.HubspotApi
import de.sixbits.salescompanion.network.HubspotManager
import de.sixbits.salescompanion.network.NetworkComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(
    subcomponents = [
        NetworkComponent::class
//        MainComponent::class,
//        DatabaseComponent::class
    ]
)
open class AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Consts.API_ROOT)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

//    @Singleton
//    @Provides
//    fun provideNetworkUtils(application: Application): NetworkUtils {
//        return NetworkUtils(application)
//    }

//    @Singleton
//    @Provides
//    fun provideDatabase(application: Application): CacheDatabase {
//        return Room
//            .databaseBuilder(
//                application,
//                CacheDatabase::class.java,
//                "cache-database.db"
//            )
//            .build()
//    }

    // Provide here
//    @Singleton
//    @Provides
//    fun provideCacheDao(cacheDatabase: CacheDatabase): CacheDao {
//        return cacheDatabase.cacheDao()
//    }


    @Singleton
    @Provides
    fun provideHubSpotManager(pixabayService: HubspotApi): HubspotManager {
        return HubspotManager(pixabayService)
    }

    @Singleton
    @Provides
    fun provideHubSpotApi(retrofit: Retrofit): HubspotApi {
        return retrofit.create(HubspotApi::class.java)
    }
}