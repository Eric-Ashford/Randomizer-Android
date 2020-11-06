package com.ashford.randomizer.logging

import android.app.Application
import timber.log.Timber

class RandomizerApplication: Application() {
    override fun onCreate(){
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}