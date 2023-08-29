package view.githubrepo.repoview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import components.CustomTextView
import model.Repository

@Composable
fun RepoView(repository:Repository){
    Spacer(
        modifier = Modifier.height(10.dp)
    )
    Column(
        modifier = Modifier
            .padding(10.dp)
            .border(BorderStroke(2.dp, Color.Black),shape= RoundedCornerShape(20.dp))
    ){

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(1.0f)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                repository.name?.let { CustomTextView("Name :", it   , backgroundColor = Color.Blue,textColor = Color.White)}
                repository.description?.let { CustomTextView("Description : ", it,backgroundColor = Color.Blue,textColor = Color.White)}
                repository.primaryLanguage?.let { CustomTextView("Primary Language : ", it.name,backgroundColor = Color.Blue,textColor = Color.White)}
            }
            Surface(
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.Green)
            ) {
                CustomTextView("Stars: ", repository.stargazerCount.toString(),Color.Blue,Color.White)
            }
        }
        Divider(
            color = Color.Black,
            thickness = 2.dp,
            modifier = Modifier
                .padding(horizontal = 10.dp)
            )
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        Row {
            CustomTextView("Fork : ",repository.forkCount.toString())
            CustomTextView("Issues  : ", repository.issues?.totalCount.toString())
            CustomTextView("Pull Requests  : ", repository.pullRequests?.totalCount.toString())
            CustomTextView("Watchers : ", repository.watchers?.totalCount.toString())
        }

    }

}