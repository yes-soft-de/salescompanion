package de.sixbits.salescompanion.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.sixbits.salescompanion.config.Consts
import de.sixbits.salescompanion.contacts.DeviceContactService
import de.sixbits.salescompanion.network.HubspotApi
import de.sixbits.salescompanion.service.ContactService
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(
    subcomponents = [
//        NetworkComponent::class,
//        ContactsComponent::class
    ]
)
open class AppModule {

    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Consts.API_ROOT)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Provides
    fun provideDeviceContactService(application: Application): DeviceContactService {
        return DeviceContactService(application.contentResolver)
    }

    @Provides
    fun provideContactsService(
        deviceContactService: DeviceContactService,
        hubspotApi: HubspotApi
    ): ContactService {
        return ContactService(
            deviceContactService = deviceContactService,
            hubspotApi = hubspotApi
        )
    }

    @Provides
    fun provideHubSpotApi(retrofit: Retrofit): HubspotApi {
        return retrofit.create(HubspotApi::class.java)
    }
}