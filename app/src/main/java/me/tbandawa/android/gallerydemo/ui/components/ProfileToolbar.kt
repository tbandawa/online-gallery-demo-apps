package me.tbandawa.android.gallerydemo.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import me.tbandawa.android.gallerydemo.R

@Composable
fun ProfileToolbar(
    title: String,
    navController: NavController
) {

    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier= Modifier.fillMaxWidth()) {

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

                val (navigationIcon, titleText, settingsIcon) = createRefs()

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
                        modifier = Modifier
                            .size(20.dp)
                    )
                }

                //Title
                Text(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .constrainAs(titleText) {
                            start.linkTo(navigationIcon.end)
                            end.linkTo(settingsIcon.start)
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

                //Settings Icon
                IconButton(
                    onClick = {
                        navController.navigate(Screen.Settings.route)
                    },
                    enabled = true,
                    modifier = Modifier
                        .constrainAs(settingsIcon) {
                            end.linkTo(parent.end)
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_settings),
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }

            }
        }
    }

}

@Preview
@Composable
fun ProfileToolbarPreview() {
    ProfileToolbar(title = "Profile", navController = rememberNavController())
}