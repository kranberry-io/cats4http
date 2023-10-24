package com.example.cats4http.android

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Relation
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReloadableCatImageScreen()
                }
            }
        }
    }
}

@Composable
fun ReloadableCatImageScreen() {
    ConstraintLayout (modifier = Modifier.padding(all = Dp(16f))) {
        val (image, button) = createRefs()
        var currentUrl by remember { mutableStateOf("https://http.cat/images/100.jpg") }

        Image(
            painter = rememberAsyncImagePainter(currentUrl),
            contentDescription = "Image",
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Button(
            onClick = { currentUrl = "https://http.cat/images/400.jpg" },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(image.bottom, margin = Dp(16f))
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text("Reload")
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        ReloadableCatImageScreen()
    }
}
