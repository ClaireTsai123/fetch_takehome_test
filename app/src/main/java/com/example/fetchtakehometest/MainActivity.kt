package com.example.fetchtakehometest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.fetchtakehometest.ui.screens.ItemScreen
import com.example.fetchtakehometest.ui.theme.FetchTakeHomeTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchTakeHomeTestTheme {
                ItemScreen()
            }
        }
    }
}

