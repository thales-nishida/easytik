package br.com.thalesnishida.makeeasytik.module

import android.content.Context
import br.com.thalesnishida.makeeasytik.services.CacheService
import br.com.thalesnishida.makeeasytik.services.impl.CacheServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {
    @Provides
    fun provideCacheService(@ApplicationContext context: Context): CacheService {
        return CacheServiceImpl(context)
    }
}