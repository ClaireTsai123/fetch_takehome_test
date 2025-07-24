package com.example.fetchtakehometest.data.network

import com.example.fetchtakehometest.data.Item
import javax.inject.Inject
import javax.inject.Singleton

interface ItemRepository {
    suspend fun getItems(): List<Item>
}

@Singleton
class NetworkItemRepository @Inject constructor(
    private val itemApiService: ItemApiService
): ItemRepository {
    override suspend fun getItems(): List<Item> {
        return itemApiService.getItems()
    }
}
