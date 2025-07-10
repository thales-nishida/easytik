package br.com.thalesnishida.makeeasytik.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.thalesnishida.makeeasytik.viewmodels.HomeViewModel
import java.io.File

@Composable
fun AudioPlay(
    audioFile: File,
    viewModel: HomeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var isCurrentlyPlayingThisFile = false
    if (uiState.audioFile.isNotEmpty()) {
        isCurrentlyPlayingThisFile = uiState.isPlaying && uiState.audioFile == audioFile.name
    }
    val editFileName = audioFile.name.substringAfter("_").substringBefore(".mp3").uppercase()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp, start = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                if (uiState.isPlaying) viewModel.pauseAudio() else viewModel.playAudio(audioFile.name)
            },
        ) {
            Icon(
                imageVector = if (isCurrentlyPlayingThisFile) Icons.Default.Warning
                else Icons.Default.PlayArrow,
                contentDescription = "Play/Pause",
                modifier = Modifier.size(48.dp)
            )
        }
        IconButton(
            onClick = { if (isCurrentlyPlayingThisFile) viewModel.stopAudio() },
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Play/Pause",
                modifier = Modifier.size(48.dp)
            )
        }

        IconButton(
            onClick = { viewModel.downloadFile(audioFile) },
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Play/Pause",
                modifier = Modifier.size(48.dp)
            )
        }

        IconButton(
            onClick = { viewModel.deleteAudio(audioFile.name) },
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Play/Pause",
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(editFileName)
    }
}