package com.example.wordle.utils

import androidx.compose.ui.graphics.Color
import com.example.wordle.model.GameState
import com.example.wordle.model.Letter

fun evaluateLetterColors(letters: List<Letter>, letterColors: MutableMap<Char, Color>, targetWord: String): List<Letter>{
    val guess = letters.map{ letter ->
        letter.char[0].lowercaseChar()
    }.toCharArray()

    val targetLetterCount = mutableMapOf<Char, Int>()
    targetWord.forEach { c -> targetLetterCount[c] = (targetLetterCount[c] ?: 0) + 1 }

    val result = MutableList(5) { index ->
        Letter(char = guess[index].toString(), color = Color(0xFF787E82))
    }

    for (i in guess.indices) {
        if (guess[i] == targetWord[i]) {
            result[i] = Letter(char = guess[i].toString(), color = Color(0XFF67ac65))
            targetLetterCount[guess[i]] = targetLetterCount[guess[i]]!! - 1
        }
    }

    for (i in guess.indices) {
        if (result[i].color == Color(0xFF787E82) && targetWord.contains(guess[i])) {
            val count = targetLetterCount[guess[i]] ?: 0
            if (count > 0) {
                result[i] = Letter(char = guess[i].toString(), color = Color(0XFFc8b555))
                targetLetterCount[guess[i]] = count - 1
            }
        }
    }

    result.forEach{letter ->
        letterColors[letter.char[0]] = letter.color
    }
    return result
}

fun checkForResult(guessList: MutableList<List<Letter>>, updateGameState: (GameState) -> Unit){
    if(guessList[0].all { letter ->
            letter.color == Color(0XFF67ac65)
        }){
        updateGameState(GameState.WON)
    }else if(guessList.size == 6){
        updateGameState(GameState.LOST)
    }else{
        updateGameState(GameState.ON_GOING)
    }
}