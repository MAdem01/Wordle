package com.example.wordle.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle.model.Letter


@Composable
fun GuessRow(letters: List<Letter>){
    Row {
        letters.forEach {letter ->
            Card(modifier = Modifier
                .padding(5.dp)
                .width(70.dp)
                .height(70.dp),
                colors = CardDefaults.cardColors(containerColor = letter.color)
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
    }
}