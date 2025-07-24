package com.example.fetchtakehometest

import com.example.fetchtakehometest.data.network.NetworkItemRepository
import kotlinx.coroutines.test.runTest
import junit.framework.TestCase.assertEquals
import org.junit.Test

class NetworkItemRepositoryTest {
    @Test
    fun networkItemRepository_getItems_verifyItemList() {
        runTest {
            val repository = NetworkItemRepository(
                itemApiService = FakeItemApiService()
            )
            assertEquals(FakeDataSource.testItems, repository.getItems())
        }
    }
}