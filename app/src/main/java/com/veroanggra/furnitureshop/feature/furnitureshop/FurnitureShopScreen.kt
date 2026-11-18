package com.veroanggra.furnitureshop.feature.furnitureshop

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.veroanggra.furnitureshop.ui.molecules.CategorySelector
import com.veroanggra.furnitureshop.ui.organisms.ProductRow
import com.veroanggra.furnitureshop.ui.templates.FurnitureShopTemplate
import com.veroanggra.reactify.gooey.GooeyRenderers
import com.veroanggra.reactify.list.rememberGooeyTransferState

/** Stateful entry point: wires the [FurnitureShopViewModel] to the stateless [FurnitureShopScreen]. */
@Composable
fun FurnitureShopRoute(
    modifier: Modifier = Modifier,
    viewModel: FurnitureShopViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FurnitureShopScreen(
        uiState = uiState,
        onCategorySelected = viewModel::onCategorySelected,
        modifier = modifier,
    )
}

/** Page: binds [FurnitureShopUiState] into the [FurnitureShopTemplate] layout. */
@Composable
fun FurnitureShopScreen(
    uiState: FurnitureShopUiState,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val renderer = remember { GooeyRenderers.auto() }
    val transferState = rememberGooeyTransferState()

    FurnitureShopTemplate(
        transferState = transferState,
        modifier = modifier,
        categorySelector = {
            CategorySelector(
                categories = uiState.categories,
                selectedCategory = uiState.selectedCategory,
                onCategorySelected = onCategorySelected,
                renderer = renderer,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        productRow = {
            ProductRow(
                products = uiState.products,
                isLoading = uiState.isLoading,
                transferState = transferState,
                modifier = Modifier.fillMaxWidth(),
            )
        },
    )
}
