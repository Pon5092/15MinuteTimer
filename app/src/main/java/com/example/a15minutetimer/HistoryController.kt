package com.example.a15minutetimer

import com.airbnb.epoxy.TypedEpoxyController
import java.util.*

class HistoryController: TypedEpoxyController<List<String>>() {

    override fun buildModels(data: List<String>) {
        data.forEach{ historyData ->
             historyRow{
                id(UUID.randomUUID().toString())
                historyShow(historyData)
             }
        }
    }
}