package com.keruyun.open.italent.di

import com.keruyun.open.italent.MainActivity
import com.keruyun.open.italent.api.ItalentApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class
        , MainModule::class)
)

interface MainComponent {
    fun inject(mainActivity: MainActivity)
}