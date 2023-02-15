package com.azimsiddiqui.unicardapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.azimsiddiqui.unicardapp.ui.theme.UnicardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnicardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   Home(modifier = Modifier, Uri.parse("https://drive.google.com/file/d/14IOPW1o6c0RHxRgEjS-zerQzzrhDAJlE/view?usp=sharing"))
                }
            }
        }
    }
}
