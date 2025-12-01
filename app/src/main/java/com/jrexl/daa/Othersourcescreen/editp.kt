package com.jrexl.daa.Othersourcescreen

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jrexl.daa.Homepage


@Composable
fun editp(label: String, txtfield: String, navController: NavController){
    var txtfield2 by remember { mutableStateOf(txtfield) }

    Column(Modifier.fillMaxWidth().fillMaxHeight().background(Brush.linearGradient(colors =
        listOf(
            Color(0xFF86CFC2),
            Color(0xFFFBF7E7),
            Color(0xFFC1DFE3),
            Color(0xFFECF8F9),
            Color(0xFFFAFCFD)
        ))))
    {
        Spacer(Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth().background(Color.Magenta).statusBarsPadding().padding(start = 20.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text("Edit profile", fontSize = 20.sp)
        }
        Spacer(Modifier.height(10.dp))

        androidx.compose.material3.Text(
            text = label, fontWeight = FontWeight.Medium, fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 18.dp),
        )
        Spacer(modifier = Modifier.height(4.dp))
        androidx.compose.material3.OutlinedTextField(
            value = txtfield2?: "",
            onValueChange = { txtfield2 = it},
            placeholder = {
                Text(
                    text = "Enter $label",
                    fontSize = 11.sp,
                )
            },
            modifier = Modifier
                .fillMaxWidth().padding(start = 16.dp, end = 16.dp)
                .height(48.dp),

            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                disabledContainerColor = Color.White
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            navController.popBackStack()


        },
            Modifier.fillMaxWidth().height(40.dp).clip(RoundedCornerShape(20.dp)).padding(end = 10.dp, start = 10.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color.Blue.copy(0.5f),
                containerColor = Color.Red.copy(0.6f))
        ) {
            Text("Submit")
        }

    }
}