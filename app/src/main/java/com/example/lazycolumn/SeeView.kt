package com.example.lazycolumn

import android.util.TypedValue
import android.widget.TextView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat

@Composable
fun SeeAllView(
    viewModel: MainActivityViewModel,
    handleItemSelection: (TestDataModel) -> Unit,
    ctaAction: (CurrentModel) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(viewModel.itemList, key = { it.index }) { item ->
            ListContent(false, item, ctaAction, handleItemSelection)
        }
    }
}

@Composable
fun ListContent(
    useSpaceView: Boolean = true,
    myTestDataItem: TestDataModel,
    ctaAction: (CurrentModel) -> Unit,
    handleItemSelection: (TestDataModel) -> Unit,
) {
    val name = myTestDataItem.name
    val image = myTestDataItem.iconUrl
    val summary = myTestDataItem.summary
    val currentModel = myTestDataItem.eventModel.currentModel

    AnimatedVisibility(visible = useSpaceView) {
        Spacer(Modifier.height(20.dp))
    }
    ListItem(name, image, summary, currentModel, ctaAction) {
        handleItemSelection(myTestDataItem)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItem(
    name: String,
    image: Int,
    summary: String,
    currentModel: CurrentModel,
    ctaAction: (CurrentModel) -> Unit,
    openBottomSheet: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = { openBottomSheet() },
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            PanelHeaderView(name, image)
            SummaryView(summary)
            CtaView(
                modifier = Modifier
                    .align(Alignment.End),
                currentModel,
                ctaAction,
            )
        }
    }
}

@Composable
private fun PanelHeaderView(testName: String, testImage: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.weight(0.1f),
            painter = painterResource(testImage),
            contentDescription = null
        )
        AnimatedVisibility(
            visible = testName.isNotEmpty(),
            modifier = Modifier
                .weight(0.8f)
                .padding(start = 20.dp),
        ) {
            Text(
                text = testName,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
            tint = Color.Black
        )
    }
}

@Composable
private fun SummaryView(summary: String) {
    AnimatedVisibility(visible = summary.isNotEmpty()) {
        AndroidView(
            modifier = Modifier.padding(top = 20.dp),
            factory = { context -> TextView(context) },
            update = { textView ->
                with(textView) {
                    text = HtmlCompat.fromHtml(summary, HtmlCompat.FROM_HTML_MODE_COMPACT)
                    setTextColor(ContextCompat.getColor(context, R.color.black))
                    setTextSize(TypedValue.COMPLEX_UNIT_PX, 60F)
                }
            }
        )
    }
}

@Composable
private fun CtaView(
    modifier: Modifier,
    currentModel: CurrentModel,
    ctaAction: (CurrentModel) -> Unit,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = currentModel.showCtaAction
    ) {
        ClickableText(
            modifier = Modifier.padding(top = 20.dp),
            text = AnnotatedString(currentModel.actionTitle),
            onClick = { ctaAction(currentModel) }
        )
    }
}

