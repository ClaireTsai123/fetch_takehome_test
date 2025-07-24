package com.example.fetchtakehometest

import com.example.fetchtakehometest.data.Item

object FakeDataSource {
    val testItems = listOf(
        Item(id = 755, listId = 2, name = ""),
        Item(id = 203, listId = 2, name = ""),
        Item(id = 684, listId = 1, name = "Item 684"),
        Item(id = 276, listId = 1, name = "Item 276"),
        Item(id = 736, listId = 3, name = null),
        Item(id = 926, listId = 4, name = null),
        Item(id = 808, listId = 4, name = "Item 808"),
        Item(id = 599, listId = 1, name = null),
        Item(id = 424, listId = 2, name = null),
        Item(id = 444, listId = 1, name = ""),
        Item(id = 809, listId = 3, name = null),
        Item(id = 293, listId = 2, name = null),
        Item(id = 510, listId = 2, name = null),
        Item(id = 680, listId = 3, name = "Item 680"),
        Item(id = 231, listId = 2, name = null),
        Item(id = 534, listId = 4, name = "Item 534"),
        Item(id = 294, listId = 4, name = ""),
        Item(id = 439, listId = 1, name = null),
        Item(id = 156, listId = 2, name = null),
        Item(id = 906, listId = 2, name = "Item 906"),
        Item(id = 49, listId = 2, name = null),
        Item(id = 48, listId = 2, name = null),
        Item(id = 735, listId = 1, name = "Item 735"),
        Item(id = 52, listId = 2, name = ""),
        Item(id = 681, listId = 4, name = "Item 681"),
        Item(id = 137, listId = 3, name = "Item 137"),
        Item(id = 989, listId = 1, name = null),
        Item(id = 94, listId = 1, name = ""),
        Item(id = 177, listId = 1, name = null)
    )
    val groupedItems = testItems.filter { !it.name.isNullOrBlank() }
        .sortedWith(compareBy({ it.listId }, { it.name }))
        .groupBy { it.listId }
}