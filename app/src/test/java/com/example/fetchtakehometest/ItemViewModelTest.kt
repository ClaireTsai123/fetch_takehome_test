package com.example.fetchtakehometest

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchtakehometest.ui.screens.ItemUiState
import com.example.fetchtakehometest.ui.screens.ItemViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import junit.framework.TestCase.assertEquals
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@OptIn(ExperimentalCoroutinesApi::class)
class ItemViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun finished() {
        Dispatchers.resetMain()
    }

    @Test
    fun itemViewModel_loadItems_verifyItemUiStateSuccess() {
        runTest {
            val fakeRepository = FakeNetworkItemRepository()
            val itemViewModel = ItemViewModel(fakeRepository)

           assertEquals(
               ItemUiState(
                    FakeDataSource.groupedItems
                ),
                itemViewModel.uiState.value
            )
        }
    }
}