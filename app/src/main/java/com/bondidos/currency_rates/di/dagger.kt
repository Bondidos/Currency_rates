package com.bondidos.currency_rates.di

import android.app.Application
import android.content.Context
import com.bondidos.currency_rates.data.RepositoryImpl
import com.bondidos.currency_rates.data.currency_service_impl.RemoteSourceImpl
import com.bondidos.currency_rates.data.remote.CurrencyService
import com.bondidos.currency_rates.data.remote.RemoteSource
import com.bondidos.currency_rates.domain.Repository
import com.bondidos.currency_rates.presentation.main_fragment.MainFragment
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://www.nbrb.by"

@Component(modules = [RepositoryModule::class, Binding::class])
@Singleton
interface AppComponent {

    fun injectMain(main: MainFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

@Module
interface Binding {
    @Binds
    fun bindRemoteSource(remoteSource: RemoteSourceImpl): RemoteSource

    @Binds
    fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    fun context(appInstance: Application): Context
}

@Module(includes = [NetworkModule::class])
object RepositoryModule

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit): CurrencyService =
        retrofit
            .create(CurrencyService::class.java)

    @Provides
    fun provideRetrofit(
        moshiConverterFactory: MoshiConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }).build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    fun provideConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory
            .create(moshi)
}