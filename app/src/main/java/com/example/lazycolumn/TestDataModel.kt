package com.example.lazycolumn

data class TestDataModel(
    val index: Int,
    val name: String,
    val iconUrl: Int,
    val summary: String,
    val eventModel: EventModel,
    val sellerId: String,
)

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

data class EventItemModel(
    val title: String = "",
    val subTitleList: List<SubTitleModel> = emptyList(),
)

data class SubTitleModel(
    val text: String,
    val url: String,
    val enable: Boolean = false,
)