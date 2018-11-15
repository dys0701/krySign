package com.keruyun.open.italent.di

import com.keruyun.open.italent.api.ItalentApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {
    @Provides
    @Singleton
    fun provideItalentApi(): ItalentApi = ItalentApi.create()
}