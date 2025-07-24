package com.example.fetchtakehometest.di

import com.example.fetchtakehometest.data.network.ItemApiService
import com.example.fetchtakehometest.data.network.ItemRepository
import com.example.fetchtakehometest.data.network.NetworkItemRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindItemRepository(repository: NetworkItemRepository): ItemRepository
}


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hiring.fetch.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ItemApiService {
        return retrofit.create(ItemApiService::class.java)
    }
}