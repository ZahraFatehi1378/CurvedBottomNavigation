package com.example.curvedbottomnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.curvedbottomnavigation.items.item.CircularItem
import com.example.curvedbottomnavigation.ui.theme.CurvedBottomNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

//            val items = listOf(
//                LinearItem(
//                    icon = ImageVector.vectorResource(id = R.drawable.ic_profile_line),
//                    selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_profile),
//                    text = "profile"
//                ),
//                LinearItem(
//                    icon = ImageVector.vectorResource(id = R.drawable.ic_home_line),
//                    selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_home),
//                    text = "home"
//                ),
//                LinearItem(
//                    icon = ImageVector.vectorResource(id = R.drawable.ic_heart_line),
//                    selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_heart),
//                    text = "favorites"
//                ),
//
//            )


            val items = listOf(
                CircularItem(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_profile),
                    iconSize = 80f,
                    text = "profile"
                ),
                CircularItem(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_home),
                    iconSize = 80f,
                    text = "home",
                ),
//                CircularItem(
//                    icon = ImageVector.vectorResource(id = R.drawable.ic_heart),
//                    text = "favorites"
//                )
            )


            CurvedBottomNavigationTheme {
                val routes = remember {
                    mutableStateListOf("first", "second", "third")
                }
                Scaffold(
                    bottomBar = {
                        CurvedBottomNavigation(
                            items = items,
                            defaultItemIndex = 1,
                            color = MaterialTheme.colors.primary,
                            height = 100.dp,
                            hills = 3,
                            verticalControl = -30f,
                            topPadding = 12.dp ,
                            space = 0.dp
                        ) { index ->
                            navController.navigate(routes[index]) {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        }
                    }
                ) {
                    BottomNavGraph(navHostController = navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CurvedBottomNavigationTheme {
        Greeting("Android")
    }
}