package com.example.wordle.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wordle.model.GameState
import com.example.wordle.model.Letter
import com.example.wordle.model.WordViewModel
import com.example.wordle.utils.checkForResult
import com.example.wordle.utils.evaluateLetterColors
import com.example.wordle.widgets.GuessRow
import com.example.wordle.widgets.KeyBoard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WordleScreen(navController: NavController,
                 wordViewModel: WordViewModel = viewModel()
) {
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
        MainContent(paddingValues = paddingValues,
            wordViewModel = wordViewModel
            )
    }
}

@Composable
fun MainContent(
    paddingValues: PaddingValues,
    wordViewModel: WordViewModel
) {
    val targetWord by wordViewModel.word.collectAsState()
    val currentGuess = remember { mutableStateListOf<Letter>()}
    val guessList = remember { mutableStateListOf<List<Letter>>() }
    val gameState = remember { mutableStateOf(GameState.ON_GOING) }
    val guessedLetters = remember { mutableStateMapOf<Char, Color>().withDefault { Color(0xFF787E82) } }


    Surface(modifier = Modifier.padding(paddingValues)) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GuessRow(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), letters = currentGuess)

            when (gameState.value) {
                GameState.ON_GOING -> {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(items = guessList) { guess ->
                            GuessRow(letters = guess)
                        }
                    }
                }
                GameState.WON -> {
                    Result(gameState = gameState, guessList = guessList, guessedLetters = guessedLetters, message = "You Won"){
                        wordViewModel.fetchRandomWord()
                    }

                }
                else -> {
                    Result(gameState = gameState, guessList = guessList, guessedLetters = guessedLetters, message = "You Lost"){
                        wordViewModel.fetchRandomWord()
                    }
                }
            }

            if(gameState.value == GameState.ON_GOING) {
                KeyBoard(guessedLetters) { letter ->
                    if(letter == '-' && currentGuess.size > 0){
                        currentGuess.removeAt(currentGuess.size - 1)
                    }else if (currentGuess.size < 4) {
                        currentGuess.add(Letter(char = letter.toString(), color = Color(0XFF787e82)))
                    } else {
                        currentGuess.add(Letter(char = letter.toString(), color = Color(0XFF787e82)))
                        guessList.add(0, evaluateLetterColors(currentGuess, guessedLetters, targetWord))
                        currentGuess.clear()
                        checkForResult(guessList) { updatedGameState ->
                            gameState.value = updatedGameState
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Result(gameState: MutableState<GameState>, guessList: MutableList<List<Letter>>, guessedLetters: MutableMap<Char, Color>, message: String, updateWord: () -> Unit){
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally){
        guessList.map{ guess ->
            GuessRow(
                letters = guess){
            }
        }
        Text(text = message)
        ResetButton(gameState = gameState, guessList = guessList, guessedLetters = guessedLetters){
            updateWord()
        }
    }
}

@Composable
fun ResetButton(gameState: MutableState<GameState>, guessList: MutableList<List<Letter>>, guessedLetters: MutableMap<Char, Color>, updateWord: () -> Unit){
    Button(onClick = {
        guessList.clear()
        guessedLetters.clear()
        gameState.value = GameState.ON_GOING
        updateWord()
    }){
        Text(text = "Try Again")
    }
}
