package com.example.wordle.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
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