package br.com.thalesnishida.makeeasytik.services.impl

import android.content.Context
import br.com.thalesnishida.makeeasytik.services.CacheService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CacheServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CacheService {
    override fun setTest(test: String) {
        val sharedPref = context.getSharedPreferences(CACHE, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(TEST, test)
            apply()
        }
    }

    companion object {
        const val CACHE = "cache"
        const val TEST = "test"
    }
}