package com.example.smartnotebook.View.WidgtesCreateSupport


import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.smartnotebook.R

object ButtonWidgets {

    @Composable
    fun CreateAcceptButton(
        text: String,
        onClick: () -> Unit
    ){
        Button(
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = colorResource(R.color.green_button_color)
            ),
            onClick = onClick,

        ) {
            Text(
                text = text,
                color = Color.White
            )
        }
    }
}