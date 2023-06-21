package com.example.lazycolumn

import androidx.compose.runtime.Immutable

data class TestDataModel(
    val index: Int,
    val name: String,
    val iconUrl: Int,
    val summary: String,
    val eventModel: EventModel,
    val sellerId: String,
)

@Immutable
data class EventModel(
    val currentModel: CurrentModel,
    val eventList: List<EventItemModel>,
)

data class CurrentModel(
    val actionTitle: String = "",
    val subTitle: String,
    val actionUrl: String = "",
    val messageId: Int = 1,
    val showCtaAction: Boolean = false
)

@Immutable
data class EventItemModel(
    val title: String = "",
    val subTitleList: List<SubTitleModel> = emptyList(),
)

data class SubTitleModel(
    val text: String,
    val url: String,
    val enable: Boolean = false,
)