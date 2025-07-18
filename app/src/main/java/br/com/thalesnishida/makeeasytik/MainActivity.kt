package br.com.thalesnishida.makeeasytik

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.thalesnishida.makeeasytik.composables.AppHeader
import br.com.thalesnishida.makeeasytik.navigation.AppNavHost
import br.com.thalesnishida.makeeasytik.navigation.navigateToHomeScreen
import br.com.thalesnishida.makeeasytik.navigation.navigateToSettingsScreen
import br.com.thalesnishida.makeeasytik.ui.theme.CustomShape
import br.com.thalesnishida.makeeasytik.ui.theme.Typography
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(navController: NavHostController = rememberNavController()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    MaterialTheme(typography = Typography, shapes = CustomShape) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text("Menu", modifier = Modifier.padding(16.dp))
                    HorizontalDivider()
                    NavigationDrawerItem(
                        label = { Text(text = "Home") },
                        selected = false,
                        onClick = {
                            navController.navigateToHomeScreen()
                            scope.launch { drawerState.close() }
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text(text = "Settings") },
                        selected = false,
                        onClick = {
                            navController.navigateToSettingsScreen()
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Menu") },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu"
                                )
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    AppNavHost(navController)
                }
            }
        }
    }
}
