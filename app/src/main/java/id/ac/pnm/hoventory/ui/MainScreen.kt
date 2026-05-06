package id.ac.pnm.hoventory.ui

import android.R.id.title
import android.R.string.no
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.ac.pnm.hoventory.ui.Home.HomeScreen
import id.ac.pnm.hoventory.ui.theme.NavyBlue
import kotlinx.coroutines.channels.ticker
import java.nio.file.WatchEvent
import androidx.compose.runtime.getValue
import id.ac.pnm.hoventory.ui.Profile.ProfileScreen

@Composable
fun MainScreen(){

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val noBottomBarRoutes = listOf("login")

    Scaffold (
        bottomBar = {
            if (currentRoute !in noBottomBarRoutes){
                RealBottomNavigationBar(
                    currentRoute = currentRoute,
                    onNavigate = { route->
                        navController.navigate(route){
                            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ){
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "beranda",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("beranda") { HomeScreen(navController) }
            composable("produk") { DummyScreen("Halaman Produk") }
            composable("riwayat") { DummyScreen("Halaman Riwayat") }
            composable("profil") { ProfileScreen(navController) }
        }
    }
}

@Composable
fun DummyScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold)
    }
}

@Composable
fun RealBottomNavigationBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit) {
    NavigationBar (
        containerColor = Color.White,
        tonalElevation = 8.dp
    ){
        NavigationBarItem(
            selected = currentRoute == "beranda",
            onClick = {onNavigate("beranda")},
            icon = { Icon(Icons.Default.Home,
                contentDescription = "Beranda") },
            label = {Text("Beranda",
                fontWeight = if (currentRoute == "beranda")FontWeight.Bold else FontWeight.Normal)},
            colors = NavigationBarItemDefaults.colors(selectedIconColor = NavyBlue,
                selectedTextColor = NavyBlue,
                indicatorColor = Color.Transparent)
        )

        NavigationBarItem(
            selected = currentRoute == "produk",
            onClick = {onNavigate("produk")},
            icon = { Icon(Icons.Default.Home,
                contentDescription = "Produk") },
            label = {Text("Produk",
                fontWeight = if (currentRoute == "produk")FontWeight.Bold else FontWeight.Normal)},
            colors = NavigationBarItemDefaults.colors(selectedIconColor = NavyBlue,
                selectedTextColor = NavyBlue,
                indicatorColor = Color.Transparent)
        )

        NavigationBarItem(
            selected = currentRoute == "riwayat",
            onClick = {onNavigate("riwayat")},
            icon = { Icon(Icons.Default.Home,
                contentDescription = "Riwayat") },
            label = {Text("Riwayat",
                fontWeight = if (currentRoute == "riwayat")FontWeight.Bold else FontWeight.Normal)},
            colors = NavigationBarItemDefaults.colors(selectedIconColor = NavyBlue,
                selectedTextColor = NavyBlue,
                indicatorColor = Color.Transparent)
        )

        NavigationBarItem(
            selected = currentRoute == "profil",
            onClick = {onNavigate("profil")},
            icon = { Icon(Icons.Default.Home,
                contentDescription = "Profil") },
            label = {Text("Profil",
                fontWeight = if (currentRoute == "profil")FontWeight.Bold else FontWeight.Normal)},
            colors = NavigationBarItemDefaults.colors(selectedIconColor = NavyBlue,
                selectedTextColor = NavyBlue,
                indicatorColor = Color.Transparent)
        )

    }
}
