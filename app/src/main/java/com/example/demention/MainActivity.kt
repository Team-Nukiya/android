package com.example.demention

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demention.feature.signup.LocationScreen
import com.example.demention.feature.signup.PassScreen
import com.example.demention.navigation.AppNavigationItem
import com.example.demention.ui.theme.DementionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DementionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DementionApp()
                }
            }
        }
    }
}

@Composable
fun DementionApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppNavigationItem.Pass.route) {
        composable(AppNavigationItem.Pass.route) {
            PassScreen(navController = navController)
        }
        composable(AppNavigationItem.Location.route) {
            LocationScreen(navController = navController)
        }
    }
}
