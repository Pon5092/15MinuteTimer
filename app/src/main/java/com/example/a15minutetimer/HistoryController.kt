package com.example.a15minutetimer

import com.airbnb.epoxy.TypedEpoxyController
import java.util.*

class HistoryController: TypedEpoxyController<List<String>>() {

    override fun buildModels(data: List<String>) {
        data.forEach { historyData ->
            //model_lap_history_list.xmlにおいて
            lapHistoryList {
                id(UUID.randomUUID().toString())
                historyShow(historyData)
            }
        }
    }
}