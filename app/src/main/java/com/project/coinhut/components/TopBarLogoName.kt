package com.project.coinhut.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter
import com.project.coinhut.R
import com.project.coinhut.ui.theme.Typography
import com.project.coinhut.utils.Token


@Composable
fun TopBarLogoName(
    token: Token,
    onIconButtonClick: () -> Unit = {}
) {
    TopAppBar(
        backgroundColor = Color.Blue
    ) {
        IconButton(onClick = { onIconButtonClick() }) {
            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.White
            )
        }

        Image(
            painter = rememberImagePainter(data = token.image.replace("/large/", "/small/")),
            contentDescription = null,
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.small_spacing),
                end = dimensionResource(id = R.dimen.small_spacing)
            )
        )

        Text(
            text = token.name + " " + "(" + token.symbol.uppercase() + ")",
            style = Typography.h6,
            color = Color.White
        )
    }
}