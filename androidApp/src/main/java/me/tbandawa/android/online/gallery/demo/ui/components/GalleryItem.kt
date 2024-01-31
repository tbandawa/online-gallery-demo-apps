package me.tbandawa.android.online.gallery.demo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
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
import me.tbandawa.android.online.gallery.demo.utils.MMM_DD_YYYY
import me.tbandawa.android.online.gallery.demo.utils.YYYY_MM_DD_T
import me.tbandawa.android.online.gallery.demo.utils.convertDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryItem(
    gallery: Gallery,
    navigateToGallery: (galleryId: Long) -> Unit
) {
    val profile = gallery.profile!!

    Box(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 10.dp)
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = {
                navigateToGallery(gallery.id)
            }
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = profile.profilePhoto.thumbnail,
                        placeholder = painterResource(R.drawable.ic_user),
                        error = painterResource(R.drawable.ic_user),
                        contentDescription = "Profile Photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                    )
                    Column {
                        Text(
                            text = gallery.title,
                            style = TextStyle(
                                color = Color(0xff024040),
                                fontWeight = FontWeight.Medium,
                                fontSize = 12.sp
                            ),
                            modifier = Modifier
                                .padding(start = 5.dp, top = 0.dp)
                        )
                        Text(
                            text = "${gallery.images.size} photos added by ${gallery.profile!!.firstname} - ${convertDate(YYYY_MM_DD_T, MMM_DD_YYYY, gallery.created)}",
                            style = TextStyle(
                                color = Color(0xff024040),
                                fontWeight = FontWeight.Normal,
                                fontSize = 10.sp
                            ),
                            modifier = Modifier
                                .padding(start = 5.dp, top = 0.dp)
                        )
                    }
                }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(gallery.images) { image ->
                        AsyncImage(
                            model = image.thumbnail,
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(top = 5.dp)
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