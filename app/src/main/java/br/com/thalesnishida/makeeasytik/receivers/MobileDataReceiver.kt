package br.com.thalesnishida.makeeasytik.receivers

import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MobileDataReceiver : HiltBroadcastReceiver() {

    companion object {
        val mobileDataReceivers: MobileDataReceiver by lazy {
            MobileDataReceiver()
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
    }
}