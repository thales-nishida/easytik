package br.com.thalesnishida.makeeasytik

import android.app.Application
import br.com.thalesnishida.makeeasytik.extentions.registerDataReceiver
import br.com.thalesnishida.makeeasytik.extentions.unRegisterDataReceiver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MakeEasyTikApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        registerDataReceiver()
    }

    override fun onTerminate() {
        super.onTerminate()
        unRegisterDataReceiver()
    }
}