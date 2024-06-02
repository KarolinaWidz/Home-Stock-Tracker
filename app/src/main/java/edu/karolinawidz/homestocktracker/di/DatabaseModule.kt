package edu.karolinawidz.homestocktracker.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.karolinawidz.homestocktracker.data.local.StockDatabase
import edu.karolinawidz.homestocktracker.data.local.StockItemDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabaseName(): String = "HomeStockTracker_database"

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext applicationContext: Context,
        databaseName: String
    ): StockDatabase =
        Room.databaseBuilder(
            applicationContext,
            StockDatabase::class.java,
            databaseName
        ).build()

    @Singleton
    @Provides
    fun provideStockItemDao(database: StockDatabase): StockItemDao = database.stockItemDao()
}