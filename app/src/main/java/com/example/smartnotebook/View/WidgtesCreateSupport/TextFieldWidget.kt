package com.example.smartnotebook.View.WidgtesCreateSupport

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import com.example.smartnotebook.R

object TextFieldWidget {

    @Composable
    fun Create_UserMainInputField(
        hint_param: String,
        value_param: String,
        label_param: String,
        placeholder_param: String,
        onValueChange_param: (String) -> Unit
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
                    text = hint_param,
                    fontSize = integerResource(R.integer.textfield_hint_font_size).sp,
                    color = Color.White
                )

                // Редактируемое поле
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    value = value_param,
                    placeholder = { Text(text = placeholder_param) },
                    label = { Text(text = label_param) },

                    onValueChange = onValueChange_param
                )
            }


        }
    }
}