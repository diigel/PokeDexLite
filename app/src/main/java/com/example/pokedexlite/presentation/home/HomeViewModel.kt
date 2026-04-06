package com.example.pokedexlite.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexlite.domain.model.Pokemon
import com.example.pokedexlite.domain.usecase.pokemon.GetPokemonsUseCase
import com.example.pokedexlite.domain.usecase.pokemon.SearchPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val pokemons: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = "",
    val searchResults: List<Pokemon> = emptyList(),
    val isSearching: Boolean = false,
    val currentPage: Int = 1,
    val canLoadMore: Boolean = true
) {
    /** Which list to show: search results when query is active, otherwise paged list */
    val displayList: List<Pokemon>
        get() = if (searchQuery.isNotBlank()) searchResults else pokemons
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val searchPokemonUseCase: SearchPokemonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val loadedPages = mutableSetOf<Int>()
    private var searchJob: Job? = null

    init {
        loadPage(1)
    }

    // ── Pagination ────────────────────────────────────────────────────────────

    private fun loadPage(page: Int) {
        if (page in loadedPages && page != 1) return      // avoid duplicate loads

        viewModelScope.launch {
            if (page == 1) {
                _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            } else {
                _uiState.update { it.copy(isLoadingMore = true) }
            }

            getPokemonsUseCase(page).collect { fresh ->
                loadedPages.add(page)

                val merged = if (page == 1) {
                    fresh
                } else {
                    // Merge: keep old pages, replace/append this page
                    val existing = _uiState.value.pokemons
                        .filter { it.page != page }
                    existing + fresh
                }

                _uiState.update {
                    it.copy(
                        pokemons = merged,
                        isLoading = false,
                        isLoadingMore = false,
                        currentPage = page,
                        canLoadMore = fresh.size >= 10
                    )
                }
            }
        }
    }

    fun loadNextPage() {
        val state = _uiState.value
        if (!state.isLoading && !state.isLoadingMore && state.canLoadMore
            && state.searchQuery.isBlank()
        ) {
            loadPage(state.currentPage + 1)
        }
    }

    // ── Search ────────────────────────────────────────────────────────────────

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }

        searchJob?.cancel()

        if (query.isBlank()) {
            _uiState.update { it.copy(searchResults = emptyList(), isSearching = false) }
            return
        }

        _uiState.update { it.copy(isSearching = true) }

        searchJob = viewModelScope.launch {
            searchPokemonUseCase(query).collect { results ->
                _uiState.update { it.copy(searchResults = results, isSearching = false) }
            }
        }
    }

    fun clearSearch() = onSearchQueryChanged("")
}
