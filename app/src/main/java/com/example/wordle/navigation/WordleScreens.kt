package com.example.wordle.navigation

enum class WordleScreens{
    WordleScreen;
    companion object{
        fun fromRoute(route: String?): WordleScreens
            = when(route?.substringBefore("/")){
                WordleScreen.name -> WordleScreen
                null -> WordleScreen
                else -> throw IllegalArgumentException("Route $route is not recognized ")
            }
    }
}