package br.com.thalesnishida.makeeasytik.extentions

import android.content.Context
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.ConnectivityManager
import br.com.thalesnishida.makeeasytik.receivers.MobileDataReceiver
import java.io.File
import java.io.FileOutputStream

@Suppress("DEPRECATION")
fun Context.registerDataReceiver() {
    val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    registerReceiver(MobileDataReceiver.mobileDataReceivers, intentFilter)
}

fun Context.unRegisterDataReceiver() {
    unregisterReceiver(MobileDataReceiver.mobileDataReceivers)
}
