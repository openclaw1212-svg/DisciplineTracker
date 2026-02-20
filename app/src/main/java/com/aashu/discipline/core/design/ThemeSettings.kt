package com.aashu.discipline.core.design

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aashu.discipline.core.data.datastore.ThemePreferencesRepository
import com.aashu.discipline.core.domain.model.ThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val repository: ThemePreferencesRepository = ThemePreferencesRepository()
) : ViewModel() {

    private val _themeState = MutableStateFlow(ThemeState())
    val themeState: StateFlow<ThemeState> = _themeState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.themeSettings.collect { prefs ->
                _themeState.value = ThemeState(
                    themeMode = prefs.mode,
                    dynamicColor = prefs.dynamicColor
                )
            }
        }
    }

    fun updateTheme(mode: ThemeMode, dynamicColor: Boolean) {
        viewModelScope.launch {
            repository.updateTheme(mode, dynamicColor)
        }
    }
}
