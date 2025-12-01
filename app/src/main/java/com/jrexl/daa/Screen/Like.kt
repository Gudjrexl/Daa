package com.jrexl.daa.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Like() {
    Column(Modifier.fillMaxWidth().fillMaxHeight().background(Brush.linearGradient(colors =
        listOf(
            Color(0xFF86CFC2),
            Color(0xFFFBF7E7),
            Color(0xFFC1DFE3),
            Color(0xFFECF8F9),
            Color(0xFFFAFCFD)
        ))).navigationBarsPadding())
    {
        Row(modifier = Modifier.statusBarsPadding().padding(start = 20.dp)){
        }}
}