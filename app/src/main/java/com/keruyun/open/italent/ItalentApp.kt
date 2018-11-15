package com.keruyun.open.italent

import android.app.Application
import com.keruyun.open.italent.di.AppModule
import com.keruyun.open.italent.di.DaggerMainComponent
import com.keruyun.open.italent.di.MainComponent

class ItalentApp: Application() {
    companion object {
        lateinit var mainComponent: MainComponent
    }

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder().appModule(AppModule(this)).build()

    }
}