package me.tbandawa.android.gallerydemo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.gallerydemo.R

@Composable
fun NotificationItem(
    painter: Painter,
    message: String,
    user: String,
    date: String,
    isRead: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(color = if (isRead)
                Color.Transparent
            else
                Color(0x10024040)
            )
            .clickable {

            }
    ) {

        Row(modifier = Modifier
            .padding(start = 16.dp, top = 8.dp)
            .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.ic_avatar),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .border(0.dp, Color.Gray, CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "$user $message",
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                )
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = date,
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp
                        )
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xff024040))
                )
            }

        }

    }
}

@Preview
@Composable
fun NotificationItemPreview() {
    NotificationItem(
        painter =  painterResource(id = R.drawable.ic_avatar),
        message = "created a new gallery.",
        user = "Tendai Bandawa",
        date = "5 Nov 2022",
        isRead = false
    )
}