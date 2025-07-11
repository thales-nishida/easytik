package br.com.thalesnishida.makeeasytik.extentions

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import br.com.thalesnishida.makeeasytik.receivers.MobileDataReceiver

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")

@Suppress("DEPRECATION")
fun Context.registerDataReceiver() {
    val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
    registerReceiver(MobileDataReceiver.mobileDataReceivers, intentFilter)
}

fun Context.unRegisterDataReceiver() {
    unregisterReceiver(MobileDataReceiver.mobileDataReceivers)
}
