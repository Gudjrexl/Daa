package com.jrexl.daa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jrexl.daa.Othersourcescreen.editp
import com.jrexl.daa.Othersourcescreen.profile
import com.jrexl.daa.Othersourcescreen.profileview
import com.jrexl.daa.Screen.Chat
import com.jrexl.daa.Screen.Home
import com.jrexl.daa.Screen.Like
import com.jrexl.daa.Screen.Settings
import com.jrexl.daa.Screen.Videocall

class Homepage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
homepage()        }
    }
}

@Composable
fun homepage() {
    val navController = rememberNavController()
    Scaffold(

        bottomBar = {BottomBar(navController = navController)}) {
            innerPadding ->
        NavHost(navController = navController,startDestination = "Home",  modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())){
            composable("Chat") { Chat() }
            composable("Call") { Videocall() }
            composable("Home") { Home(navController) }
            composable("Like"){ Like() }
            composable("Setting") {Settings() }
            composable ("profile"){ profile((navController)) }
            composable(  route = "editprofile/{label}/{txtfield}",
                arguments = listOf(
                    navArgument("label") { type = NavType.StringType },
                    navArgument("txtfield") { type = NavType.StringType }
                )){backStackEntry ->
                val label = backStackEntry.arguments?.getString("label") ?: ""
                val txtfield = backStackEntry.arguments?.getString("txtfield") ?: ""

                editp(label = label, txtfield = txtfield, navController)            }

            composable(
                route = "profileview/{userId}",
                arguments = listOf(
                    navArgument("userId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getString("userId")
profileview(userId)
            }
        }
    }
}



@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        BottomNavItem("Chat", Icons.Filled.Chat),
        BottomNavItem("Call", Icons.Filled.VideoCall),
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Like", Icons.Default.Favorite),
        BottomNavItem("Setting", Icons.Default.Settings)
    )

    NavigationBar(
        modifier = Modifier.background(
            Brush.horizontalGradient(
                colors = listOf(Color(0xFF6A1B9A), Color(0xFF8E24AA))
            )
        )
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.route) },
                label = { Text(item.route) },
                alwaysShowLabel = true
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val icon: ImageVector

)















@Preview(showBackground = true)
@Composable
fun homePreview() {
    homepage()
}