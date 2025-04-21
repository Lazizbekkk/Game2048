package com.mamadiyorov_lazizbek.game2048

import android.app.Application

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        Repository.setShared(getSharedPreferences("Lazizbek", MODE_PRIVATE))
    }
}