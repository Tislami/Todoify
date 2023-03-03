package com.tis.todoify.presentation.screens.detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tis.todoify.utils.onClick


@Composable
fun DetailAlertDialog(
    onDismissRequest : onClick,
    submitOnclick : onClick,
    cancelOnClick : onClick,
) {
        AlertDialog(
            shape = CircleShape,
            onDismissRequest = onDismissRequest,
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        onClick = submitOnclick
                    ) { Text("Evet ") }
                    Button(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        onClick = cancelOnClick
                    ) { Text("Hayır") }
                }
            },
            text = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Değişklikleri kaydetmek istermisiniz ? ",
                    textAlign = TextAlign.Center
                )
            }
        )
}