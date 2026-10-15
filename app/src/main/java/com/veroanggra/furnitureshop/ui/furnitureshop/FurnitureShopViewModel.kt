package com.veroanggra.furnitureshop.ui.furnitureshop

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veroanggra.furnitureshop.data.repository.FakeProductRepository
import com.veroanggra.furnitureshop.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FurnitureShopViewModel : ViewModel() {

    private val repository: ProductRepository = FakeProductRepository()

    private val _uiState = MutableStateFlow(FurnitureShopUiState())
    val uiState: StateFlow<FurnitureShopUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val categories = repository.getCategories()
            _uiState.update { it.copy(categories = categories) }
            loadProducts(_uiState.value.selectedCategory)
        }
    }

    fun onCategorySelected(category: String) {
        if (category == _uiState.value.selectedCategory) return
        _uiState.update { it.copy(selectedCategory = category) }
        loadProducts(category)
    }

    fun onNavItemSelected(index: Int) {
        _uiState.update { it.copy(selectedNavIndex = index) }
    }

    private fun loadProducts(category: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val products = repository.getProducts(category)
            _uiState.update { it.copy(isLoading = false, products = products) }
        }
    }
}
