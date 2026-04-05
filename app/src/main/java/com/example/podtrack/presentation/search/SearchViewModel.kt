package com.example.podtrack.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podtrack.domain.model.Podcast
import com.example.podtrack.domain.usecase.SearchPodcastsUseCase
import com.example.podtrack.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchPodcastsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    data class UiState(
        val isLoading: Boolean = false,
        val query: String = "",
        val results: List<Podcast> = emptyList(),
        val error: String? = null
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val searchQuery = MutableStateFlow("")

    init {
        // 🔥 Debounce + distinctUntilChanged для оптимизации поиска
        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .filter { it.length >= 2 } // Минимум 2 символа
                .collect { query -> performSearch(query) }
        }
    }

    fun onQueryChanged(query: String) {
        _uiState.update { it.copy(query = query) }
        searchQuery.value = query
    }

    fun onSearchTriggered() {
        val query = _uiState.value.query
        if (query.length >= 2) searchQuery.value = query
    }

    private suspend fun performSearch(query: String) {
        _uiState.update { it.copy(isLoading = true, error = null) }
        try {
            val results = searchUseCase(query)
            _uiState.update { it.copy(isLoading = false, results = results) }
        } catch (e: Exception) {
            _uiState.update {
                it.copy(isLoading = false, error = "Ошибка: ${e.localizedMessage ?: "Неизвестная"}")
            }
        }
    }

    fun toggleFavorite(podcast: Podcast) {
        viewModelScope.launch {
            toggleFavoriteUseCase(podcast)
            // Обновляем локально в списке для мгновенного фидбека
            val current = _uiState.value.results.toMutableList()
            val idx = current.indexOfFirst { it.id == podcast.id }
            if (idx != -1) {
                current[idx] = podcast.copy(isFavorite = !podcast.isFavorite)
                _uiState.update { it.copy(results = current) }
            }
        }
    }
}