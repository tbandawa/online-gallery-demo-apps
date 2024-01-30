package me.tbandawa.android.online.gallery.demo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import me.tbandawa.android.online.gallery.R
import me.tbandawa.android.online.gallery.data.domain.models.Gallery

@Composable
fun ProfileGalleries(
    galleries: List<Gallery>,
    navigateToGallery: (Long) -> Unit,
    deleteGallery: (Long) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp)
    ) {
        items(galleries) { gallery ->
            Column(
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        navigateToGallery(gallery.id)
                    }
            ) {
                Row {
                    Text(
                        text = gallery.title,
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    )
                    Spacer(Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                            .padding(top = 5.dp)
                            .clickable(
                                enabled = true,
                                onClick = {
                                    deleteGallery(gallery.id)
                                }
                            )
                    )
                }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
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