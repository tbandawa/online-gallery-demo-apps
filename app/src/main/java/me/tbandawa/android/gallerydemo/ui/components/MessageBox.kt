package me.tbandawa.android.gallerydemo.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.gallerydemo.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MessageBox(title: String, message: String) {

    var visible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = visible,
        exit = shrinkVertically(animationSpec = tween(500))
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 5.dp, top = 32.dp, end = 5.dp, bottom = 5.dp),
            shape = RoundedCornerShape(3.dp),
            elevation = 1.dp,
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(Color(0xffF0F5F1))
                    .padding(start = 5.dp)
            ) {

                val (titleText, messageText, closeIcon) = createRefs()

                Text(
                    text = title,
                    style = TextStyle(
                        color = Color(0xff024040),
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
                        .padding(top = 5.dp, end = 5.dp)
                        .clickable(
                            enabled = true,
                            onClick = {
                                visible = !visible
                            }
                        )
                )

                Text(
                    text = message,
                    style = TextStyle(
                        color = Color(0xff6A7777),
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