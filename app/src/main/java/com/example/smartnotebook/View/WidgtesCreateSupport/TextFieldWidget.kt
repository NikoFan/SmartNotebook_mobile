package com.example.smartnotebook.View.WidgtesCreateSupport

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartnotebook.R

object TextFieldWidget {

    @Composable
    fun Create_UserMainInputField(
        hintParam: String,
        valueParam: String,
        labelParam: String,
        placeholderParam: String,
        onValueChangeParam: (String) -> Unit
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            Column(
            ) {
                // Текстовая подсказка для вводимого значения
                Text(
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            bottom = 10.dp),
                    text = hintParam,
                    fontSize = integerResource(R.integer.textfield_hint_font_size).sp,
                    color = Color.White
                )

                // Редактируемое поле
                TextField(
                    shape = RoundedCornerShape(5.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,

                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,

                        focusedPlaceholderColor = Color.White,

                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            shape = RoundedCornerShape(5.dp),
                            color = Color.White
                        )
                        .fillMaxWidth(0.9f),
                    value = valueParam,
                    placeholder = { Text(text = placeholderParam) },
                    label = { Text(text = labelParam) },

                    onValueChange = onValueChangeParam
                )
            }


        }
    }
}