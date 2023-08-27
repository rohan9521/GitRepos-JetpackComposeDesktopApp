package components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextView(fieldName:String,value:String){

    Surface(color = Color.LightGray , shape = RoundedCornerShape(20.dp)) {

        Row(
            modifier = Modifier
                .border(BorderStroke(2.dp, androidx.compose.ui.graphics.Color.Black), shape = RoundedCornerShape(20.dp))
                .padding(10.dp)
        ) {
            Text(fieldName)
            Text(value)

        }

    }

}