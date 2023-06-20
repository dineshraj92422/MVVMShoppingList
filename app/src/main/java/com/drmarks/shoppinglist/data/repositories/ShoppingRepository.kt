package com.drmarks.shoppinglist.data.repositories

import com.drmarks.shoppinglist.data.db.ShoppingDatabase
import com.drmarks.shoppinglist.data.db.entities.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppinDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppinDao().delete(item)

    fun getAllItems() = db.getShoppinDao().getAllShoppingItems()

}