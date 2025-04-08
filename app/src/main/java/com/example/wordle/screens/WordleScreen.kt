package com.example.wordle.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wordle.components.InputField
import com.example.wordle.model.GameState
import com.example.wordle.model.Letter
import com.example.wordle.widgets.GuessRow

val randomWord = "rando".toCharArray()


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordleScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
                title = {
                    Text(
                        text = "Wordle",
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        fontSize = 35.sp
                    )
                }
            )
        }
    ) { paddingValues ->
        MainContent(paddingValues = paddingValues)
    }
}

@Composable
fun MainContent(
    paddingValues: PaddingValues,
    onValChange: (String) -> Unit = {}
) {
    val guessState = remember { mutableStateOf("") }
    val validState = remember(guessState.value) { guessState.value.trim().isNotEmpty() }
    val guessList = remember { mutableStateListOf<List<Letter>>() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val gameState = remember { mutableStateOf(GameState.ON_GOING) }

    Surface(modifier = Modifier.padding(paddingValues)) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            when (gameState.value) {
                GameState.ON_GOING -> {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(items = guessList) { guess ->
                            GuessRow(letters = guess)
                        }
                    }
                }
                GameState.WON -> {
                    Column(modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally){
                        guessList.map{ guess ->
                            GuessRow(
                                letters = guess)
                            HorizontalDivider()
                        }
                        Text(text = "You Won")
                        Button(onClick = {
                            guessList.clear()
                            gameState.value = GameState.ON_GOING
                        }){
                            Text(text = "Try Again")
                        }
                    }
                }
                else -> {
                    Column(modifier = Modifier.weight(1f)){
                        guessList.map{ guess ->
                            GuessRow(
                                letters = guess){
                            }
                        }
                        Text(text = "You Lost")
                        Button(onClick = {
                            guessList.clear()
                            gameState.value = GameState.ON_GOING
                        }){
                            Text(text = "Try Again")
                        }
                    }
                }
            }
            InputField(
                modifier = Modifier.fillMaxWidth(),
                valueState = guessState,
                labelId = "Guess",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValChange(guessState.value.trim())
                    if(guessState.value.length == 5 && gameState.value == GameState.ON_GOING)
                        guessList.add(createListOfLetters(guessState.value))
                    checkForResult(guessList){ updatedGameState ->
                        gameState.value = updatedGameState
                        Log.d("xd", "$updatedGameState $gameState")
                    }
                    keyboardController?.hide()
                }
            )
        }
    }
}

fun createListOfLetters(word: String): List<Letter>{
    val target = randomWord.copyOf()
    val guess = word.toCharArray()

    val targetLetterCount = mutableMapOf<Char, Int>()
    target.forEach { c -> targetLetterCount[c] = (targetLetterCount[c] ?: 0) + 1 }

    val result = MutableList(5) { Letter(char = guess[it].toString(), color = Color(0XFF787e82))}

    for (i in guess.indices) {
        if (guess[i] == target[i]) {
            result[i] = Letter(char = guess[i].toString(), color = Color(0XFF67ac65))
            targetLetterCount[guess[i]] = targetLetterCount[guess[i]]!! - 1
        }
    }

    for (i in guess.indices) {
        if (result[i].color == Color.Red && target.contains(guess[i])) {
            val count = targetLetterCount[guess[i]] ?: 0
            if (count > 0) {
                result[i] = Letter(char = guess[i].toString(), color = Color(0XFFc8b555))
                targetLetterCount[guess[i]] = count - 1
            }
        }
    }

    return result
}

fun checkForResult(guessList: MutableList<List<Letter>>, updateGameState: (GameState) -> Unit){
    if(guessList[guessList.size - 1].all { letter ->
        letter.color == Color(0XFF67ac65)
        }){
        updateGameState(GameState.WON)
    }else if(guessList.size == 6){
        updateGameState(GameState.LOST)
    }else{
        updateGameState(GameState.ON_GOING)
    }
}