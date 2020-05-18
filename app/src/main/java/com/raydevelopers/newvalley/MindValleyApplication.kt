package com.raydevelopers.newvalley

import android.app.Application

class MindValleyApplication : Application() {

    companion object {
        private var instance: MindValleyApplication? = null

        fun applicationContext() : MindValleyApplication {
            return instance as MindValleyApplication

        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}