package me.tbandawa.android.online.gallery.demo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.tbandawa.android.online.gallery.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior
) {
    MediumTopAppBar(
        title = {
            Text(
                text = title
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White
        ),
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun MainToolbar(
    title: String
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier= Modifier
            .background(color = Color.White)
            .fillMaxWidth(),

    ) {

        //TopAppBar Content
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(32.dp)
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth(1f)
            ) {

                val (_, titleText, _) = createRefs()

                //Title
                androidx.compose.material.Text(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .constrainAs(titleText) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    text = title,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp
                    )
                )

            }
        }
    }
}

@Composable
fun HomeToolBar(
    title: String,
    navController: NavController
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier= Modifier
            .background(color = Color.White)
            .fillMaxWidth(),

        ) {

        //TopAppBar Content
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(32.dp)
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth(1f)
            ) {

                val (_, titleText, searchIcon) = createRefs()

                // Title
                Text(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .constrainAs(titleText) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    text = title,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp
                    )
                )

                // Search Icon
                IconButton(
                    onClick = {
                        navController.navigate("search")
                    },
                    enabled = true,
                    modifier = Modifier
                        .constrainAs(searchIcon) {
                            end.linkTo(parent.end)
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Back",
                        tint = Color(0xff024040),
                        modifier = Modifier
                            .size(25.dp)
                    )
                }

            }
        }
    }
}

@Composable
fun NavigationToolbar(
    title: String,
    navController: NavController
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier= Modifier
            .background(color = Color.White)
            .fillMaxWidth(),

        ) {

        //TopAppBar Content
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(32.dp)
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth(1f)
            ) {

                val (navigationIcon, titleText, _) = createRefs()

                //Navigation Icon
                androidx.compose.material.IconButton(
                    onClick = {
                        navController.navigateUp()
                    },
                    enabled = true,
                    modifier = Modifier
                        .constrainAs(navigationIcon) {
                            start.linkTo(parent.start)
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = Color(0xff024040),
                        modifier = Modifier
                            .size(25.dp)
                    )
                }

                //Title
                androidx.compose.material.Text(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .constrainAs(titleText) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    text = title,
                    style = TextStyle(
                        color = Color(0xff024040),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 22.sp
                    )
                )
            }
        }
    }
}

@Composable
fun GalleryToolbar(
    title: String,
    time: String,
    navController: NavController
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier= Modifier
            .background(color = Color.White)
            .fillMaxWidth(),

        ) {

        //TopAppBar Content
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(40.dp)
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth(1f)
            ) {

                val (navigationIcon, titleText, _) = createRefs()

                //Navigation Icon
                IconButton(
                    onClick = {
                        navController.navigateUp()
                    },
                    enabled = true,
                    modifier = Modifier
                        .constrainAs(navigationIcon) {
                            start.linkTo(parent.start)
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = Color(0xff024040),
                        modifier = Modifier
                            .size(25.dp)
                    )
                }

                //Title
                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .constrainAs(titleText) {
                            start.linkTo(navigationIcon.end)
                        }
                ) {
                    Text(
                        text = title,
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = time,
                        style = TextStyle(
                            color = Color(0xff024040),
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToolBarsPreview() {
    GalleryToolbar(title = "Home Toolbar", time = "25 June, 2024",navController = rememberNavController())
}