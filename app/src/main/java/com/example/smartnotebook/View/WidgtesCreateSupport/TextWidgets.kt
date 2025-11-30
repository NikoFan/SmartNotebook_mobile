package com.example.smartnotebook.View.WidgtesCreateSupport

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.smartnotebook.R

object TextWidgets {

    private val TITLE_FONT_SIZE = R.integer.title_font_size
    @Composable
    fun CreateWindowTitleWidget(
        text_message: String?
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {
            println("РАЗМЕР ТЕКСТА: $TITLE_FONT_SIZE")
            Text(
                text = text_message.toString(),
                fontSize = integerResource(TITLE_FONT_SIZE).sp,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.white)
            )
        }
    }
}