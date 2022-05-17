package com.elslode.onetwotrip.di

import android.app.Application
import com.elslode.onetwotrip.data.database.AppDatabase
import com.elslode.onetwotrip.data.database.TicketsDao
import com.elslode.onetwotrip.data.network.ApiFactory
import com.elslode.onetwotrip.data.network.ApiService
import com.elslode.onetwotrip.data.repository.TicketsRepositoryImpl
import com.elslode.onetwotrip.domain.TicketsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindsRepository(impl: TicketsRepositoryImpl): TicketsRepository

    companion object {

        @Provides
        fun providesApiService(): ApiService {
            return ApiFactory.apiService
        }

        @Provides
        fun providesTicketsDao(application: Application): TicketsDao {
            return AppDatabase.getInstance(application).airTicketsInfoDao()
        }
    }
}