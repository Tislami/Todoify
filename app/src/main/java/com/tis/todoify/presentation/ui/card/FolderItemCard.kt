package com.tis.todoify.presentation.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.tis.todoify.R
import com.tis.todoify.utils.onClick

@Composable
fun FolderItemCard(
    onClick : ()-> Unit,
) {
    Column(
        modifier = Modifier
            .height(200.dp)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.surface.copy(alpha = 0.25f))
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = R.drawable.folder_icon),
            contentDescription = null,
            //tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .size(125.dp)
        )

        Text(text = "Folder")
    }

}