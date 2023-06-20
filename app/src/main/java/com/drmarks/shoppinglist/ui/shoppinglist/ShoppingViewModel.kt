package com.drmarks.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.drmarks.shoppinglist.data.db.entities.ShoppingItem
import com.drmarks.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository:ShoppingRepository
):ViewModel() {

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllItems() = repository.getAllItems()
}