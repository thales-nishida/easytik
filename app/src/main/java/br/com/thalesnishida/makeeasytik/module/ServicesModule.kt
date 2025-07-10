package br.com.thalesnishida.makeeasytik.module

import android.content.Context
import br.com.thalesnishida.makeeasytik.services.AudioPlayService
import br.com.thalesnishida.makeeasytik.services.AudioPlayServiceImpl
import br.com.thalesnishida.makeeasytik.services.CacheService
import br.com.thalesnishida.makeeasytik.services.CacheServiceImpl
import br.com.thalesnishida.makeeasytik.services.ElevenLabsService
import br.com.thalesnishida.makeeasytik.services.ElevenLabsServiceImpl
import br.com.thalesnishida.makeeasytik.services.GeminiService
import br.com.thalesnishida.makeeasytik.services.GeminiServiceImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {
    @Provides
    fun provideCacheService(@ApplicationContext context: Context): CacheService {
        return CacheServiceImpl(context)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideGeminiService(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): GeminiService {
        return GeminiServiceImpl(
            gson = gson,
            okHttpClient = okHttpClient
        )
    }

    @Provides
    fun provideElevenLabsService(
        okHttpClient: OkHttpClient,
        gson: Gson,
        cacheService: CacheService,
    ): ElevenLabsService {
        return ElevenLabsServiceImpl(
            gson = gson,
            okHttpClient = okHttpClient,
            cacheService = cacheService
        )
    }

    @Provides
    fun provideAudioPlayService(
        cacheService: CacheService
    ) : AudioPlayService {
        return AudioPlayServiceImpl(cacheService)
    }
}