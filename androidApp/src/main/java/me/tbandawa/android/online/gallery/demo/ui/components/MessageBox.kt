package me.tbandawa.android.online.gallery.demo.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.online.gallery.R

@Composable
fun MessageBox(
    type: MessageType,
    title: String,
    message: String,
    visibility: Boolean,
    dismiss: () -> Unit
) {

    val backgroundColor = when (type) {
        MessageType.SUCCESS -> Color(0xffF0F5F1)
        MessageType.ERROR -> Color(0xffffacac)
        MessageType.INFO -> Color(0xff9fc5e8)
    }
    val titleColor = when (type) {
        MessageType.SUCCESS -> Color(0xff024040)
        MessageType.ERROR -> Color(0xfff44336)
        MessageType.INFO -> Color(0xff2986cc)
    }
    val messageColor = when (type) {
        MessageType.SUCCESS -> Color(0xff6A7777)
        MessageType.ERROR -> Color(0xfff44336)
        MessageType.INFO -> Color(0xff3d85c6)
    }

    var visible by remember { mutableStateOf(visibility) }

    AnimatedVisibility(
        visible = visibility,
        exit = shrinkVertically(animationSpec = tween(500))
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 5.dp, top = 16.dp, end = 5.dp, bottom = 16.dp),
            shape = RoundedCornerShape(3.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp
            )
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(backgroundColor)
                    .padding(start = 5.dp, end = 5.dp)
            ) {

                val (titleText, messageText, closeIcon) = createRefs()

                Text(
                    text = title,
                    style = TextStyle(
                        color = titleColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier
                        .constrainAs(titleText) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }
                        .padding(start = 0.dp, top = 10.dp, bottom = 5.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(closeIcon) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                        .size(25.dp)
                        .padding(top = 5.dp)
                        .clickable(
                            enabled = true,
                            onClick = {
                                dismiss.invoke()
                            }
                        )
                )

                Text(
                    text = message,
                    style = TextStyle(
                        color = messageColor,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .constrainAs(messageText) {
                            start.linkTo(parent.start)
                            top.linkTo(titleText.bottom)
                        }
                        .padding(start = 0.dp, bottom = 10.dp)
                )

            }

        }

    }
}

enum class MessageType {
    SUCCESS, ERROR, INFO
}

@Preview
@Composable
fun MessageBoxPreview() {
    MessageBox(
        type = MessageType.INFO,
        title = "Login Error",
        message = "Upload images to gallery and let others view them",
        true
    ) {}
}