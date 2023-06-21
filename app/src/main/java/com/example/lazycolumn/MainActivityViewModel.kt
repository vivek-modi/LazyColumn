package com.example.lazycolumn

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {

    var itemList = mutableStateListOf<TestDataModel>()
    val requestCompleteLiveData by lazy { MutableLiveData<Boolean>() }

    fun createData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repeat(100) {
                    itemList.add(
                        TestDataModel(
                            index = it,
                            name = "Xyz $it",
                            iconUrl = it,
                            summary = "abc ${it * it}",
                            eventModel = EventModel(
                                currentModel = getCurrentItem(it),
                                eventList = getEventList()
                            ),
                            sellerId = "$it",
                        )
                    )
                }
            }
            requestCompleteLiveData.postValue(true)
        }
    }

    private fun getCurrentItem(index: Int): CurrentModel {
        return CurrentModel(
            actionTitle = "$index",
            subTitle = "$index",
            actionUrl = "$index",
            messageId = index,
            showCtaAction = false
        )
    }

    private fun getEventList(): MutableList<EventItemModel> {
        val eventItemModelList = mutableListOf<EventItemModel>()
        repeat(10) {
            eventItemModelList.add(
                EventItemModel("", listOf(SubTitleModel("", "", true)))
            )
        }
        return eventItemModelList
    }
}