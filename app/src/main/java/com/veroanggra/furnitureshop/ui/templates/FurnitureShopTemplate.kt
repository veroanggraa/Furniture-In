package com.veroanggra.furnitureshop.ui.templates

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.veroanggra.furnitureshop.ui.molecules.ScreenHeader
import com.veroanggra.furnitureshop.ui.molecules.SearchBar
import com.veroanggra.furnitureshop.ui.molecules.SectionHeader
import com.veroanggra.reactify.list.GooeyTransferHost
import com.veroanggra.reactify.list.GooeyTransferState

/** Page-level layout skeleton: arranges the header, search bar and content slots. */
@Composable
fun FurnitureShopTemplate(
    transferState: GooeyTransferState,
    categorySelector: @Composable () -> Unit,
    productRow: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    GooeyTransferHost(state = transferState, modifier = modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            Spacer(Modifier.height(24.dp))
            ScreenHeader(transferState = transferState)
            Spacer(Modifier.height(20.dp))
            SearchBar()
            Spacer(Modifier.height(20.dp))
            categorySelector()
            Spacer(Modifier.height(20.dp))
            SectionHeader("Chair Collections")
            Spacer(Modifier.height(14.dp))
            productRow()
        }
    }
}
