package br.com.thalesnishida.makeeasytik.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.CallSuper

abstract class HiltBroadcastReceiver : BroadcastReceiver(){

    @CallSuper
    override fun onReceive(context: Context, intent: Intent) {
    }
}