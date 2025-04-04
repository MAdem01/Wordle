package com.example.wordle.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun WordleNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WordleScreens.WordleScreen.name){
        composable(
            WordleScreen(navController = navController)
        )
    }
}