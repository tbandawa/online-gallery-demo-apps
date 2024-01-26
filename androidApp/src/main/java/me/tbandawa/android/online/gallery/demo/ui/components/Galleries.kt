package me.tbandawa.android.online.gallery.demo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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

            val profile = gallery.profile!!

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = profile.profilePhoto.thumbnail,
                        contentDescription = "Profile Photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                    )
                    Column {
                        Text(
                            text = "${profile.firstname} ${profile.lastname}",
                            style = TextStyle(
                                color = Color(0xff024040),
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier
                                .padding(start = 5.dp, top = 0.dp)
                        )
                        Text(
                            text = gallery.created,
                            style = TextStyle(
                                color = Color(0xff024040),
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier
                                .padding(start = 5.dp, top = 0.dp)
                        )
                    }
                }
                Text(
                    text = gallery.title,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                )
                LazyRow {
                    items(gallery.images) { image ->
                        AsyncImage(
                            model = image.thumbnail,
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(top = 5.dp, end = 10.dp, bottom = 5.dp)
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

@Preview
@Composable
fun GalleriesPreview() {
    Galleries(
        galleries = listOf()
    )
}