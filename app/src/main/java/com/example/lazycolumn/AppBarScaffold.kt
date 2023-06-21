package com.example.lazycolumn

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun AppBarScaffold(
    @StringRes titleId: Int? = null,
    displayHomeAsUpEnabled: Boolean = false,
    handleOnBackPressed: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit = {}
) {
    val activity: ComponentActivity? = (LocalContext.current as? ComponentActivity)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    titleId?.let {
                        Text(
                            text = stringResource(id = titleId),
                        )
                    }
                },
                elevation = 0.dp,
                backgroundColor = White,
                navigationIcon = {
                    if (displayHomeAsUpEnabled) {
                        IconButton(
                            onClick = {
                                handleOnBackPressed?.invoke() ?: activity?.finish()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null,
                                tint = Black
                            )
                        }
                    }
                }
            )
        },
        content = content
    )
}