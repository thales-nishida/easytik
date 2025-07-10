package br.com.thalesnishida.makeeasytik.viewmodels

import androidx.lifecycle.ViewModel
import br.com.thalesnishida.makeeasytik.services.AudioPlayService
import br.com.thalesnishida.makeeasytik.services.CacheService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ServicesProviderViewModel @Inject constructor(
    val cacheService: CacheService,
    val audiPlayService: AudioPlayService
) : ViewModel()