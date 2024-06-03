package edu.karolinawidz.homestocktracker.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.karolinawidz.homestocktracker.data.repository.StockItemOfflineRepository
import edu.karolinawidz.homestocktracker.data.repository.StockItemRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repository: StockItemOfflineRepository): StockItemRepository
}