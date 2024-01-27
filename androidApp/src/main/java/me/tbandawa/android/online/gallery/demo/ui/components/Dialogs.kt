package me.tbandawa.android.online.gallery.demo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import me.tbandawa.android.online.gallery.R

@Composable
fun SuccessDialog(
    showDialog: Boolean,
    message: String,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            Box(
                Modifier
                    .pointerInput(Unit) { detectTapGestures { } }
                    .shadow(8.dp, shape = RoundedCornerShape(5.dp))
                    .width(300.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        MaterialTheme.colorScheme.surface,
                    )
                    .padding(PaddingValues(horizontal = 5.dp, vertical = 10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
                        tint = Color(0xff024040),
                        contentDescription = "Success",
                        modifier = Modifier
                            .size(45.dp)
                    )
                    Text(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .padding(bottom = 10.dp),
                        textAlign = TextAlign.Center,
                        text = message,
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontSize = 16.sp
                        )
                    )

                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topStart = 15.dp,
                                    topEnd = 15.dp,
                                    bottomEnd = 15.dp,
                                    bottomStart = 15.dp
                                )
                            )
                            .background(
                                color = Color(0xff024040)
                            )
                            .clickable {
                                onDismiss()
                            }
                            .padding(
                                PaddingValues(
                                    horizontal = 5.dp,
                                    vertical = 2.dp
                                )
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Okay",
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .padding(start = 5.dp, end = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorDialog(
    showDialog: Boolean,
    message: String,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = {
                onDismiss()
            }
        ) {
            Box(
                Modifier
                    .pointerInput(Unit) { detectTapGestures { } }
                    .shadow(8.dp, shape = RoundedCornerShape(5.dp))
                    .width(300.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(
                        MaterialTheme.colorScheme.surface,
                    )
                    .padding(PaddingValues(horizontal = 5.dp, vertical = 10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_error),
                        tint = Color(0xffFF0000),
                        contentDescription = "Success",
                        modifier = Modifier
                            .size(45.dp)
                    )
                    Text(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .padding(bottom = 10.dp),
                        textAlign = TextAlign.Center,
                        text = message,
                        style = TextStyle(
                            color = Color(0xffFF0000),
                            fontSize = 16.sp
                        )
                    )

                    Box(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topStart = 15.dp,
                                    topEnd = 15.dp,
                                    bottomEnd = 15.dp,
                                    bottomStart = 15.dp
                                )
                            )
                            .background(
                                color = Color(0xffFF0000)
                            )
                            .clickable {
                                onDismiss()
                            }
                            .padding(
                                PaddingValues(
                                    horizontal = 5.dp,
                                    vertical = 2.dp
                                )
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Okay",
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .padding(start = 5.dp, end = 5.dp)
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
fun DialogsPreview() {
    ErrorDialog(showDialog = true, message = "Changes Successfully Saved") {}
}