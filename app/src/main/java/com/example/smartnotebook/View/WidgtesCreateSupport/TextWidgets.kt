package com.example.smartnotebook.View.WidgtesCreateSupport

import android.icu.util.Calendar
import android.view.Surface
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartnotebook.R
import java.text.SimpleDateFormat
import java.util.Locale

object TextWidgets {

    private val TITLE_FONT_SIZE = R.integer.title_font_size
    private val BIG_FONT_SIZE = R.integer.big_font_size

    private val days_translate: Map<String, String> = mapOf(
        "Monday" to "Понедельник",
        "Tuesday" to "Вторник",
        "Wednesday" to "Среда",
        "Thursday" to "Четверг",
        "Friday" to "Пятница",
        "Sunday" to "Суббота",
        "Saturday" to "Воскресенье"
    )
    @Composable
    fun Create_WindowTitleTextWidget(
        widget_text: String?
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(1f)
        ) {
            println("РАЗМЕР ТЕКСТА: $TITLE_FONT_SIZE")
            Text(
                text = widget_text.toString(),
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