package com.jhs.searchbookapp.data.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SearchBookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
