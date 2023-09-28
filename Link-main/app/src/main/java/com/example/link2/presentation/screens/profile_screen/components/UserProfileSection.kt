package com.example.link2.presentation.screens.profile_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.More
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/*
@Composable
fun UserInfoSection() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-16).dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = "Selena, 29",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge, // Use appropriate typography
                fontFamily = FontFamily.SansSerif,
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location Icon",
                    modifier = Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = "New York, US",
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}
*/


@Composable
fun UserInfoSection()
{
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-16).dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Rose, 27",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily.SansSerif,
                    color = MaterialTheme.colorScheme.onSurface
                )

                MoreOptions()

            }

            Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Icon",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(0.dp)
                            .offset(x = -2.5.dp),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "New York, US", color = MaterialTheme.colorScheme.secondary
                    )
                }

            Spacer(modifier = Modifier.height(7.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocalFireDepartment,
                    contentDescription = "Location Icon",
                    tint = Color.Red,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(0.dp)
                        .offset(x = -2.5.dp),
                )
                Text(
                    text = "Positive Member",
                    color = MaterialTheme.colorScheme.secondary,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )
            }



        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreOptions()
{
    var showDialog by remember { mutableStateOf(false) }
    var isBlocked by remember { mutableStateOf(false) }

    val onBlockUserClick: () -> Unit = {
//        isBlocked = true
        showDialog = false
    }

    val onReportUserClick: () -> Unit = {
        // Implement reporting logic here
        showDialog = false
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        Icon(
            imageVector = Icons.Default.MoreHoriz,
            contentDescription = "More options",
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .size(20.dp)
                .clickable {
                    showDialog = true // Set showDialog to true to show the dialog
                }
        )
    }


    // Show the dialog if showDialog is true
    if (showDialog) {
        CustomAlertDialog(
            onDismissRequest = { showDialog = true },
            showBlockButton = !isBlocked,
            onBlockButtonClick = onBlockUserClick,
            onReportButtonClick = onReportUserClick
        )
    }

}


@Composable
fun CustomAlertDialog(
    onDismissRequest: () -> Unit,
    showBlockButton: Boolean,
    onBlockButtonClick: () -> Unit,
    onReportButtonClick: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Column (modifier = Modifier
                .fillMaxWidth() // Make the dialog width full
                .padding(16.dp) ) {

                    if (showBlockButton) {
                        TextButton(onClick = onBlockButtonClick) {
                            Text("Block this user")
                        }
                    }
                    TextButton(onClick = onReportButtonClick) {
                        Text("Report")
                    }
                    TextButton(onClick = onDismissRequest) {
                        Text("Cancel")
                    }

            }
        }
    }
}





@Preview
@Composable
fun Preview()
{
    UserInfoSection()

}