package id.ac.pnm.hoventory.ui.Profile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import id.ac.pnm.hoventory.ui.login.LoginScreen

class ProfileActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProfileScreen(navController = rememberNavController())
        }
    }
}