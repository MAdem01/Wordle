package com.example.wordle.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun WordleScreen(navController: NavController){
    Scaffold() { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
        }
    }
}