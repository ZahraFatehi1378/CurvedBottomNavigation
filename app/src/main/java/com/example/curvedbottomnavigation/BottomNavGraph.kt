package com.example.curvedbottomnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun BottomNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = "second"
    ) {
        composable(route = "first") {
            FirstScreen()
        }
        composable(route = "second") {
            SecondScreen()
        }
        composable(route = "third") {
            ThirdScreen()
        }
        composable(route = "forth") {
            ForthScreen()
        }
    }
}


@Composable
fun FirstScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Screen(txt = "first", txtColor = Color(0xFF88b388) , bgColor = Color(0xFFFFFFFF))
    }
}

@Composable
fun SecondScreen() {
    Screen(txt = "second", txtColor = Color(0xFFAD8B73) , bgColor = Color(0xFFFFFFFF))
}

@Composable
fun ThirdScreen() {
    Screen(txt = "third", txtColor = Color(0xFF8892b3) , bgColor = Color(0xFFFFFFFF))

}

@Composable
fun ForthScreen() {
    Screen(txt = "Forth", txtColor = Color(0xFF99A799) , bgColor = Color(0xFFFFFFFF))
}

@Composable
fun Screen(txt: String, txtColor: Color, bgColor: Color) {
    Column(modifier = Modifier.fillMaxSize().background(color = bgColor),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Text(text = txt , fontSize =60.sp , color =txtColor  )
    }
}