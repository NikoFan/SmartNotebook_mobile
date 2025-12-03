package com.example.smartnotebook.View.WidgtesCreateSupport

import android.icu.util.Calendar
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
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

    @Composable
    fun CreateStatisticPanel(
        all_counts: String?,
        streak_count: String?
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                lineHeight = 40.sp,
                text = "Всего:\n${all_counts}",
                textAlign = TextAlign.Center,
                fontSize = integerResource(BIG_FONT_SIZE).sp,
                maxLines = 2,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                lineHeight = 40.sp,
                text = "Подряд:\n${streak_count}",
                textAlign = TextAlign.Center,
                maxLines = 2,
                color = Color.White,
                fontSize = integerResource(BIG_FONT_SIZE).sp
            )
        }
    }


    @Composable
    fun CreateDayInformation(){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val current_day = Calendar.getInstance()
            val date_format = SimpleDateFormat("EEEE", Locale.getDefault())
            val day_of_week = date_format.format(current_day.time)
            val today_date = SimpleDateFormat("d", Locale.getDefault())
                .format(current_day.time)
            Text(
                text =days_translate[day_of_week].toString(),
                fontSize = integerResource(TITLE_FONT_SIZE).sp,
                color = Color.White
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                for (i in 1..5){
                    DayCell(
                        day_number = today_date,
                        cell_color = Color.Green, // if () colorResource(R.color.green_button_color),
                        today_status = false
                    )
                }
            }
        }
    }

    @Composable
    fun DayCell(
        day_number: String,
        cell_color: Color,
        today_status: Boolean
    ) {

    }
}