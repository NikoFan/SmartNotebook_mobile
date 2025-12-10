package com.example.smartnotebook.View.WidgtesCreateSupport

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartnotebook.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object ErrorsShowsWidgets {

    fun CallSnackBar( // Всплывающее сообщение
        snackbarHostState: SnackbarHostState,
        scope: CoroutineScope
    ) {

        scope.launch {
            snackbarHostState.showSnackbar(
                message = "",
                duration = SnackbarDuration.Short
            )
        }
    }
    @Composable
    fun ErrorSnackBar( // Сообщение об ошибке
        message: String,
        snackbarHostState: SnackbarHostState
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(0.9f)
        ) { data ->
            Snackbar(
                containerColor = colorResource(R.color.red_error_color),
                contentColor = Color.White,
                content = {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = message, // data.visuals.message,
                            fontSize = 20.sp)
                    }
                }
            )
        }
    }

}