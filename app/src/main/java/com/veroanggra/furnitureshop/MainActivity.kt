package com.veroanggra.furnitureshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.veroanggra.furnitureshop.ui.furnitureshop.FurnitureShopRoute
import com.veroanggra.furnitureshop.ui.theme.FurnitureShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FurnitureShopTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    FurnitureShopRoute()
                }
            }
        }
    }
}
