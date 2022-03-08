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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.curvedbottomnavigation.ui.theme.CurvedBottomNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            var items = listOf(
                BottomNavItem(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_profile_line),
                    selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_profile),
                ),
                BottomNavItem(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_heart_line),
                    selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_heart),
                ),
                BottomNavItem(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_home_line),
                    selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_home)
                ),
//                BottomNavItem(
//                    icon = ImageVector.vectorResource(id = R.drawable.ic_home_line),
//                    selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_home)
//                ),
            )


            CurvedBottomNavigationTheme {
                val routes = remember {
                    mutableStateListOf("first", "second", "third" ,"forth")
                }
                Scaffold(
                    bottomBar = {
                        CurvedBottomNavigation2(
                            items = items,
                            defaultItemIndex=2,
                            color = MaterialTheme.colors.primary,
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