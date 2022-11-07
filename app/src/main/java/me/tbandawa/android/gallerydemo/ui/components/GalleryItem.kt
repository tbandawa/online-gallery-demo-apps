package me.tbandawa.android.gallerydemo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xffF0F5F1))
        ) {

            val (galleryImage, infoBox) = createRefs()

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .constrainAs(galleryImage) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(infoBox) {
                        top.linkTo(galleryImage.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Row(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_avatar),
                        contentDescription = null,
                        modifier = Modifier.size(45.dp),
                        contentScale = ContentScale.Fit
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, top = 5.dp)
                    ) {
                        Box(Modifier.weight(3f)) {
                            Column() {
                                Text(
                                    text = title,
                                    style = TextStyle(
                                        color = Color(0xff024040),
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp
                                    ),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "$count images",
                                    style = TextStyle(
                                        color = Color(0xff024040),
                                        fontWeight = FontWeight.Light,
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }
                        Box(modifier = Modifier) {
                            Text(
                                text = "Tue 5 Nov",
                                style = TextStyle(
                                    color = Color(0xff024040),
                                    fontWeight = FontWeight.Light,
                                    fontSize = 12.sp
                                )
                            )
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
        title = "Sample Gallery",
        count = 5
    )
}