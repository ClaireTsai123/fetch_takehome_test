package com.example.fetchtakehometest

import com.example.fetchtakehometest.data.Item
import com.example.fetchtakehometest.data.network.ItemApiService

class FakeItemApiService: ItemApiService {

    override suspend fun getItems(): List<Item> {
        return FakeDataSource.testItems
    }
}