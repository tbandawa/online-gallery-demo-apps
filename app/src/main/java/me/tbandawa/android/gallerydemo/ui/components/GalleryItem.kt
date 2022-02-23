package me.tbandawa.android.gallerydemo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun GalleryItem(
    painter: Painter,
    title: String,
    count: Int
) {
    Card(
        modifier = Modifier
            .size(130.dp)
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
                    .constrainAs(galleryImage) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Box(
                modifier = Modifier
                    .constrainAs(infoBox) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth(1f)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0x05000000),
                                Color(0xff000000)
                            )
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Text(
                        text = title,

                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "$count",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp
                        )
                    )
                }
            }

        }

    }
}