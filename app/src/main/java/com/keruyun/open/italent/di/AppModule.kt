package com.keruyun.open.italent.di

import android.app.Application
import android.content.Context
import com.keruyun.open.italent.ItalentApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: ItalentApp) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication() : Application = app

}