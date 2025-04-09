package com.example.wordle.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle.model.Letter


@Composable
fun GuessRow(modifier: Modifier = Modifier, letters: List<Letter>, content: @Composable () -> Unit = {}){
    Row(modifier = modifier) {
        letters.forEach {letter ->
            Card(modifier = Modifier
                .padding(5.dp)
                .width(65.dp)
                .height(70.dp),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(containerColor = letter.color),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),

            )
            {
                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = letter.char.uppercase(),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        content()
    }
}

@Preview
@Composable
fun KeyBoard(guessedLetter: MutableMap<Char, Color> = mutableMapOf(), updateGuessState: (letter: Char) -> Unit = {}){
    val alphabet = ('a'..'z').toList()
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        alphabet.chunked(10).forEach{ row ->
            Row {
                row.forEach{ letter ->
                    Button(
                        onClick = {
                            updateGuessState(letter)
                        },
                        modifier = Modifier.padding(2.dp).width(35.dp),
                        shape = RoundedCornerShape(3.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = guessedLetter[letter] ?: Color(0xFF787E82)
                        )
                    ) {
                        Text(text = letter.uppercase(),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                    if(letter == 'z'){
                        Button(
                            onClick = {
                                updateGuessState('-')
                            },
                            modifier = Modifier.padding(2.dp).width(50.dp),
                            shape = RoundedCornerShape(3.dp),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = guessedLetter[letter] ?: Color(0xFF787E82)
                            )
                        ) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back Button",
                                modifier = Modifier.size(24.dp),
                                tint = Color.White)
                        }
                    }
                }
            }
        }
    }
}