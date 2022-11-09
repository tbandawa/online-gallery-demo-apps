package me.tbandawa.android.gallerydemo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
fun GalleryItem(
    painter: Painter,
    title: String,
    description: String,
    user: String,
    date: String,
    count: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(5.dp),
        shape = RoundedCornerShape(3.dp),
        elevation = 1.dp,
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 5.dp)
                    )
                    Row(modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth()
                    ) {
                        Spacer(
                            modifier = Modifier
                                .width(3.dp)
                                .fillMaxHeight(1f)
                                .background(Color(0xff024040))
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 5.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = description,
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp
                                )
                            )
                            Row(
                                modifier = Modifier
                                    .padding(top = 5.dp, bottom = 5.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.ic_avatar),
                                    contentDescription = "avatar",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape)
                                        .border(0.dp, Color.Gray, CircleShape)
                                )
                                Text(
                                    text = user,
                                    style = TextStyle(
                                        color = Color(0xff024040),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 10.sp
                                    ),
                                    modifier = Modifier.padding(start = 5.dp)
                                )
                                Text(
                                    text = "·",
                                    style = TextStyle(
                                        color = Color(0xff024040),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 10.sp
                                    ),
                                    modifier = Modifier.padding(start = 3.dp, end = 3.dp)
                                )
                                Text(
                                    text = date,
                                    style = TextStyle(
                                        color = Color(0xff024040),
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 10.sp
                                    )
                                )
                            }
                        }
                        
                    }
                }
            }

        }

    }
}

@Preview
@Composable
fun GalleryItemPreview() {
    GalleryItem(
        painter =  painterResource(id = R.drawable.free_1),
        title = "Cyberpunk is Not Dead",
        description = "Cyber-chic is trending, and we’re plugged in to the possibilities. Add some futuristic flair and dystopian vibes to your upcoming projects with this sci-fi inspired collection.",
        user = "Tendai Bandawa",
        date = "5 Nov 2022",
        count = 5
    )
}