package com.example.fetchtakehometest.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchtakehometest.data.Item
import com.example.fetchtakehometest.data.network.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ItemUiState(
    val items: Map<Int, List<Item>> = emptyMap(),
    val loading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<ItemUiState> = MutableStateFlow(ItemUiState())
    val uiState: StateFlow<ItemUiState> = _uiState.asStateFlow()

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, error = null)
            try {
                val items: Map<Int, List<Item>> = itemRepository.getItems()
                    .filter { !it.name.isNullOrBlank() }
                    .sortedWith(compareBy({it.listId}, {it.name}))
                    .groupBy { it.listId }
                _uiState.value = _uiState.value.copy(
                    items = items,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    error = e.message
                )
            }
        }
    }
}
