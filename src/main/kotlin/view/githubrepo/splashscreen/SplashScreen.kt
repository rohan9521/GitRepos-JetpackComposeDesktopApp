package view.githubrepo.splashscreen

import Utils.TITLE
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Preview
@Composable
fun SplashScreen(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()

    ){
        Image(
            painterResource(resourcePath = "githublogo.png"),
            contentDescription = TITLE,
            modifier = Modifier
                .fillMaxHeight(0.6f)
        )
       LinearProgressIndicator()
    }
}
