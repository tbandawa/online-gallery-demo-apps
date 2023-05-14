package me.tbandawa.android.gallerydemo.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import me.tbandawa.android.gallerydemo.R

@Composable
fun AuthScreen() {
    Surface(
        modifier = Modifier
            .padding(top = 25.dp, bottom = 25.dp, start = 25.dp, end = 25.dp),
        shape = RoundedCornerShape(5)
    ) {
        Surface {
            var selectedTab by remember { mutableStateOf(Tab.LOGIN) }
            Scaffold(
                topBar = {
                    Column {
                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(start = 15.dp)
                        ) {

                            val (tabs, closeIcon) = createRefs()

                            Row(
                                Modifier
                                    .constrainAs(tabs) {
                                        top.linkTo(parent.top)
                                        start.linkTo(parent.start)
                                    }
                                    .padding(top = 5.dp)
                            ) {
                                Tab.values().forEach { tab ->
                                    AuthTab(
                                        tab.name,
                                        selected = selectedTab == tab,
                                        onSelect = {
                                            selectedTab = tab
                                        },
                                    )
                                }
                            }

                            Image(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = null,
                                modifier = Modifier
                                    .constrainAs(closeIcon) {
                                        top.linkTo(parent.top)
                                        end.linkTo(parent.end)
                                    }
                                    .size(35.dp)
                                    .padding(top = 5.dp, end = 8.dp)
                                    .clickable(
                                        enabled = true,
                                        onClick = {

                                        }
                                    )
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.2.dp)
                                .background(Color(0xff024040))
                        )
                    }

            }) {
                when (selectedTab) {
                    Tab.LOGIN -> LoginComponent()
                    Tab.REGISTER -> RegisterComponent()
                }
            }
        }
    }
}

enum class Tab(value: String) {
    LOGIN("Log in"),
    REGISTER("Register")
}

@Composable
fun AuthTab(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Column(
        modifier = Modifier
            .height(height = 34.dp)
            .width(83.dp)
            .clickable(onClick = onSelect)
    ) {
        Text(
            text,
            textAlign = TextAlign.Center,
            color = if (selected) Color.Black else Color.Gray,
            modifier = Modifier
                .padding(5.dp),
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .background(if (selected) Color(0xff024040) else Color.Transparent)
        )
    }
}

@Preview
@Composable
fun AuthScreenPreview() {
    AuthScreen()
}