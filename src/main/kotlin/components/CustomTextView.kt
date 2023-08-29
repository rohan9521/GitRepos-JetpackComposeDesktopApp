package components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextView(
    fieldName: String,
    value: String,
    backgroundColor:Color = Color.LightGray,
    textColor:Color = Color.Black) {

    Surface(
        color = backgroundColor,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(
                vertical = 10.dp,
                horizontal = 5.dp
                )
    ) {

        Row(
            modifier = Modifier
                .border(BorderStroke(2.dp, Color.Black), shape = RoundedCornerShape(20.dp))
                .padding(10.dp)
        ) {
            Text(
                fieldName,modifier = Modifier.wrapContentWidth(align = Alignment.Start),
                color = textColor
            )
            Text(value,color = textColor)

        }

    }

}