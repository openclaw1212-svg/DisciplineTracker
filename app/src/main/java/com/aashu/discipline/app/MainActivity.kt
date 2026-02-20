package com.aashu.discipline.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aashu.discipline.core.design.DisciplineTheme
import com.aashu.discipline.core.design.ThemeViewModel
import com.aashu.discipline.core.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val themeState = themeViewModel.themeState.collectAsStateWithLifecycle()

            DisciplineTheme(themeState.value) {
                AppNavHost()
            }
        }
    }
}
