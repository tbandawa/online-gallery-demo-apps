package me.tbandawa.android.online.gallery.demo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import me.tbandawa.android.online.gallery.data.domain.models.Gallery

@Composable
fun Galleries(
    galleries: List<Gallery>
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
    ) {
        items(galleries) { gallery ->
            Column {
                Text(
                    text = gallery.title,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                )
                LazyRow {
                    items(gallery.images) { image ->
                        val imageUrl = rememberImagePainter(
                            data = image.thumbnail,
                            builder = {
                                crossfade(true)
                                size(OriginalSize)
                            }
                        )
                        Image(
                            painter = imageUrl,
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(top = 5.dp, end = 10.dp, bottom = 20.dp)
                                .height(145.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .align(alignment = Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}