package view.githubrepo.repoview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

        Row {

            Box(
                modifier = Modifier.size(100.dp).clip( RoundedCornerShape(10.dp)).background(Color.Red)
            )
            Column {
                repository.name?.let { CustomTextView("Name :", it   )}
                repository.description?.let { CustomTextView("Description : ", it) }
            }

        }
        Divider(color = Color.Black, thickness = 2.dp)
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        Row {
            CustomTextView("ForkCount : ",repository.forkCount.toString())
            CustomTextView("IsFork : ",repository.isFork.toString())
            CustomTextView("Issues Count : ", repository.issues?.totalCount.toString())
        }

    }
    Spacer(
        modifier = Modifier.height(10.dp)
    )
}