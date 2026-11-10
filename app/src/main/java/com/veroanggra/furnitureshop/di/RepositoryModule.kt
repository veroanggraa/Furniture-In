package com.veroanggra.furnitureshop.di

import com.veroanggra.furnitureshop.data.repository.FakeProductRepository
import com.veroanggra.furnitureshop.data.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(impl: FakeProductRepository): ProductRepository
}
