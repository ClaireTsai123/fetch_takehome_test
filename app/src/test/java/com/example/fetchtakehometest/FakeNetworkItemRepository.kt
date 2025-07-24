package com.example.fetchtakehometest

import com.example.fetchtakehometest.data.Item
import com.example.fetchtakehometest.data.network.ItemRepository

class FakeNetworkItemRepository : ItemRepository{

    override suspend fun getItems(): List<Item> {
        return FakeDataSource.testItems
    }
}