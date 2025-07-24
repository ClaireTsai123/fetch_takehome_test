package com.example.fetchtakehometest.ui.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fetchtakehometest.data.Item
import com.example.fetchtakehometest.ui.theme.FetchTakeHomeTestTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemScreen(
    viewModel: ItemViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState().value

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Item List",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValue ->

        when {
            uiState.loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValue),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.error != null -> {
                Text(
                    "Error: ${uiState.error}",
                    modifier = Modifier
                        .padding(paddingValue)
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.error,
                )
            }

            else -> {
                ItemScreenContent(
                    uiState.items,
                    modifier = Modifier.padding(paddingValue)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemScreenContent(
    itemsGrouped: Map<Int, List<Item>>,
    modifier: Modifier = Modifier
) {
    // ensures keys(listId) is in ascending order
    val groupedItems = itemsGrouped.toSortedMap()

    val expanded = remember { mutableStateMapOf<Int, Boolean>() }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        groupedItems.forEach { listId, items ->
            stickyHeader {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val isExpanded = expanded[listId] ?: true
                            expanded[listId] = !isExpanded
                        },
                    color = MaterialTheme.colorScheme.primaryContainer

                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "List ID: $listId",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Total: ${items.size}",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            imageVector = if (expanded[listId] != false)
                                Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "Expand",
                        )
                    }
                }
            }
            if (expanded[listId] != false) {
                items(items) { item ->
                    ItemRow(item)
                }
            }
        }
    }

}

@Composable
private fun ItemRow(
    item: Item
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(text = "id:" + item.id + ", name:" + item.name)
        HorizontalDivider(
            modifier = Modifier.padding(top = 8.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemScreenContentPreview() {
    val itemsGrouped = mapOf(
        1 to listOf(
            Item(id = 1, name = "item1", listId = 11),
            Item(id = 2, name = "item2", listId = 12)
        ),
        2 to listOf(
            Item(id = 3, name = "item33", listId = 102),
            Item(id = 4, name = "item44", listId = 112)
        ),
        3 to listOf(
            Item(id = 1, name = "item11", listId = 222),
            Item(id = 2, name = "item22", listId = 111)
        )
    )
    FetchTakeHomeTestTheme {
        ItemScreenContent(
            itemsGrouped = itemsGrouped
        )
    }
}