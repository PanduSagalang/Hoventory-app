package id.ac.pnm.hoventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import id.ac.pnm.hoventory.ui.MainScreen
import id.ac.pnm.hoventory.ui.theme.HoventoryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HoventoryTheme {
                MainScreen()
                }
            }
        }
    }