package me.tbandawa.android.gallerydemo.ui.components

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.tbandawa.android.gallerydemo.R

@Composable
fun GalleryToolBar(
    title: String,
    navigateToUpload: () -> Unit
) {

    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = Color(0xff024040),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            )
        },
        backgroundColor = Color.White,
        elevation = 0.dp,
        actions = {
            IconButton(
                onClick = {
                    navigateToUpload.invoke()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_upload),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp),
                    tint = Color(0xff024040)
                )
            }
        }
    )

}